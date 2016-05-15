package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.action.Constrain;
import ar.fiuba.tdd.tp.game.player.action.NoObjectAction;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class LookAround extends NoObjectAction {

    public LookAround(Context context) {
        super(context, "^look around ");
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
    public List<Constrain> getConstrains() {
        return new ArrayList<>();
    }

    private Optional<String> getDescriptions() {
        return this.context.getComponents().stream().map(Component::getName).reduce(this::combine);
    }

    private String combine(String name1, String name2) {
        return name1 + " and a " + name2;
    }

}
