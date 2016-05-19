import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.behavior.DirectAction;
import ar.fiuba.tdd.tp.engine.behavior.LookAround;
import ar.fiuba.tdd.tp.engine.behavior.NormalPick;
import ar.fiuba.tdd.tp.engine.behavior.WhatCanIDo;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

public class BuildLevel {
    private static final String STICK_NAME = "stick";
    private static final String ROOM_NAME = "room";

    private static final String LOOK_AROUND = "look around";
    private static final String PICK = "pick";
    private static final String WHAT_CAN_I_DO_WITH = "what can i do with";
    private static ComponentInterface winningCondition;

    public static void build(Game fetchQuest) {

        fetchQuest.getPlayer().addBehavior(LOOK_AROUND, new LookAround(fetchQuest));
        fetchQuest.getPlayer().addBehavior(PICK, new DirectAction(fetchQuest));
        fetchQuest.getPlayer().addBehavior(WHAT_CAN_I_DO_WITH, new DirectAction(fetchQuest));

        ComponentInterface stick = new ComponentSimple(STICK_NAME);
        stick.addBehavior(PICK, new NormalPick(fetchQuest, stick));
        stick.addBehavior(WHAT_CAN_I_DO_WITH, new WhatCanIDo(stick));

        ComponentContainer room = new ComponentContainer(ROOM_NAME);
        room.addItem(stick);
        fetchQuest.getPlayer().setRoom(room);

        winningCondition = stick;
    }

    public static ComponentInterface getWinningCondition() {
        return winningCondition;
    }
}
