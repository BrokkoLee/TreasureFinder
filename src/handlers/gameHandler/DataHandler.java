package handlers.gameHandler;


import characters.Enemy;
import handlers.ioHandler.FileInputHandler;
import items.Item;
import map.Map;

import java.util.ArrayList;

class DataHandler {
    ArrayList<Item> weaponList = new ArrayList<>();
    ArrayList<Item> potionList = new ArrayList<>();
    ArrayList<Enemy> creatureList = new ArrayList<>();

    void loadData(Map map){
        map.loadLocations();
        loadItems();
        loadCreatures();
        //TODO throws error if missing file
        //stopGameIfMissingFile();
    }

    private void loadItems(){
        FileInputHandler.inputAllWeaponToList(weaponList, "weapons");
        FileInputHandler.inputAllPotionToList(potionList, "potions");
    }

    private void loadCreatures(){
        FileInputHandler.inputAllCreaturesToList(creatureList, "enemyCreatures");
    }


}
