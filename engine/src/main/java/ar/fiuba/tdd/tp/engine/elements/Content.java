package ar.fiuba.tdd.tp.engine.elements;

import ar.fiuba.tdd.tp.engine.commands.VoidToString;
import ar.fiuba.tdd.tp.engine.commands.VoidToBoolean;

import java.util.HashMap;
import java.util.Map;

public class Content extends Container {
    private Content container;
    private Map<String,VoidToBoolean> commandsValidations;
    private Map<String,VoidToString> commandsExecutions;

    public Content(String name) {
        super(name);
        container = null;
        commandsValidations = new HashMap<>();
        commandsExecutions = new HashMap<>();
    }

    public void setContainer(Container container) {
        this.container = (Content) container;
    }

    public Content getContainer() {
        return container;
    }

    public void addCommand(String commandName, VoidToBoolean commandValidation, VoidToString commandExecution) {
        commandsValidations.put(commandName, commandValidation);
        commandsExecutions.put(commandName, commandExecution);
    }

    public String doCommand(String commandName) {
        if (commandsValidations.containsKey(commandName) && commandsExecutions.containsKey(commandName) && commandsValidations.get(commandName).convert()) {
            return commandsExecutions.get(commandName).execute();
        } else {
            return "Can't do " + commandName + " on " + name;
        }
    }

}