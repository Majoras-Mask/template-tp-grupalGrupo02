package ar.fiuba.tdd.tp.game.test;

import ar.fiuba.tdd.tp.game.Game;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.ComponentImpl;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.pick.PickableImpl;
import ar.fiuba.tdd.tp.game.context.GameContext;
import ar.fiuba.tdd.tp.game.player.Inventory;
import ar.fiuba.tdd.tp.game.player.PlayerImpl;
import ar.fiuba.tdd.tp.game.player.action.Action;
import ar.fiuba.tdd.tp.game.player.action.impl.LookAround;
import ar.fiuba.tdd.tp.game.player.action.impl.Pick;
import ar.fiuba.tdd.tp.game.player.action.impl.WhatToDoWith;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FetchQuest implements Game {

    private final PlayerImpl player;

    public FetchQuest() {
        final GameContext gameContext = createGameContext();
        this.player = getPlayer(gameContext);
    }

    private static PlayerImpl getPlayer(GameContext gameContext) {
        final Inventory inventory = new Inventory(new ArrayList<>(), 10);
        return new PlayerImpl(createActionResolver(inventory, gameContext));
    }

    private static ActionResolver createActionResolver(Inventory inventory, GameContext gameContext) {
        final Set<Action> actions = new HashSet<>();

        final LookAround lookAround = new LookAround(gameContext);
        final WhatToDoWith whatToDoWith = new WhatToDoWith(gameContext);
        final Pick pick = new Pick(inventory, gameContext);

        actions.add(lookAround);
        actions.add(whatToDoWith);
        actions.add(pick);

        return new ActionResolver(actions);
    }

    private static Component createStick(String name) {
        List<Attribute> attributes = new ArrayList<>();
        Attribute pickable = new PickableImpl();
        attributes.add(pickable);

        return new ComponentImpl(name, attributes);
    }

    private static GameContext createGameContext() {
        List<Component> roomComponents = new ArrayList<>();
        roomComponents.add(createStick("stick1"));
        roomComponents.add(createStick("stick2"));
        return new GameContext("room", roomComponents);
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
    public Boolean checkIfGameIsFinished() {
        return Boolean.FALSE;
    }

    @Override
    public String help() {
        return "GAME COMMANDS:\n open X, close X, look around, consume X, store X in Y, what can i do with X, talk X, pick X";
    }
}
