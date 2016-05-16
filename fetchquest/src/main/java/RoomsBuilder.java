import ar.fiuba.tdd.tp.game.commons.condition.Condition;
import ar.fiuba.tdd.tp.game.commons.condition.have.HaveType;
import ar.fiuba.tdd.tp.game.commons.condition.have.PlayerHave;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.ComponentImpl;
import ar.fiuba.tdd.tp.game.component.state.ComponentState;
import ar.fiuba.tdd.tp.game.component.state.PickState;
import ar.fiuba.tdd.tp.game.mission.Mission;
import ar.fiuba.tdd.tp.game.mission.MissionImpl;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.scenario.context.Context;
import ar.fiuba.tdd.tp.game.scenario.context.ContextImpl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RoomsBuilder {
    private static final String WINNING_STICK_NAME = "stick1";
    private static final String ROOM_NAME = "room";
    private static final String LOSING_STICK_NAME = "stick2";
    private static final String WON_MESSAGE = "You picked the right stick, you have won the game!!!";
    private static final String LOST_MESSAGE = "You picked the wrong stick, so you lost the game.";

    public static final Context roomContext() {
        final List<Component> roomComponents = new ArrayList<>();
        roomComponents.add(createStick(WINNING_STICK_NAME));
        roomComponents.add(createStick(LOSING_STICK_NAME));
        return (new ContextImpl(ROOM_NAME, roomComponents));
    }

    private static Component createStick(String name) {
        List<ComponentState> states = new ArrayList<>();
        states.add(new PickState(Boolean.FALSE, new HashMap<>()));
        return new ComponentImpl(name, states);
    }

    public static Mission createMission(Player player) {
        List<Condition> winConditions = new ArrayList<>();
        List<Condition> loseConditions = new ArrayList<>();

        Condition winCondition = new PlayerHave(player, WINNING_STICK_NAME, HaveType.HAVE);
        Condition loseCondition = new PlayerHave(player, LOSING_STICK_NAME, HaveType.HAVE);

        winConditions.add(winCondition);
        loseConditions.add(loseCondition);

        return new MissionImpl(winConditions, loseConditions) {
            @Override
            public String finishedMessage() {
                if (isFailed()) {
                    return LOST_MESSAGE;
                }
                return WON_MESSAGE;
            }
        };
    }
}
