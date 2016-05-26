import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.commons.condition.Condition;
import ar.fiuba.tdd.tp.engine.commons.condition.have.PlayerInventoryNotFull;
import ar.fiuba.tdd.tp.engine.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.engine.commons.constraint.ConstraintImpl;
import ar.fiuba.tdd.tp.engine.component.Component;
import ar.fiuba.tdd.tp.engine.component.ComponentImpl;
import ar.fiuba.tdd.tp.engine.component.state.ComponentState;
import ar.fiuba.tdd.tp.engine.player.Player;
import ar.fiuba.tdd.tp.engine.player.PlayerImpl;
import ar.fiuba.tdd.tp.engine.player.action.NoObjectActionDecider;
import ar.fiuba.tdd.tp.engine.player.action.OneObjectActionDecider;
import ar.fiuba.tdd.tp.engine.player.action.impl.Action;
import ar.fiuba.tdd.tp.engine.player.action.impl.LookAround;
import ar.fiuba.tdd.tp.engine.scenario.context.Context;
import ar.fiuba.tdd.tp.engine.scenario.context.ContextImpl;


import java.util.ArrayList;
import java.util.List;

public class FetchBuilder implements GameBuilder {
    private static final String STICK_NAME = "stick";
    private static final String ROOM_NAME = "room";
    private static final String LOOK_AROUND = "look around";
    private static final String PICK = "pick";

    @Override
    public Game build() {
        Player player = createPlayer();
        player.putInRoom(initialRoom());
        return new FetchQuest(player);
    }

    private Context initialRoom() {
        List<Component> components = new ArrayList<>();
        List<ComponentState> states = new ArrayList<>();

        components.add(new ComponentImpl(STICK_NAME, states));

        return new ContextImpl(ROOM_NAME, components);
    }

    private Player createPlayer() {
        Player player = new PlayerImpl();

        List<Action> actionsLookAround = new ArrayList<>();
        actionsLookAround.add(new LookAround(player));
        player.addAction(new NoObjectActionDecider(LOOK_AROUND, actionsLookAround));

        List<Condition> conditionsPick = new ArrayList<>();
        conditionsPick.add(new PlayerInventoryNotFull(player));
        Constraint constraints = new ConstraintImpl(conditionsPick);
        player.addAction(new OneObjectActionDecider(PICK, constraints, player));

        return player;
    }
}
