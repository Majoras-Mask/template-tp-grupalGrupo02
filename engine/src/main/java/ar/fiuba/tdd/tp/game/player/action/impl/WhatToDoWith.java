package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.OneObjectAction;

import java.util.List;
import java.util.Set;

/*
 * This {@link Action} executes the open operation on the given {@link Component}
 */
public class WhatToDoWith extends OneObjectAction {

    public WhatToDoWith(Player player, String commandName) {
        super(player, "^" + commandName + " ", commandName);
    }

    @Override
    public String doExecute(Component component) {
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
