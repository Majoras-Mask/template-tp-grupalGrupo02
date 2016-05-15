package ar.fiuba.tdd.tp.game.component.attribute;

import ar.fiuba.tdd.tp.game.component.Component;

public interface Lockable extends Openable {

    Boolean isLocked();

    Boolean canUnlock(Component component);

    Boolean unlock(Component component);

}
