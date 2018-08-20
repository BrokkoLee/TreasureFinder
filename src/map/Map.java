package map;

import handlers.ioHandler.FileInputHandler;

import java.util.ArrayList;

public class Map{
    public ArrayList<int[]> wallPositionList = new ArrayList<>();
    public ArrayList<int[]> potionPositionList = new ArrayList<>();
    public ArrayList<int[]> weaponPositionList = new ArrayList<>();
    public ArrayList<int[]> enemyPositionList = new ArrayList<>();

    public void loadLocations(){
        FileInputHandler.inputPositionsToList(wallPositionList, "walls", "I:\\Progrik\\Joe\\src\\map\\walls");
        FileInputHandler.inputPositionsToList(potionPositionList, "potionLocation", "I:\\Progrik\\Joe\\src\\map\\potionLocation");
        FileInputHandler.inputPositionsToList(weaponPositionList, "weaponLocation", "I:\\Progrik\\Joe\\src\\map\\weaponLocation");
        FileInputHandler.inputPositionsToList(enemyPositionList, "enemyLocation", "I:\\Progrik\\Joe\\src\\map\\enemyLocation");
    }
}
