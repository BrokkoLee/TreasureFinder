package handlers.gameHandler;


import characters.Enemy;
import handlers.ioHandler.FileInputHandler;
import items.Item;
import map.Map;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import static handlers.gameHandler.GameMaster.stopGame;

class DataHandler {
    ArrayList<Item> weaponList = new ArrayList<>();
    ArrayList<Item> potionList = new ArrayList<>();
    ArrayList<Enemy> creatureList = new ArrayList<>();

    void loadData(Map map) {
        try {
            map.loadLocations();
            loadItems();
            loadCreatures();
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e);
            stopGame();
        }
    }

    private void loadItems() throws FileNotFoundException {
        FileInputHandler.inputAllWeaponToList(weaponList, "weapons");
        FileInputHandler.inputAllPotionToList(potionList, "potions");
    }

    private void loadCreatures() throws FileNotFoundException {
        FileInputHandler.inputAllCreaturesToList(creatureList, "enemyCreatures");
    }


}
