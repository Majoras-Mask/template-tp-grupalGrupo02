package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditionelements.ConditionElement;

/**
 * Created by kevin on 28/05/16.
 */
public interface Game {

    void addObject(ObjectInterface object);

    void addCommand(Command command);

    void executeCommand(String playerName, String commandString);

    void update();

}
