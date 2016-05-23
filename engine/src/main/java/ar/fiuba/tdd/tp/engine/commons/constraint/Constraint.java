package ar.fiuba.tdd.tp.engine.commons.constraint;

import ar.fiuba.tdd.tp.engine.player.action.ActionDecider;

/**
 * A Constraint is what blocks an {@link ActionDecider} from happening.
 */
public interface Constraint {

    Boolean isSatisfied();

}
