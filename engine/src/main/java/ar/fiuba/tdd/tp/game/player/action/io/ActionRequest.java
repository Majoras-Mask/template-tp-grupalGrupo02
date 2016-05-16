package ar.fiuba.tdd.tp.game.player.action.io;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.action.ActionType;

import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.requireNonNull;

public class ActionRequest {

    private final ActionType type;
    private final List<Component> arguments;

    public ActionRequest(ActionType type) {
        this(type, new ArrayList<>());
    }

    public ActionRequest(ActionType type, List<Component> arguments) {
        this.type = requireNonNull(type);
        this.arguments = requireNonNull(arguments);
    }

    public ActionType getType() {
        return type;
    }

    public List<Component> getArguments() {
        return arguments;
    }
}
