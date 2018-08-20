import characters.Player;
import characters.Character;
import handlers.gameHandler.GameMaster;

public class Main {
    public static void main(String[] args) {
        Character player = new Player();

        GameMaster gameMaster = new GameMaster((Player) player);

        gameMaster.playGame(gameMaster);
    }
}
