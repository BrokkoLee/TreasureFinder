package ioHandler;

import characters.Enemy;
import characters.items.Item;
import characters.items.Potion;
import characters.items.Weapon;
import characters.Character;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class ScannerHandler {
    public static void inputAllCreaturesToList(ArrayList<Enemy> creaturelist){
        try {
            Scanner text = new Scanner(new File("I:\\Progrik\\Joe\\src\\characters\\creatures"));
            while((text.hasNext()))
            {
                Character creature = new Enemy();
                creature.name = text.next().substring(5);
                ((Enemy) creature).tier = Integer.parseInt(text.next().substring(5));
                creature.health = Integer.parseInt(text.next().substring(7));
                creature.attackDamage = Integer.parseInt(text.next().substring(13));
                creature.blockDamage = Integer.parseInt(text.next().substring(12));
                creaturelist.add((Enemy) creature);
            }

            text.close();

        }catch (FileNotFoundException e){
            OutputHandler.showMissingtxt("creatures");
        }
    }

    public static void inputAllWeaponToList(ArrayList<Weapon> weaponlist){
        try {
            Scanner text = new Scanner(new File("I:\\Progrik\\Joe\\src\\characters\\items\\weapons"));
            while((text.hasNext()))
            {
                Item weapon = new Weapon();
                weapon.name = text.next().substring(5);
                weapon.tier = Integer.parseInt(text.next().substring(5));
                ((Weapon) weapon).damage = Integer.parseInt(text.next().substring(7));
                weaponlist.add((Weapon) weapon);
            }

            text.close();

        }catch (FileNotFoundException e){
            OutputHandler.showMissingtxt("Weapon");
        }
    }

    public static void inputAllPotionToList(ArrayList<Potion> potionlist){
        try {
            Scanner text = new Scanner(new File("I:\\Progrik\\Joe\\src\\characters\\items\\potions"));
            while((text.hasNext())) {
                Item potion = new Potion();
                potion.name = text.next().substring(5);
                potion.tier = Integer.parseInt(text.next().substring(5));
                ((Potion) potion).healthGrow = Integer.parseInt(text.next().substring(11));
                potionlist.add((Potion) potion);
            }
            text.close();

        }catch (FileNotFoundException e){
            OutputHandler.showMissingtxt("Potion");
        }
    }
}
