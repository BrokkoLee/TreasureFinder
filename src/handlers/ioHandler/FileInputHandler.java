package handlers.ioHandler;

import characters.Enemy;
import items.Item;
import items.Potion;
import items.Weapon;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class FileInputHandler {
    private static String url = ".\\resources\\" ;
    public static boolean fileMissing = false;

    public static void inputAllCreaturesToList(ArrayList<Enemy> creatureList, String fileName){
        try {
            Scanner text = new Scanner(new File(url + fileName));
            while((text.hasNext()))
            {
                Enemy creature = new Enemy();
                creature.name = text.next().substring(5);
                creature.tier = Integer.parseInt(text.next().substring(5));
                creature.health = Integer.parseInt(text.next().substring(7));
                creature.damage = Integer.parseInt(text.next().substring(13));
                creature.blockDamage = Integer.parseInt(text.next().substring(12));
                creatureList.add(creature);
            }

            text.close();

        }catch (FileNotFoundException e){
            OutputHandler.showMissingFile(fileName);
            fileMissing = true;
        }
    }

    public static void inputAllWeaponToList(ArrayList<Item> weaponList, String fileName){
        try {
            Scanner text = new Scanner(new File(url + fileName));
            while((text.hasNext()))
            {
                Weapon weapon = new Weapon();
                weapon.name = text.next().substring(5);
                weapon.tier = Integer.parseInt(text.next().substring(5));
                weapon.damage = Integer.parseInt(text.next().substring(7));
                weaponList.add(weapon);
            }

            text.close();

        }catch (FileNotFoundException e){
            OutputHandler.showMissingFile(fileName);
            fileMissing = true;
        }
    }

    public static void inputAllPotionToList(ArrayList<Item> potionList, String fileName){
        try {
            Scanner text = new Scanner(new File(url + fileName));
            while((text.hasNext())) {
                Potion potion = new Potion();
                potion.name = text.next().substring(5);
                potion.tier = Integer.parseInt(text.next().substring(5));
                potion.healthGrow = Integer.parseInt(text.next().substring(11));
                potionList.add(potion);
            }
            text.close();

        }catch (FileNotFoundException e){
            OutputHandler.showMissingFile(fileName);
            fileMissing = true;
        }
    }

    public static void inputPositionsToList(ArrayList<int[]> list, String fileName){
        try{
            int[] xy_cord;
            Scanner text = new Scanner(new File(url + fileName));

            while (text.hasNext()){
                text.nextLine();
                xy_cord = new int[2];
                xy_cord[0] = Integer.parseInt(text.next());
                xy_cord[1] = Integer.parseInt(text.next());
                list.add(xy_cord);
            }

        }catch (FileNotFoundException e){
            OutputHandler.showMissingFile(fileName);
            fileMissing = true;
        }
    }
}
