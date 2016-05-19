import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.rules.Rule;

public class WonGameRule implements Rule {

    static Game game;
    static String winningRoomName;

    @Override
    public boolean satisfiesRule() {
        return game.getPlayer().currentRoomName().equals(winningRoomName);
    }

    @Override
    public String reasonsOfRuleFail() {
        return null;
    }

    public static void setGame(Game game) {
        WonGameRule.game = game;
    }

    public static void setWinningRoomName(String winningRoomName) {
        WonGameRule.winningRoomName = winningRoomName;
    }
}
