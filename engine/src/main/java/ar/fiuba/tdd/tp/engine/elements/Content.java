package ar.fiuba.tdd.tp.engine.elements;

import ar.fiuba.tdd.tp.engine.commands.content.CommandExecutor;
import ar.fiuba.tdd.tp.engine.commands.content.CommandValidator;

import java.util.HashMap;
import java.util.Map;

public class Content extends Container {
    private Content container;
    private Map<String,CommandValidator> commandsValidators;
    private Map<String,CommandExecutor> commandsExecutors;

    public Content(String name) {
        this(name, 1000);
    }

    public Content(String name, Integer limit) {
        super(name, limit);
        container = null;
        commandsValidators = new HashMap<>();
        commandsExecutors = new HashMap<>();
    }

    public void setContainer(Container container) {
        this.container = (Content) container;
    }

    public Content getContainer() {
        return container;
    }

    public void addCommand(String commandName, CommandValidator commandValidator, CommandExecutor commandExecutor) {
        commandsValidators.put(commandName, commandValidator);
        commandsExecutors.put(commandName, commandExecutor);
    }

    public String doCommand(String commandName) {
        return doCommand(commandName, new String[0]);
    }

    public String doCommand(String commandName, String[] params) {
        if (commandsValidators.containsKey(commandName)
                && commandsExecutors.containsKey(commandName)
                && commandsValidators.get(commandName).check(params)) {
            return commandsExecutors.get(commandName).execute(params);
        } else {
            return "Can't do " + commandName + " on " + name;
        }
    }

}