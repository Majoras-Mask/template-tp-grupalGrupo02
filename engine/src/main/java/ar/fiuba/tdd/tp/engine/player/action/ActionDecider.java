package ar.fiuba.tdd.tp.engine.player.action;

import ar.fiuba.tdd.tp.engine.component.Component;

/**
 * Interface that represents actions that a character can do. This actions can take in
 * consideration one {@link Component}, two or none.
 */
public interface ActionDecider {

    Boolean canExecute(String action);

    String execute(String action);

    Boolean satisfiesActionConstraints();

}
