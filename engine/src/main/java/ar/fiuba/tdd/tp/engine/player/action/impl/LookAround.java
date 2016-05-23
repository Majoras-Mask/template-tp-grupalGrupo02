package ar.fiuba.tdd.tp.engine.player.action.impl;

import ar.fiuba.tdd.tp.engine.component.Component;
import ar.fiuba.tdd.tp.engine.player.Player;

import java.util.Optional;

public class LookAround implements Action {

    Player player;

    public LookAround(Player player) {
        this.player = player;
    }

    private Optional<String> getDescriptions() {
        return this.player.getCurrentContext().getComponents().stream().map(Component::getName).reduce(this::combine);
    }

    private String combine(String name1, String name2) {
        return name1 + " and a " + name2;
    }

    @Override
    public String doAction() {
        Optional<String> descriptions = getDescriptions();
        if (descriptions.isPresent()) {
            return "There's a " + descriptions.get() + " in the room";
        }
        return "There's nothing here...";
    }
}
