package ar.fiuba.tdd.tp.engine.elements;

import ar.fiuba.tdd.tp.engine.commands.CommandExecution;
import ar.fiuba.tdd.tp.engine.commands.CommandExecutor;
import ar.fiuba.tdd.tp.engine.commands.CommandValidation;

import java.util.HashMap;
import java.util.Map;

public class Content extends Container {
    private Content container;
    private Map<String,CommandValidation> commandsValidations;
    private Map<String,CommandExecutor> commandsExecutors;

    public Content(String name) {
        this(name, 1000);
    }

    public Content(String name, Integer limit) {
        super(name, limit);
        container = null;
        commandsValidations = new HashMap<>();
        commandsExecutors = new HashMap<>();
    }

    public void setContainer(Container container) {
        this.container = (Content) container;
    }

    public Content getContainer() {
        return container;
    }

    public void addCommand(String commandName, CommandValidation commandValidation, CommandExecutor commandExecutor) {
        commandsValidations.put(commandName, commandValidation);
        commandsExecutors.put(commandName, commandExecutor);
    }

    public String doCommand(String commandName) {
        return doCommand(commandName, new String[0]);
    }

    public String doCommand(String commandName, String[] params) {
        if (commandsValidations.containsKey(commandName) && commandsExecutors.containsKey(commandName) && commandsValidations.get(commandName).convert(params)) {
            return commandsExecutors.get(commandName).execute(params);
        } else {
            return "Can't do " + commandName + " on " + name;
        }
    }

}