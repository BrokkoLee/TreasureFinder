package characters;

public abstract class Character {
    public String name;
    //TODO add charachter a basic weapon(hand);
    public  int health;
    public int damage;
    public int blockDamage;
    public int attackDamage;
    public int x_cord;
    public int y_cord;

    public void attack(Character attacker, Character defender){
        int damage = attacker.attackDamage - defender.blockDamage;
        if (damage < 0) damage = 0;
        defender.health -= damage;
    }

}
