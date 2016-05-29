package ar.fiuba.tdd.tp.commands;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;

/**
 * Created by kevin on 28/05/16.
 */
public interface Command {
    boolean matches(String command);

    void execute(Context context);

    void addElement(Element element);
}
