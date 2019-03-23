package handlers.ioHandler;

import java.util.Scanner;

public class InputHandler {
    private static Scanner sc = new Scanner(System.in);

    private static boolean either(String givenWord, String[] list) {
        for (String word : list) {
            if (word.equals(givenWord)) return true;
        }
        return false;
    }

    public static Command getChoice() {
        //TODO fix input with space eg.:
        OutputHandler.showChoices();
        String input = getInput();
        while (true) {
            if (either(input, Command.move.text)){
                return Command.move;
            } else if (either(input, Command.stats.text)) {
                return Command.stats;
            } else if (either(input, Command.inventory.text)) {
                return Command.inventory;
            } else if (either(input, Command.potion.text)) {
                return Command.potion;
            } else if (either(input, Command.weapon.text)) {
                return Command.weapon;
            } else if (either(input, Command.map.text)) {
                return Command.map;
            } else if (either(input, Command.exit.text)) {
                return Command.exit;
            } else {
                OutputHandler.showWrongChoice();
                OutputHandler.showChoices();
                input = getInput();
            }
        }
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

    private static String getInput() {
        return sc.next().toLowerCase();
    }
}
