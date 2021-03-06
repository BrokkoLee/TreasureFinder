package handlers.gameHandler;

import characters.Enemy;
import characters.Player;
import handlers.ioHandler.Command;
import items.Item;
import handlers.ioHandler.InputHandler;
import handlers.ioHandler.OutputHandler;
import map.Map;

import java.util.ArrayList;
import java.util.Arrays;

public class GameMaster {

    private Map map = new Map();
    private Player player = new Player();
    private DataHandler dataHandler = new DataHandler();

    public void playGame() {
        dataHandler.loadData(map);
        setDefaultStats();
        OutputHandler.introduction();
        runGame();
    }

    private void runGame() {
        while (true) {
            OutputHandler.showNewLine();
            Command choice = InputHandler.getChoice();
            OutputHandler.showNewLine();

            switch (choice) {
                case move:
                    player.move(map);
                    break;
                case stats:
                    OutputHandler.showStats(player, map);
                    break;
                case inventory:
                    player.openInventory();
                    break;
                case weapon:
                    player.changeWeapon();
                    break;
                case potion:
                    player.usePotion();
                    break;
                case map:
                    OutputHandler.showCurrentLocation(player.x_cord, player.y_cord);
                    OutputHandler.showMap(player, map);
                    break;
                case exit:
                    exitGame();
                    return;
                default:
                    OutputHandler.showWrongChoice();
                    break;
            }
        }
    }

    private void setDefaultStats() {
        //TODO input default stats from file
        player.name = InputHandler.getPlayerName();

        player.x_cord = 1;
        player.y_cord = 1;
        player.health = 10;
        player.handDamage = 5;
        player.blockDamage = 3;
        player.currentWeapon = null;
        player.inventory = new ArrayList<>();

        player.setDamage();
    }

    static void stopGame() {
        System.exit(1);
    }

    private void battle() {
        int tier = getTier();

        Enemy enemy = getRandomCreature(tier);

        OutputHandler.showEnteredBattle(enemy);
        OutputHandler.showEnemyInfo(enemy);

        attackPhase(enemy);
    }

    private void attackPhase(Enemy enemy) {
        //TODO remove forever loop
        while (true) {
            if (player.health > 0) {
                player.attackPhase(enemy, player);
            } else {
                OutputHandler.playerDied();
                playerLostGame();
                break;
            }

            if (enemy.health > 0) {
                enemy.attackPhase(enemy, player);
            } else {
                OutputHandler.showDefeatedEnemy(enemy);
                int[] playerCords = {player.x_cord, player.y_cord};
                removeCords(playerCords, map.enemyPositionList);
                break;
            }
        }
    }

    private int getTier() {
        if (player.x_cord < 3) {
            return 1;
        } else if (player.x_cord < 6) {
            return 2;
        } else {
            return 3;
        }
    }

    private void pickUpItem(int[] itemCords, ArrayList<Item> itemList, ArrayList<int[]> itemCordsList) {
        int tier = getTier();

        Item item = getRandomItem(tier, itemList);
        OutputHandler.showPickedUpItem(item);
        GameMaster.addItemToInventory(item, player.inventory);
        removeCords(itemCords, itemCordsList);
    }

    private void removeCords(int[] itemCords, ArrayList<int[]> cordsList) {
        for (int[] cords : cordsList) {
            if (Arrays.equals(cords, itemCords)) {
                cordsList.remove(cords);
                break;
            }
        }
    }

    public static boolean is_colliding(int x_cord, int y_cord, ArrayList<int[]> bufferCoordinates) {
        int[] playerCoordinates = {x_cord, y_cord};
        return is_contains(bufferCoordinates, playerCoordinates);
    }

    private static boolean is_contains(ArrayList<int[]> mainList, int[] array) {
        boolean is_containing = false;
        for (int[] subArray : mainList) if (Arrays.equals(subArray, array)) is_containing = true;
        return is_containing;
    }

    public static void addItemToInventory(Item item, ArrayList<Item> inventory) {
        inventory.add(item);
    }

    private Enemy getRandomCreature(int tier) {
        ArrayList<Enemy> creatureList = new ArrayList<>();
        Enemy enemy;

        for (Enemy creature : dataHandler.creatureList)
            if (creature.tier == tier) creatureList.add(creature);

        int randomIndex = (int) (Math.random() * creatureList.size()) + 1;

        enemy = creatureList.get(randomIndex - 1);
        return enemy;
    }

    private Item getRandomItem(int tier, ArrayList<Item> itemList) {
        ArrayList<Item> randomSpecItems = new ArrayList<>();

        for (Item item : itemList)
            if (item.tier == tier) randomSpecItems.add(item);

        int randomIndex = (int) (Math.random() * randomSpecItems.size()) + 1;

        return randomSpecItems.get(randomIndex - 1);
    }

    private void wonGame() {
        OutputHandler.foundTreasure();
        exitGame();
    }

    private void playerLostGame() {
        String choice = InputHandler.getNewGameChoice();

        if (choice.equals("Yes")) {
            setDefaultStats();
        } else if (choice.equals("No")) {
            exitGame();
        } else {
            OutputHandler.showWrongChoice();
            playerLostGame();
        }
    }

    private void exitGame() {
        OutputHandler.showExitGame();
        System.exit(1);
    }
}