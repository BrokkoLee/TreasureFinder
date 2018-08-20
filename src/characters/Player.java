package characters;

import items.Item;
import items.Potion;
import items.Weapon;
import map.Map;
import handlers.ioHandler.InputHandler;
import handlers.ioHandler.OutputHandler;
import handlers.gameHandler.GameMaster;

import java.util.ArrayList;

public class Player extends Character{
    public int maxHealth = 20;
    public Weapon currentWeapon;
    private int weaponDamage;
    public int handDamage;
    public int damage;
    public ArrayList<Item> inventory = new ArrayList<>();

    public void setDamage(){
        damage = handDamage + weaponDamage;
    }

    public void usePotion(){
        if (hasPotion(inventory)){
            OutputHandler.showYourPotions(inventory);
            String chosenPotionName = InputHandler.getPotionName();
            for (Item item : inventory) {
                if (item instanceof Potion && item.name.equals(chosenPotionName)) {
                    health += ((Potion) item).healthGrow;

                    if (health > maxHealth)
                        health = maxHealth;

                    inventory.remove(item);
                    OutputHandler.showUsedPotion(item.name);
                    OutputHandler.showHealthChange(health);
                    break;
                }
            }
        }else{
            OutputHandler.showZeroPotions();
        }
    }

    private boolean hasPotion(ArrayList<Item> inventory){
        boolean potion = false;
        for (Item item : inventory) {
            if(item instanceof Potion) potion = true;
        }
        return potion;

    }

    public void changeWeapon(){
        OutputHandler.showYourWeapons(inventory);
        String chosenWeapon = InputHandler.getWeaponName();
        if (hasWeapon(inventory)){
            for (Item item : inventory) {
                if(item.name.equals(chosenWeapon)){
                    if (currentWeapon != null){
                        GameMaster.addItemToInventory(currentWeapon, inventory);
                    }
                    currentWeapon = (Weapon) item;
                    weaponDamage = ((Weapon) item).damage;
                    setDamage();
                    OutputHandler.showChangedWeapon(item.name);
                    inventory.remove(item);
                    break;
                }
            }
        }else{
            OutputHandler.showZeroWeapons();
        }
    }

    private boolean hasWeapon(ArrayList<Item> inventory){
        boolean weapon = false;
        for (Item item : inventory) {
            if(item instanceof Weapon) weapon = true;
        }
        return weapon;
    }

    public void openInventory(){
        if (inventory.size() == 0){
            OutputHandler.showZeroItems();
        }else{
            OutputHandler.showYourItems();
            OutputHandler.showInventory(inventory);
        }
    }

    public void move(Map map, GameMaster gameMaster){
        int oldXCord = x_cord;
        int oldYCord = y_cord;

        OutputHandler.showCurrentLocation(x_cord, y_cord);

        String direction = InputHandler.getDirection();
        if ("North".equals(direction)) {
            y_cord += 1;

        } else if ("South".equals(direction)) {
            y_cord -= 1;

        } else if ("West".equals(direction)) {
            x_cord += -1;

        } else if ("East".equals(direction)) {
            x_cord += 1;

        } else {
            OutputHandler.showWrongDirection();
            move(map, gameMaster);
        }

        gameMaster.hitObject(map, oldXCord, oldYCord, gameMaster);

    }

}