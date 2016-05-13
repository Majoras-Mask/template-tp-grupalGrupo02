package ar.fiuba.tdd.tp.game.component.attribute.contain;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;

public interface ContainerAttribute extends Attribute {

    Integer getCapacity();

    void add(Component component);

    void remove(Component component);

    Integer size();
}
