package ar.fiuba.tdd.tp.game.commons.constraint;

import ar.fiuba.tdd.tp.game.player.action.ActionDecider;

/**
 * A Constraint is what blocks an {@link ActionDecider} from happening.
 */
public interface Constraint {

    Boolean isSatisfied();

}
