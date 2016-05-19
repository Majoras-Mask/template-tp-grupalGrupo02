import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.rules.Rule;

public class GameRules implements Rule {
    private static final String SHEEP_CABBAGE_TOGETHER = "You can't the sheep would eat that delicious cabbage.";
    private static final String WOLF_SHEEP_TOGETHER = "You can't, the wolf is looking hungrily at the sheep";
    private static final String ERROR = "This shouldn't be happening";

    static Game game;
    static ComponentInterface wolf;
    static ComponentInterface sheep;
    static ComponentInterface cabbage;
    static ComponentContainer northRiver;

    public boolean satisfiesRule() {
        if (sheepAndCabbageTogether() || wolfAndSheepTogether()) {
            return false;
        }
        return true;
    }

    private static boolean sheepAndCabbageTogether() {
        return (game.getPlayer().seesItemInRoom(sheep) && game.getPlayer().seesItemInRoom(cabbage));
    }

    private static boolean wolfAndSheepTogether() {
        return (game.getPlayer().seesItemInRoom(wolf) && game.getPlayer().seesItemInRoom(sheep));
    }

    public String reasonsOfRuleFail() {
        if (sheepAndCabbageTogether()) {
            return SHEEP_CABBAGE_TOGETHER;
        }
        if (wolfAndSheepTogether()) {
            return WOLF_SHEEP_TOGETHER;
        }
        return ERROR;
    }

    public static boolean wonTheGame() {
        if (northRiver.hasItem(wolf) && northRiver.hasItem(sheep) && northRiver.hasItem(cabbage)) {
            return true;
        }
        return false;
    }

    public static void setGame(Game game) {
        GameRules.game = game;
    }

    public static void setWolf(ComponentInterface wolf) {
        GameRules.wolf = wolf;
    }

    public static void setSheep(ComponentInterface sheep) {
        GameRules.sheep = sheep;
    }

    public static void setCabbage(ComponentInterface cabbage) {
        GameRules.cabbage = cabbage;
    }

    public static void setNorthRiver(ComponentContainer northRiver) {
        GameRules.northRiver = northRiver;
    }
}
