package ar.fiuba.tdd.tp.game.component.attribute.contain;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;

import java.util.ArrayList;
import java.util.List;

public class ContainerImpl implements ContainerAttribute {

    private final Integer capacity;
    private final List<Component> componentList;

    public ContainerImpl(Integer capacity, List<Component> componentList) {
        this.capacity = capacity;
        this.componentList = componentList;
    }

    public ContainerImpl(Integer capacity) {
        this.capacity = capacity;
        this.componentList = new ArrayList<>();
    }

    @Override
    public Integer getCapacity() {
        return this.capacity;
    }

    @Override
    public void add(Component component) {
        if (this.capacity < this.componentList.size()) {
            this.componentList.add(component);
        }
    }

    @Override
    public void remove(Component component) {
        this.componentList.remove(component);

    }

    @Override
    public Integer size() {
        return this.componentList.size();
    }

    @Override
    public AttributeType getType() {
        return AttributeType.CONTAINER;
    }

    @Override
    public String getDescription() {
        return null;
    }
}
