package characters;

import characters.items.Item;
import characters.items.Potion;
import ioHandler.FileInputHandler;
import ioHandler.InputHandler;
import ioHandler.OutputHandler;

import java.util.ArrayList;

public class Player extends Character{

    public ArrayList<Item> inventory = new ArrayList<>();

    public void usePoiton(){
        if (hasPoiton(inventory)){
            OutputHandler.showYourPotions(inventory);
            String chosenPotionName = InputHandler.getPotionName();

        }else{
            OutputHandler.showZeroPotions();
        }
    }

    private boolean hasPoiton(ArrayList<Item> inventory){
        boolean potion = false;
        for (Item item : inventory) {
            if(item instanceof Potion) potion = true;
        }
        return potion;

    }

    public void changeWeapon(){

    }

    public void openInventory(){
        if (inventory.size() == 0){
            OutputHandler.showZeroItems();
        }else{
            OutputHandler.showYourItems();
            OutputHandler.showInventory(inventory);
        }
    }

    public void move(){
        System.out.println("Mozogsz:D");
    }
}