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
    private static final String KEY_NAME = "key";
    private static final String DOOR_NAME = "door";
    private static final String ROOM_NAME = "room";
    private static final String WINNING_ROOM_NAME = "room2";
    private static final String WON_MESSAGE = "You picked the right stick, you have won the game!!!";
    private static final String LOST_MESSAGE = "Can't lose";

    public static final Context roomContext() {
        final List<Component> roomFirst = new ArrayList<>();
        final List<Component> roomSecond = new ArrayList<>();

        Component key = createKey(KEY_NAME);

        roomFirst.add(key);
        roomFirst.add(createDoor(DOOR_NAME, key, roomSecond));
        return (new ContextImpl(ROOM_NAME, roomFirst));
    }

    private static Component createDoor(String doorName, Component key, List<Component> roomSecond) {
        //TODO ver como hacer para que la puerta se fije si tiene la llave si la tiene pasa al room segundo
        return null;
    }

    private static Component createKey(String name) {
        List<ComponentState> states = new ArrayList<>();
        states.add(new PickState(Boolean.FALSE, new HashMap<>()));
        return new ComponentImpl(name, states);
    }

    public static Mission createMission(Player player) {
        List<Condition> winConditions = new ArrayList<>();

        //TODO agregar condicion de estar....
        Condition winCondition = new PlayerHave(player, WINNING_ROOM_NAME, HaveType.HAVE);

        winConditions.add(winCondition);

        return new MissionImpl(winConditions, null) {
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
