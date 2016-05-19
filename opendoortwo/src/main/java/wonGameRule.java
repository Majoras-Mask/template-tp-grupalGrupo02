import ar.fiuba.tdd.tp.engine.Game;

public class WonGameRule {

    static Game game;
    static String winningRoomName;

    public static boolean wonTheGame() {
        return game.getPlayer().currentRoomName().equals(winningRoomName);
    }

    public static void setWinningRoomName(String winningRoomName) {
        WonGameRule.winningRoomName = winningRoomName;
    }

    public static void setGame(Game game) {
        WonGameRule.game = game;
    }
}
