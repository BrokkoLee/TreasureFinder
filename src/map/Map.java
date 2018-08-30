package map;

import handlers.ioHandler.FileInputHandler;

import java.util.ArrayList;

public class Map {
    public final int maxX = 11;
    public final int maxY = 11;

    public ArrayList<int[]> wallPositionList = new ArrayList<>();
    public ArrayList<int[]> potionPositionList = new ArrayList<>();
    public ArrayList<int[]> weaponPositionList = new ArrayList<>();
    public ArrayList<int[]> enemyPositionList = new ArrayList<>();
    public ArrayList<int[]> treasurePositionList = new ArrayList<>();

    public void loadLocations() {
        FileInputHandler.inputPositionsToList(wallPositionList, "walls");
        FileInputHandler.inputPositionsToList(potionPositionList, "potionLocation");
        FileInputHandler.inputPositionsToList(weaponPositionList, "weaponLocation");
        FileInputHandler.inputPositionsToList(enemyPositionList, "enemyLocation");
        FileInputHandler.inputPositionsToList(treasurePositionList, "treasureLocation");
    }

}
