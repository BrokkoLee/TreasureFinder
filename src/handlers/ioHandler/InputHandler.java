package handlers.ioHandler;

import java.util.Scanner;

public class InputHandler {
    private static Scanner sc = new Scanner(System.in);

    public static String getChoice(){
        OutputHandler.showChoices();
        return sc.next();
    }

    public static String getPotionName(){
        OutputHandler.showInputPotionName();
        return sc.next();
    }

    public static String getWeaponName(){
        OutputHandler.showInputWeaponName();
        return sc.next();
    }

    public static String getPlayerName(){
        OutputHandler.showInputPlayerName();
        return sc.nextLine();
    }

    public static String getDirection(){
        OutputHandler.showDirections();
        return sc.next();
    }
}
