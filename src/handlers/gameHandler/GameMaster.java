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
    public ArrayList<Item> weaponList = new ArrayList<>();
    public ArrayList<Item> potionList = new ArrayList<>();
    public ArrayList<Enemy> creatureList = new ArrayList<>();
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

    private Item getRandomItem(int tier, ArrayList<Item> itemList){
        ArrayList<Item> randomSpecItems = new ArrayList<>();

        for (Item item : itemList) {
            if (item.tier == tier)
                randomSpecItems.add(item);
        }

        int randomIndex = (int)(Math.random() * randomSpecItems.size()-1) + 1;

        return randomSpecItems.get(randomIndex);
    }

    public void hitObject(Map map, int oldXCord, int oldYCord, GameMaster gameMaster){
        int[] playerCords = new int[2];
        playerCords[0] = player.x_cord;
        playerCords[1] = player.y_cord;

        if (checkHitObject(map.enemyPositionList)){
            //TODO battle()
        } else if (checkHitObject(map.weaponPositionList)){
            pickUpItem(playerCords, weaponList, map.weaponPositionList);
        } else if (checkHitObject(map.potionPositionList)) {
            pickUpItem(playerCords, potionList, map.potionPositionList);
        } else if (checkHitObject(map.wallPositionList)) {
            hitWall(map.wallPositionList, map, oldXCord, oldYCord, gameMaster);
        }

    }

    private void pickUpItem(int[] itemCords, ArrayList<Item> itemList, ArrayList<int[]> itemCordsList){

        int x = itemCords[0];
        int y = itemCords[1];
        int tier;

        if (x < 3){
            tier = 1;
        } else if (x < 6){
            tier = 2;
        } else {
            tier = 3;
        }

        Item item = getRandomItem(tier, itemList);
        OutputHandler.showPickedUpItem(item);
        GameMaster.addItemToInventory(item, player.inventory);
        removeItem(itemCords, itemCordsList);
    }

    private void removeItem(int[] itemCords, ArrayList<int[]> cordsList){
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

    public static void addItemToInventory(Item item, ArrayList<Item> inventory){
        inventory.add(item);
    }

    private void setDefaultStats(){
        player.name = InputHandler.getPlayerName();

        player.health = 10;
        player.handDamage = 1;
        player.blockDamage = 1;

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

    private void runGame(GameMaster gameMaster){
        while (true){
            OutputHandler.showNewLine();
            String choice = InputHandler.getChoice();
            OutputHandler.showNewLine();

            if ("Move".equals(choice)) {
                player.move(map, gameMaster);
                OutputHandler.showNewLocation(player.x_cord, player.y_cord);
            } else if ("Show_stats".equals(choice)) {
                OutputHandler.showStats(player);
            } else if ("Open_inventory".equals(choice)) {
                player.openInventory();
            } else if ("Change_weapon".equals(choice)) {
                player.changeWeapon();
            } else if ("Use_potion".equals(choice)) {
                player.usePotion();
            } else if ("Save_game".equals(choice)) {
                saveGame();
            } else if ("Exit_game".equals(choice)) {
                OutputHandler.showExitGame();
                return;
            } else {
                OutputHandler.showWrongChoice();
            }
        }
    }
}