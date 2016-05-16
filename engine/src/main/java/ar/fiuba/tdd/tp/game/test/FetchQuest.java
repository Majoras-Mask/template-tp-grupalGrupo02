package ar.fiuba.tdd.tp.game.test;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.commons.condition.Condition;
import ar.fiuba.tdd.tp.game.commons.condition.have.HaveType;
import ar.fiuba.tdd.tp.game.commons.condition.have.PlayerHave;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.ComponentImpl;
import ar.fiuba.tdd.tp.game.component.state.ComponentState;
import ar.fiuba.tdd.tp.game.component.state.PickState;
import ar.fiuba.tdd.tp.game.mission.Mission;
import ar.fiuba.tdd.tp.game.mission.MissionImpl;
import ar.fiuba.tdd.tp.game.player.Inventory;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.PlayerImpl;
import ar.fiuba.tdd.tp.game.player.action.Action;
import ar.fiuba.tdd.tp.game.player.action.impl.LookAround;
import ar.fiuba.tdd.tp.game.player.action.impl.Pick;
import ar.fiuba.tdd.tp.game.player.action.impl.WhatToDoWith;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;
import ar.fiuba.tdd.tp.game.scenario.context.Context;
import ar.fiuba.tdd.tp.game.scenario.context.ContextImpl;

import java.util.*;

public class FetchQuest implements Game {

    private final Player player;
    private final Mission mission;

    public FetchQuest() {
        final List<Component> roomComponents = createRoomComponents();
        final Context playerContext = new ContextImpl("room", roomComponents);

        final Inventory inventory = new Inventory(new ArrayList<>(), 10);
        this.player = new PlayerImpl(createActionResolver(new Inventory(new ArrayList<>(), 10), playerContext), inventory);
        this.mission = createMission();
    }

    private Mission createMission() {
        List<Condition> winConditions = new ArrayList<>();
        List<Condition> loseConditions = new ArrayList<>();

        Condition winCondition = new PlayerHave(this.player, "stick1", HaveType.HAVE);
        Condition loseCondition = new PlayerHave(this.player, "stick2", HaveType.NOT_HAVE);

        winConditions.add(winCondition);
        loseConditions.add(loseCondition);

        return new MissionImpl(winConditions, loseConditions);
    }

    private static ActionResolver createActionResolver(Inventory inventory, Context context) {
        final Set<Action> actions = new HashSet<>();

        final LookAround lookAround = new LookAround(context);
        final WhatToDoWith whatToDoWith = new WhatToDoWith(context);
        final Pick pick = new Pick(inventory, context);

        actions.add(lookAround);
        actions.add(whatToDoWith);
        actions.add(pick);

        return new ActionResolver(actions);
    }

    private static Component createStick(String name) {
        List<ComponentState> states = new ArrayList<>();
        states.add(new PickState(Boolean.FALSE, new HashMap<>()));
        return new ComponentImpl(name, states);
    }

    private static List<Component> createRoomComponents() {
        List<Component> roomComponents = new ArrayList<>();
        roomComponents.add(createStick("stick1"));
        roomComponents.add(createStick("stick2"));
        return roomComponents;
    }

    @Override
    public String doCommand(String command) {
        return this.player.doCommand(command);
    }

    @Override
    public String getName() {
        return "Fetch Quest";
    }

    @Override
    public Boolean isWon() {
        return this.mission.isAccomplished();
    }

    @Override
    public Boolean isLost() {
        return this.mission.isFailed();
    }

    @Override
    public String help() {
        return "GAME COMMANDS:\n open X, close X, look around, consume X, store X in Y, what can i do with X, talk X, pick X";
    }
}
