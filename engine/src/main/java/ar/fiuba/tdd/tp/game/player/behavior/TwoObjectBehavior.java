package ar.fiuba.tdd.tp.game.player.behavior;

import ar.fiuba.tdd.tp.game.component.Component;

/**
 * Some behaviors take not one but two objects: PUT BOOK ON SHELF, UNLOCK DOOR WITH KEY.
 * Note that we're not talking about commands that list multiple direct objects,
 * such as TAKE BOOK AND KEY: those are still one-object behaviors in the sense we're talking about here,
 * because there's only one role that an object can play with the behavior. If a list of objects is specified
 * for the single role of a one-object behavior, it's just saying that the behavior should be applied in turn
 * to each object in the list, with each object serving the same role.
 * Instead, what we're talking about here is behaviors that have two objects in different roles.
 * In UNLOCK DOOR WITH KEY, the key is the "instrument" or "agent" of the action, but the door
 * is what's being unlocked; we're not unlocking the key, obviously.
 * The door is called the "direct object," because it's the object that's being directly affected by the action.
 * The key is called the "indirect object," because it's being used in the course of performing the action but
 * isn't the main focus of the action.
 */
public interface TwoObjectBehavior extends Behavior {

    String execute(Component directComponent, Component indirectComponent);

}
