package ar.fiuba.tdd.tp.game.player;

import ar.fiuba.tdd.tp.game.player.behavior.Behavior;
import ar.fiuba.tdd.tp.game.player.behavior.impl.FallBackBehavior;

import java.util.Optional;
import java.util.Set;

/**
 * Finds the {@link Behavior} that can handle the input command.
 * In case that no behavior can handles that command, a fallback response
 * is retrieved.
 */
public class BehaviorResolver {

    private static final Behavior FALL_BACK_BEHAVIOR = new FallBackBehavior();
    private final Set<Behavior> behaviors;

    public BehaviorResolver(Set<Behavior> behaviors) {
        this.behaviors = behaviors;
    }

    public Behavior findBehavior(String command) {
        Optional<Behavior> first = this.behaviors.stream()
                .filter(behavior -> behavior.canExecute(command))
                .findFirst();

        if (first.isPresent()) {
            return first.get();
        }

        return FALL_BACK_BEHAVIOR;
    }
}
