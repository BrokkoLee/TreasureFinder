package map;

import characters.Player;
import handlers.ioHandler.FileInputHandler;

import java.util.ArrayList;

public class Map {
    public final int maxX = 11;
    public final int maxY = 11;

    public ArrayList<int[]> wallPositionList = new ArrayList<>();
    public ArrayList<int[]> potionPositionList = new ArrayList<>();
    public ArrayList<int[]> weaponPositionList = new ArrayList<>();
    public ArrayList<int[]> enemyPositionList = new ArrayList<>();

    public void loadLocations() {
        FileInputHandler.inputPositionsToList(wallPositionList, "walls", "I:\\Progrik\\Joe\\src\\map\\walls");
        FileInputHandler.inputPositionsToList(potionPositionList, "potionLocation", "I:\\Progrik\\Joe\\src\\map\\potionLocation");
        FileInputHandler.inputPositionsToList(weaponPositionList, "weaponLocation", "I:\\Progrik\\Joe\\src\\map\\weaponLocation");
        FileInputHandler.inputPositionsToList(enemyPositionList, "enemyLocation", "I:\\Progrik\\Joe\\src\\map\\enemyLocation");
    }

    public void showMap(Player player) {
        boolean placedX = false;

        for (int Y = maxY; Y >= 0; Y--) {
            for (int X = 0; X <= maxX; X++) {
                for (int[] xNy : wallPositionList) {
                    placedX = false;
                    if (xNy[0] == X && xNy[1] == Y) {
                        System.out.print("XX");
                        placedX = true;
                        break;
                    }
                }
                if (X == player.x_cord && Y == player.y_cord){
                    System.out.print("OO");
                    placedX = true;
                }

                if (!placedX) {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }
}
