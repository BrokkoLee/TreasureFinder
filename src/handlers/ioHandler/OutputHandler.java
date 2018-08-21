package handlers.ioHandler;

import characters.Character;
import characters.Enemy;
import items.Item;
import items.Potion;
import characters.Player;
import items.Weapon;

import java.util.ArrayList;

public class OutputHandler {

    public static void introduction(){
        System.out.println("Welcome to TreasureFinder!");
        System.out.println("Your aim is to find the lost treasure.");
        System.out.println("In the way of your aim you have to fight with evil creatures to move on.");
        System.out.println("Good luck!");
    }

    public static void showMissingtxt(String fileName){
        System.out.println("Missing file: " + fileName + ".txt" );
    }

    public static void showDirections(){
        System.out.print("The directions: North, South, East, West:" );
    }

    //------------ Choice -----------------

    public static void showChoices(){
        System.out.println("Enter what you want to do!");
        System.out.println("Move, Show_stats, Open_inventory, Use_potion, Change_weapon, Save_game, Exit_game");
        System.out.print("Enter your choice here: ");
    }

    public static void showAttackPhaseChoices(){
        System.out.println("Enter what you want to do!");
        System.out.println("Attack, Use_potion");
        System.out.print("Enter your choice here: ");
    }

    public static void showWrongChoice(){
        System.out.println("\nPlease enter the choice name correctly!\n");
    }

    //------------ Move ------------------

    public static void showNewLocation(int x_cord, int y_cord) {
        System.out.println("\nYou moved to X: " + x_cord + " Y: "+ y_cord);
    }

    public static void showCurrentLocation(int x_cord, int y_cord) {
        System.out.println("Current location: X: " + x_cord + " Y: " + y_cord + "\n");
    }

    public static void showWrongDirection() {
        System.out.println("\nThere is no direction like this.\n");
    }

    //------------- Items ------------------

    public static void showInventory(ArrayList<Item> inventory){
        for (Item item : inventory) {
            System.out.println(item.name);
        }
    }

    public static void showYourItems(){
        System.out.println("Content of your inventory:");
    }

    public static void showZeroItems(){
        System.out.println("Your inventory is empty.");
    }

    //------------- Potion -----------------

    public static void showYourPotions(ArrayList<Item> inventory){
        System.out.println("Your potions:");
        for (Item item : inventory) {
            if (item instanceof Potion) System.out.println("Name: " + item.name + "\nHealth regen: " + ((Potion) item).healthGrow);
        }
    }

    public static void showInputPotionName(){
        System.out.print("\nEnter the name of the potion you want to use: ");
    }

    public static void showUsedPotion(String potionName){
        System.out.println("\nYou used " + potionName);
    }

    public static void showZeroPotions(){
        System.out.println("You have zero potions!");
    }

    //------------- Weapon -----------------

    public static void showYourWeapons(ArrayList<Item> inventory){
        System.out.println("Your weapons:");
        for (Item item : inventory) {
            if (item instanceof Weapon) System.out.println("Name: " + item.name + "\nDamage: " + ((Weapon) item).damage);
        }
    }

    public static void showInputWeaponName(){
        System.out.print("\nEnter the name of the weapon you want to use: ");
    }

    public static void showChangedWeapon(String weaponName){
        System.out.println("\nYou changed to " + weaponName);
    }

    public static void showZeroWeapons(){
        System.out.println("You have zero Weapons!");
    }


    //------------- Health ----------------

    public static void showHealthChange(int health){
        System.out.println("Your health changed to " + health);
    }

    public static void showStats(Player player){
        System.out.println("Your stats:");
        System.out.println("Name: " + player.name);
        System.out.println("Max health: " + player.maxHealth);
        System.out.println("Health: " + player.health);
        if (player.currentWeapon == null){
            System.out.println("Current Weapon: None");
        }else{
            System.out.println("Current Weapon: " + player.currentWeapon.name);
        }
        System.out.println("Damage: " + player.damage);
        System.out.println("Blocking: " + player.blockDamage);
        System.out.println("Position: X: " + player.x_cord + " Y: " + player.y_cord);
    }

    //------------ Other -----------------

    public static void showInputPlayerName() {
        System.out.print("Enter your hero name:");
    }

    public static void showPickedUpItem(Item item){
        System.out.println("\nYou found an item!\n"+ item.name + " was added to your inventory");
    }

    public static void showHitWall() {
        System.out.println("\nYou hit wall!");
        System.out.println("Enter a new direction!");
    }

    public static void showExitGame(){
        System.out.println("Thanks for playing the game.\n Bye!");
    }

    public static void showNewLine(){
        System.out.println();
    }

    public static void showEnteredBattle(Enemy enemy) {
        System.out.println("\n" + enemy.name + " blocked your way");
        System.out.println("Prepare for BATTLE!\n");
    }

    public static void showPlayerDamageInfo(Enemy enemy, int damage) {
        System.out.println("\nYou attacked with: " + damage + " attack points");
        System.out.println(enemy.name + "\'s health drops to " + enemy.health + " health points\n");
    }

    public static void showEnemyDamageInfo(Enemy enemy, Player player,int damage) {
        System.out.println(enemy.name + " damaged you with " + damage + " attackDamage");
        System.out.println("Your health changed to " + player.health + "\n");

    }

    public static void showZeroEnemyDamage(Enemy enemy) {
        System.out.println("You blocked " + enemy.name + "\'s attack");
    }

    public static void showZeroPlayerDamage(Enemy enemy) {
        System.out.println(enemy.name + " blocked your attack\n");
    }

    public static void playerLost() {
        System.out.println("You died!");
    }

    public static void showNewGameChoice() {
        System.out.print("Do you want to play again?(Yes/No): ");
    }

    public static void showDefeatedEnemy(Enemy enemy) {
        System.out.println("You killed " + enemy.name + "!");
    }

    public static void showTurn(Character fighter) {
        System.out.println(fighter.name + "\'s turn:");
    }
}