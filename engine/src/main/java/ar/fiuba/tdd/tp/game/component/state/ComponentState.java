package ar.fiuba.tdd.tp.game.component.state;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.action.ActionDecider;

/**
 * Represents some state that may have a {@link Component}.
 * A state can be altered by some {@link ActionDecider}, for example: a door can change it's
 * state (from close to open) when the Action Open is executed
 */
public interface ComponentState {

    void changeValue(String value);

    String getValue();
}
