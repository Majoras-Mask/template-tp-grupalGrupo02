package ar.fiuba.tdd.tp.game.commons.condition.lock;

import ar.fiuba.tdd.tp.game.commons.condition.Condition;
import ar.fiuba.tdd.tp.game.component.state.LockState;

/**
 * A lock condition returns true when:
 * -If {@link LockType} is LOCK, and the {@link LockState} is locked
 * -If {@link LockType} is UNLOCK, and the {@link LockState} is not locked
 * In any other case, it returns false.
 */
public class LockCondition implements Condition {

    private final LockType type;
    private final LockState lockable;

    public LockCondition(LockType type, LockState lockable) {
        this.type = type;
        this.lockable = lockable;
    }

    @Override
    public Boolean accomplished() {
        return this.type.getValue().equals(this.lockable.isLocked());
    }

}
