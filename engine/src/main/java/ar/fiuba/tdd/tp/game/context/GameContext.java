package ar.fiuba.tdd.tp.game.context;

import ar.fiuba.tdd.tp.game.component.Component;

import java.util.List;
import java.util.Optional;

public interface GameContext {

    Optional<Component> findComponentByName(String name);

    String getName();

    List<Component> getComponents();

    void add(Component component);

    void remove(Component component);

}
