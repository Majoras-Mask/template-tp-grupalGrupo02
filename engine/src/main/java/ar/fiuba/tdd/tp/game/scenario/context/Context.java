package ar.fiuba.tdd.tp.game.scenario.context;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.scenario.Scenario;

import java.util.List;
import java.util.Optional;

/**
 * A {@link Context} is a representation of a part of a {@link Scenario} in a certain moment.
 * It can be either a boat, a room, a custom scenario, etc...
 */
public interface Context {

    Optional<Component> findComponentByName(String name);

    String getName();

    List<Component> getComponents();

    void add(Component component);

    void remove(Component component);

}
