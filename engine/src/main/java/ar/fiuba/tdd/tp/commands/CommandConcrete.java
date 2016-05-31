package ar.fiuba.tdd.tp.commands;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.conditions.Condition;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 28/05/16.
 */
public class CommandConcrete implements Command {

    protected String command;
    protected Condition condition;
    protected List<Element> elements = new ArrayList<>();
    protected String response;

    public CommandConcrete(String command, String response) {
        this.command = command.toLowerCase();
        this.response = response;
    }

    @Override
    public boolean matches(String command, Context context) {
        return this.command.equals(command.toLowerCase()) && condition.check(context);
    }

    @Override
    public String execute(String command, Context context) {
        if (matches(command,context)) {
            for (Element element:elements) {
                element.execute(context);
            }
        }
        return response;
    }

    @Override
    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    @Override
    public void addElement(Element element) {
        this.elements.add(element);
    }
}
