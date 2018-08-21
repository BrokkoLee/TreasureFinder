package characters;

public abstract class Character {
    public String name;
    public int health;
    public int blockDamage;
    public int x_cord = 1;
    public int y_cord = 1;

    public abstract void attackPhase(Enemy enemy, Player player);
}
