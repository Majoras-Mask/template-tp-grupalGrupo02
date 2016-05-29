package ar.fiuba.tdd.tp.commands;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 28/05/16.
 */
public class CommandConcrete implements Command {

    protected String command;
    protected List<Element> elements = new ArrayList<>();

    public CommandConcrete(String command) {
        this.command = command.toLowerCase();
    }

    @Override
    public boolean matches(String command) {
        return this.command.equals(command.toLowerCase());
    }

    @Override
    public void execute(String command, Context context) {
        if (matches(command)) {
            for (Element element:elements) {
                element.execute(context);
            }
        }
    }

    @Override
    public void addElement(Element element) {
        this.elements.add(element);
    }
}
