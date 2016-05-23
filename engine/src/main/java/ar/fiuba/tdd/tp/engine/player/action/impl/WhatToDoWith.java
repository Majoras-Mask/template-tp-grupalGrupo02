package ar.fiuba.tdd.tp.engine.player.action.impl;

import ar.fiuba.tdd.tp.engine.component.Component;

import java.util.Set;

/*
 * This {@link Action} executes the open operation on the given {@link Component}
 */
public class WhatToDoWith implements Action {

    private final Component component;

    public WhatToDoWith(Component component) {
        this.component = component;
    }

    @Override
    public String doAction() {
        Set<String> actions = component.getSupportedActions();
        if (actions != null) {
            StringBuffer message = new StringBuffer();
            message.append("You can ");
            for (String action : actions) {
                message.append(action).append("/");
            }
            message.append(" the ").append(component.getName());
            return message.toString();
        }
        return "You can do nothing with the " + component.getName();
    }
}
