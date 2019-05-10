package characters;

import handlers.gameHandler.GameMaster;
import handlers.ioHandler.InputHandler;
import handlers.ioHandler.OutputHandler;
import items.Item;
import items.Potion;
import items.Weapon;
import map.Map;

import java.util.ArrayList;

import static handlers.gameHandler.GameMaster.is_colliding;

public class Player extends Character {
    public int maxHealth = 20;
    public Weapon currentWeapon;
    private int weaponDamage;
    public int handDamage;
    public int damage;
    public ArrayList<Item> inventory = new ArrayList<>();

    public void setDamage() {
        damage = handDamage + weaponDamage;
    }

    public void usePotion() {
        boolean usedPotion = false;

        if (hasPotion(inventory)) {
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
                    usedPotion = true;
                    break;
                }
            }
            if (!usedPotion) {
                OutputHandler.showWrongChoice();
                usePotion();
            }
        } else {
            OutputHandler.showZeroPotions();
        }
    }

    private boolean hasPotion(ArrayList<Item> inventory) {
        boolean potion = false;
        for (Item item : inventory) {
            if (item instanceof Potion) potion = true;
        }
        return potion;

    }

    public void changeWeapon() {
        boolean hasChanged = false;


        if (hasWeapon(inventory)) {
            OutputHandler.showYourWeapons(inventory);
            String chosenWeapon = InputHandler.getWeaponName();

            for (Item item : inventory) {
                if (item.name.equals(chosenWeapon)) {
                    if (currentWeapon != null) {
                        GameMaster.addItemToInventory(currentWeapon, inventory);
                    }
                    currentWeapon = (Weapon) item;
                    weaponDamage = ((Weapon) item).damage;
                    setDamage();
                    OutputHandler.showChangedWeapon(item.name);
                    hasChanged = true;
                    inventory.remove(item);
                    break;
                }
            }
            if (!hasChanged) {
                OutputHandler.showWrongChoice();
                changeWeapon();
            }

        } else {
            OutputHandler.showZeroWeapons();
        }
    }

    private boolean hasWeapon(ArrayList<Item> inventory) {
        boolean hasWeapon = false;
        for (Item item : inventory) {
            if (item instanceof Weapon) hasWeapon = true;
        }
        return hasWeapon;
    }

    public void openInventory() {
        if (inventory.size() == 0) {
            OutputHandler.showZeroItems();
        } else {
            OutputHandler.showYourItems();
            OutputHandler.showInventory(inventory);
        }
    }

    public void move(Map map) {
        int[] oldCords = {x_cord, y_cord};
        String direction;

        OutputHandler.showCurrentLocation(x_cord, y_cord);
        OutputHandler.showDirections();

        direction = InputHandler.getDirection();

        switch (direction) {
            case "North":
                y_cord += 1;
                break;
            case "South":
                y_cord -= 1;
                break;
            case "West":
                x_cord += -1;
                break;
            case "East":
                x_cord += 1;
                break;
            default:
                OutputHandler.showWrongDirection();
                OutputHandler.showDirections();
                break;
        }

        if (is_colliding(x_cord, y_cord, map.wallPositionList)) {
            OutputHandler.showHitWall();
            x_cord = oldCords[0];
            y_cord = oldCords[1];
            move(map);
        } else {
            OutputHandler.showNewLocation(x_cord, y_cord);
        }
    }

    @Override
    public void attackPhase(Enemy enemy, Player player) {
        OutputHandler.showTurn(this);

        String choice = InputHandler.getAttackPhaseChoice();

        if (choice.equals("Attack")) {
            attack(enemy);
        } else if (choice.equals("Use_potion")) {
            usePotion();
        } else {
            OutputHandler.showWrongChoice();
            attackPhase(enemy, this);
        }
    }

    private void attack(Enemy enemy) {
        int attackDamage = damage - enemy.blockDamage;

        if (attackDamage > 0) {
            enemy.health -= attackDamage;
            OutputHandler.showPlayerDamageInfo(enemy, attackDamage);
        } else {
            OutputHandler.showZeroPlayerDamage(enemy);
        }
    }
}