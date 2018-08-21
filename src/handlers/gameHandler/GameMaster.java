package handlers.gameHandler;

import characters.Enemy;
import characters.Player;
import items.Item;
import handlers.ioHandler.InputHandler;
import handlers.ioHandler.OutputHandler;
import handlers.ioHandler.FileInputHandler;
import map.Map;

import java.util.ArrayList;
import java.util.Arrays;

public class GameMaster {
    private ArrayList<Item> weaponList = new ArrayList<>();
    private ArrayList<Item> potionList = new ArrayList<>();
    private ArrayList<Enemy> creatureList = new ArrayList<>();
    private Map map = new Map();
    private Player player;

    public GameMaster(Player player) {
        this.player = player;
    }

    public void playGame(GameMaster gameMaster){
        loadData();
        setDefaultStats();
        OutputHandler.introduction();
        runGame(gameMaster);
    }

    private void runGame(GameMaster gameMaster){
        while (true){
            OutputHandler.showNewLine();
            String choice = InputHandler.getChoice();
            OutputHandler.showNewLine();

            if ("Move".equals(choice)) {
                OutputHandler.showMap(player, map);
                player.move(map, gameMaster);
                OutputHandler.showMap(player, map);
                OutputHandler.showNewLocation(player.x_cord, player.y_cord);
            } else if ("Show_stats".equals(choice)) {
                OutputHandler.showStats(player, map);
            } else if ("Open_inventory".equals(choice)) {
                player.openInventory();
            } else if ("Change_weapon".equals(choice)) {
                player.changeWeapon();
            } else if ("Use_potion".equals(choice)) {
                player.usePotion();
            } else if ("Save_game".equals(choice)) {
                saveGame();
            } else if ("Exit_game".equals(choice)) {
                exitGame();
                return;
            } else {
                OutputHandler.showWrongChoice();
            }
        }
    }

    private void setDefaultStats(){
        player.name = InputHandler.getPlayerName();

        player.x_cord = 7;
        player.y_cord = 9;
        player.health = 10;
        player.handDamage = 5;
        player.blockDamage = 3;

        addItemToInventory(weaponList.get(0), player.inventory);
        addItemToInventory(weaponList.get(1), player.inventory);
        addItemToInventory(potionList.get(0), player.inventory);

        player.setDamage();
    }

    private void loadData(){
        map.loadLocations();
        loadItems();
        loadCreatures();
    }

    private void loadItems(){
        FileInputHandler.inputAllWeaponToList(weaponList);
        FileInputHandler.inputAllPotionToList(potionList);
    }

    private void loadCreatures(){
        FileInputHandler.inputAllCreaturesToList(creatureList);
    }

    private void saveGame(){
        //TODO write current data to file
        //TODO add loadGame method
    }

    //TODO loadGame method

    private void battle(){
        int tier = getTier();

        Enemy enemy = getRandomCreature(tier);

        OutputHandler.showEnteredBattle(enemy);

        attackPhase(enemy);
    }

    private void attackPhase(Enemy enemy){
        while (true){
            if (player.health > 0){
                player.attackPhase(enemy, player);
            } else {
                OutputHandler.playerDied();
                playerLostGame();
                return;
            }

            if (enemy.health > 0) {
                enemy.attackPhase(enemy, player);

                OutputHandler.showDefeatedEnemy(enemy);
                int[] playerCords = {player.x_cord, player.y_cord};
                removeCords(playerCords, map.enemyPositionList);
                return;
            }
        }
    }

    private int getTier() {
        if (player.x_cord < 3){
            return 1;
        } else if (player.x_cord < 6){
            return 2;
        } else {
            return 3;
        }
    }

    private void pickUpItem(int[] itemCords, ArrayList<Item> itemList, ArrayList<int[]> itemCordsList){
        int tier = getTier();

        Item item = getRandomItem(tier, itemList);
        OutputHandler.showPickedUpItem(item);
        GameMaster.addItemToInventory(item, player.inventory);
        removeCords(itemCords, itemCordsList);
    }

    private void removeCords(int[] itemCords, ArrayList<int[]> cordsList){
        for (int[] cords : cordsList) {
            if (Arrays.equals(cords, itemCords)){
                cordsList.remove(cords);
                break;
            }
        }
    }

    private boolean checkHitObject(ArrayList<int[]> list){
        for (int[] xNy : list) {
            if (player.x_cord == xNy[0] && player.y_cord == xNy[1]) return  true;
        }

        return false;
    }

    private void hitWall(ArrayList<int[]> wallPositionsList, Map map, int oldXCord, int oldYCord, GameMaster gameMaster){
        for (int[] xNy : wallPositionsList) {
            while (xNy[0] == player.x_cord && xNy[1] == player.y_cord){
                player.x_cord = oldXCord;
                player.y_cord = oldYCord;
                OutputHandler.showHitWall();
                player.move(map, gameMaster);
            }
        }
    }

    public void hitObject(Map map, int oldXCord, int oldYCord, GameMaster gameMaster){
        int[] playerCords = new int[2];
        playerCords[0] = player.x_cord;
        playerCords[1] = player.y_cord;

        if (checkHitObject(map.enemyPositionList)){
            battle();
        } else if (checkHitObject(map.weaponPositionList)){
            pickUpItem(playerCords, weaponList, map.weaponPositionList);
        } else if (checkHitObject(map.potionPositionList)) {
            pickUpItem(playerCords, potionList, map.potionPositionList);
        } else if (checkHitObject(map.wallPositionList)) {
            hitWall(map.wallPositionList, map, oldXCord, oldYCord, gameMaster);
        } else if (checkHitObject(map.treasurePositionList)){
            wonGame();
        }

    }

    public static void addItemToInventory(Item item, ArrayList<Item> inventory){
        inventory.add(item);
    }

    private Enemy getRandomCreature(int tier) {
        ArrayList<Enemy> creatureList = new ArrayList<>();

        for (Enemy creature : this.creatureList)
            if (creature.tier == tier) creatureList.add(creature);

            int randomIndex = (int)(Math.random() * creatureList.size() ) + 1;

        return creatureList.get(randomIndex-1);
    }

    private Item getRandomItem(int tier, ArrayList<Item> itemList){
        ArrayList<Item> randomSpecItems = new ArrayList<>();

        for (Item item : itemList)
            if (item.tier == tier) randomSpecItems.add(item);

        int randomIndex = (int)(Math.random() * randomSpecItems.size()) + 1;

        return randomSpecItems.get(randomIndex-1);
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
            OutputHandler.showExitGame();
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