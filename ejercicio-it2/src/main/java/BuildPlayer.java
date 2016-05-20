import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.behavior.*;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;
import ar.fiuba.tdd.tp.engine.player.Player;

import java.util.regex.Matcher;

public class BuildPlayer {

    //actions
    private static final String PICK = "pick";
    private static final String OPEN = "open";
    private static final String HELP = "help";
    private static final String LOOK_AROUND = "look around";
    private static final String WHAT_CAN_I_DO = "what can i do with";
    private static final String DROP = "drop";
    private static final String STORE = "store";

    public static Player build(Game ejercicioIt2) {

        Player player = ejercicioIt2.getPlayer();
        player.addBehavior(LOOK_AROUND, new LookAround(ejercicioIt2));
        player.addBehavior(PICK, new DirectActionRoom(ejercicioIt2));
        player.addBehavior(OPEN, new DirectActionRoom(ejercicioIt2));
        player.addBehavior(DROP, new DirectActionPlayer(ejercicioIt2));
        player.addBehavior(STORE, getStoreAction(ejercicioIt2));
        player.addBehavior(WHAT_CAN_I_DO, new DirectActionRoom(ejercicioIt2));
        player.addBehavior(HELP, new Help(ejercicioIt2));

        player.setInventoryLimit(4);
        player.addItemToInventory(crateFoto());
        player.addItemToInventory(crateCredencial());

        return  player;
    }

    private static ComponentContainer crateCredencial() {
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
