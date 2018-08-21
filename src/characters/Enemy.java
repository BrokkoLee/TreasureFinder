package characters;

import handlers.ioHandler.OutputHandler;

public class Enemy extends Character{
    public int damage;
    public int tier;

    @Override
    public void attackPhase(Enemy enemy, Player player) {
        OutputHandler.showTurn(enemy);

        int damage = this.damage - player.blockDamage;

        if (damage > 0) {
            player.health -= damage;
            OutputHandler.showEnemyDamageInfo(enemy, player, damage);
        } else {
            OutputHandler.showZeroEnemyDamage(enemy);
        }
    }
}
