package ar.fiuba.tdd.tp.commands;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.conditions.Condition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by kevin on 28/05/16.
 */
public class CommandConcrete implements Command {

    protected String command;
    protected HashMap<Condition, String> responses = new HashMap<>();
    protected HashMap<Condition,Element> actions = new HashMap<>();

    public CommandConcrete(String command) {
        this.command = command.toLowerCase();
    }

    @Override
    public boolean matches(String command) {
        return this.command.equals(command.toLowerCase());
    }

    @Override
    public String execute(String command, Context context) {
        if (!matches(command)) {
            return "Can't do it";
        }
        for (Condition condition : actions.keySet()) {
            if (condition.check(context)) {
                actions.get(condition).execute(context);
                return responses.get(condition);
            }
        }

        return "Can't do it.";
    }

    @Override
    public void setCondition(Condition condition, Element toExecute, String response) {
        this.actions.put(condition, toExecute);
        this.responses.put(condition, response);
    }

}
