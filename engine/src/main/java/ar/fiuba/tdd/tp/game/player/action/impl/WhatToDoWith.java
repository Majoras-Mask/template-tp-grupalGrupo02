package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.context.GameContext;
import ar.fiuba.tdd.tp.game.player.action.Constrain;
import ar.fiuba.tdd.tp.game.player.action.OneObjectActionImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * This behavior executes the open operation on the given component
 */
public class WhatToDoWith extends OneObjectActionImpl {

    public WhatToDoWith(GameContext context) {
        super(context, "^what to do with ");
    }

    @Override
    public String execute(Component component) {
        Optional<String> options = getAttributes(component);
        if (options.isPresent()) {
            return "you can " +  options.get() + " the " + component.getName();
        }
        return "You can do nothing with the " + component.getName();
    }

    private Optional<String> getAttributes(Component component) {
        return component.getAttributes().stream().map(Attribute::getDescription).reduce(this::combine);
    }

    private String combine(String attribute1, String attribute2) {
        return attribute1 + "/" + attribute2;
    }

    @Override
    public List<Constrain> getConstrains() {
        return new ArrayList<>();
    }
}
