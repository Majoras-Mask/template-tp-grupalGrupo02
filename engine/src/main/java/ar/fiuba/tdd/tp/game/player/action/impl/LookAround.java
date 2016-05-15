package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.context.GameContext;
import ar.fiuba.tdd.tp.game.player.action.Constrain;
import ar.fiuba.tdd.tp.game.player.action.NoObjectAction;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LookAround implements NoObjectAction {

    private GameContext gameContext;

    public LookAround(GameContext gameContext) {
        this.gameContext = gameContext;
    }

    @Override
    public String execute() {
        Optional<String> descriptions = getDescriptions();
        if (descriptions.isPresent()) {
            return "There's a " + descriptions.get() + " in the room";
        }
        return "There's nothing here...";

    }

    @Override
    public Boolean canExecute(String action) {
        return "look around".equals(action);
    }

    @Override
    public String doExecute(String action) {
        return this.execute();
    }

    @Override
    public List<Constrain> getConstrains() {
        return new ArrayList<>();
    }

    private Optional<String> getDescriptions() {
        return this.gameContext.getComponents().stream().map(Component::getName).reduce(this::combine);
    }

    private String combine(String name1, String name2) {
        return name1 + " and a " + name2;
    }

    public GameContext getGameContext() {
        return gameContext;
    }

    public void setGameContext(GameContext gameContext) {
        this.gameContext = gameContext;
    }
}
