package ar.fiuba.tdd.tp.game.commons.constraint;

import ar.fiuba.tdd.tp.game.player.action.Action;

/**
 * A Constraint is what blocks an {@link Action} from happening.
 */
public interface Constraint {

    Boolean isSatisfied();

}
