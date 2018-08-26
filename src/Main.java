import characters.Player;
import handlers.gameHandler.GameMaster;

public class Main {
    public static void main(String[] args) {
        Player player = new Player();

        GameMaster gameMaster = new GameMaster(player);

        gameMaster.playGame(gameMaster);
    }
}
