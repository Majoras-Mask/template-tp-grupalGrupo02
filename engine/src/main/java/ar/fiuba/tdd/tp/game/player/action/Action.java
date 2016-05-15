package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.component.Component;

import java.util.List;

/**
 * Interface that represents actions that a character can do. This actions can take in
 * consideration one {@link Component}, two or none.
 */
public interface Action {

    Boolean canExecute(String action);

    String doExecute(String action);

    List<Constrain> getConstrains();

}
