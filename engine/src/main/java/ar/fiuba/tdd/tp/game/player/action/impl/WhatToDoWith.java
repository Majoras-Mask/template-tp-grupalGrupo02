package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.OneObjectAction;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * This {@link Action} executes the open operation on the given {@link Component}
 */
public class WhatToDoWith extends OneObjectAction {

    public WhatToDoWith(Player player) {
        super(player, "^what to do with ");
    }

    @Override
    public String doExecute(Component component) {
        Optional<String> options = getActions(component);
        if (options.isPresent()) {
            return "you can " +  options.get() + " the " + component.getName();
        }
        return "You can do nothing with the " + component.getName();
    }

    private Optional<String> getActions(Component component) {
        return component.getSupportedActions().stream().map(Enum::toString).reduce(this::combine);
    }

    private String combine(String attribute1, String attribute2) {
        return attribute1 + "/" + attribute2;
    }

    @Override
    public List<Constraint> getConstrains() {
        return new ArrayList<>();
    }
}
