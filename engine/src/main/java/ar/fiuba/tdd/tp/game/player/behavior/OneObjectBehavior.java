package ar.fiuba.tdd.tp.game.player.behavior;

import ar.fiuba.tdd.tp.game.component.Component;

/**
 * Many behaviors require an object to operate upon: TAKE THE KEY, READ THE BOOK.
 * These are called "transitive" behaviors, and the object that a transitive behavior
 * operates upon is called the behavior's 'direct object.'
 */
public interface OneObjectBehavior extends Behavior {

    String execute(Component component);

}
