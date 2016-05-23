package ar.fiuba.tdd.tp.engine.player.action.io;

import ar.fiuba.tdd.tp.engine.component.Component;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class ActionRequest {

    private final List<Component> arguments;

    public ActionRequest(List<Component> arguments) {
        this.arguments = requireNonNull(arguments);
    }

    public List<Component> getArguments() {
        return arguments;
    }
}
