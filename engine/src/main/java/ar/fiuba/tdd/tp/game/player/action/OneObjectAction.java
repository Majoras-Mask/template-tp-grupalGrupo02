package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.component.Component;

/**
 * Many actions require an object to operate upon: TAKE THE KEY, READ THE BOOK.
 * These are called "transitive" actions, and the object that a transitive action
 * operates upon is called the action's 'direct object.'
 */
public interface OneObjectAction extends Action {

    String execute(Component component);

}
