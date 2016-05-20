import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.behavior.*;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;
import ar.fiuba.tdd.tp.engine.player.Player;

import java.util.regex.Matcher;

@SuppressWarnings("CPD-START")
public class BuildPlayer {

    //actions
    private static final String PICK = "pick";
    private static final String OPEN = "open";
    private static final String HELP = "help";
    private static final String LOOK_AROUND = "look around";
    private static final String WHAT_CAN_I_DO = "what can i do with";
    private static final String DROP = "drop";
    private static final String STORE = "store";
    private static final String GOTO = "goto";
    private static final String MOVE = "move";
    private static final String PUT = "put";
    private static final String SHOW = "show";
    private static final String USE = "use";
    private static final String BREAK = "break";

    public static Player build(Game game) {

        Player player = game.getPlayer();
        player.addBehavior(LOOK_AROUND, new LookAround(game));
        player.addBehavior(PICK, new DirectActionRoom(game));
        player.addBehavior(OPEN, new DirectActionRoom(game));
        player.addBehavior(DROP, new DirectActionPlayer(game));
        player.addBehavior(STORE, getStoreAction(game));
        player.addBehavior(WHAT_CAN_I_DO, new DirectActionRoom(game));
        player.addBehavior(HELP, new Help(game));
        player.addBehavior(GOTO, new DirectActionRoom(game));
        player.addBehavior(MOVE, new DirectActionRoom(game));
        player.addBehavior(PUT, new IndirectAction(game));
        player.addBehavior(SHOW, new IndirectAction(game));
        player.addBehavior(USE, new DirectActionRoom(game));
        player.addBehavior(BREAK, new DirectActionRoom(game));

        player.setInventoryLimit(4);
        player.addItemToInventory(crateFoto());
        player.addItemToInventory(createCredencial());

        return  player;
    }

    private static ComponentContainer createCredencial() {
        //TODO reivisar comportamiento de esto

        ComponentContainer credencial = new ComponentContainer("credencial");



        return credencial;
    }

    private static ComponentInterface crateFoto() {
        return new ComponentSimple("foto");
    }

    public static Behavior getStoreAction(Game ejercicioIt2) {
        return new IndirectAction(ejercicioIt2) {
            @Override
            public String doAction(Matcher matcher, String completeMessage) {
                return super.doAction(matcher, completeMessage);
            }
        };
    }

}
