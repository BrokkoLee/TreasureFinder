package handlers.ioHandler;

import java.util.Scanner;

public class InputHandler {
    private static Scanner sc = new Scanner(System.in);

    public static String getChoice(){
        OutputHandler.showChoices();
        return sc.next();
    }

    public static String getNewGameChoice(){
        OutputHandler.showNewGameChoice();
        return sc.next();
    }

    public static String getAttackPhaseChoice(){
        OutputHandler.showAttackPhaseChoices();
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
        return sc.next();
    }

    public static String getDirection(){
        OutputHandler.showMoveChoice();
        return sc.next();
    }
}
