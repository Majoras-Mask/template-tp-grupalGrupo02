package ar.fiuba.tdd.tp.game.scenario.context;

import ar.fiuba.tdd.tp.game.component.Component;

import java.util.List;
import java.util.Optional;

public interface Context {

    Optional<Component> findComponentByName(String name);

    String getName();

    List<Component> getComponents();

    void add(Component component);

    void remove(Component component);

}
