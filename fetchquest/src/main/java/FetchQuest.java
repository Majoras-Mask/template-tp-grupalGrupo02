import ar.fiuba.tdd.tp.engine.GameImpl;
import ar.fiuba.tdd.tp.engine.player.Player;

public class FetchQuest extends GameImpl {

    public FetchQuest(Player player) {
        super(player);
    }

    @Override
    public String getName() {
        return "Fetch Quest";
    }

    @Override
    public String help() {
        return "Commands should be here.";
    }

    @Override
    public String getWelcomeMessage() {
        return "Welcome to Fetch Quest game, your mission is to pick the marvelous stick to win the game.";
    }
}
