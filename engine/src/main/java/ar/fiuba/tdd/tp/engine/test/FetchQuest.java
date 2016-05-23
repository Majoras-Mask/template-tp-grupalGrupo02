package ar.fiuba.tdd.tp.engine.test;

import ar.fiuba.tdd.tp.engine.GameImpl;
import ar.fiuba.tdd.tp.engine.commons.condition.Condition;
import ar.fiuba.tdd.tp.engine.commons.condition.have.PlayerInventoryNotFull;
import ar.fiuba.tdd.tp.engine.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.engine.commons.constraint.ConstraintImpl;
import ar.fiuba.tdd.tp.engine.player.Player;
import ar.fiuba.tdd.tp.engine.player.PlayerImpl;
import ar.fiuba.tdd.tp.engine.player.action.ActionDecider;
import ar.fiuba.tdd.tp.engine.player.action.NoObjectActionDecider;
import ar.fiuba.tdd.tp.engine.player.action.OneObjectActionDecider;
import ar.fiuba.tdd.tp.engine.player.action.impl.Action;
import ar.fiuba.tdd.tp.engine.player.action.impl.LookAround;
import ar.fiuba.tdd.tp.engine.player.action.resolver.ActionResolver;

import java.util.ArrayList;
import java.util.List;

public class FetchQuest extends GameImpl {
    private static final String GAME_NAME = "Fetch Quest";
    private static final String GAME_HELP = "Fetch Quest Help commands";
    private static final int INVENTORY_LIMIT = 10;
    private static final String LOOK_AROUND = "look around";
    private static final String PICK = "pick";

    public FetchQuest(Player player) {
        this.setGameName(GAME_NAME);
        this.setHelp(GAME_HELP);
        this.setPlayer(createPlayer());
    }

    private Player createPlayer() {
        Player player = new PlayerImpl(INVENTORY_LIMIT);
        ActionResolver actionResolver = new ActionResolver();
        List<Action> actions = new ArrayList<>();
        Constraint constraints;
        List<Condition> conditions = new ArrayList<>();
        ActionDecider actionDecider;


        actions.add(new LookAround(player));
        actionDecider = new NoObjectActionDecider(LOOK_AROUND,actions, null);
        actions.clear();
        actionResolver.addActionDecider(actionDecider);


        conditions.add(new PlayerInventoryNotFull(player));
        constraints = new ConstraintImpl(conditions);
        actionDecider = new OneObjectActionDecider(PICK, constraints, player);
        conditions.clear();
        actionResolver.addActionDecider(actionDecider);

        player.setActionResolver(actionResolver);

        return player;
    }

}
