package ar.fiuba.tdd.tp.game.component;

import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ComponentImpl implements Component {

    private final String name;
    private final List<Attribute> attributes;

    public ComponentImpl(String name, List<Attribute> attributes) {
        this.name = name;
        this.attributes = attributes;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public List<Attribute> getAttributes() {
        return this.attributes;
    }

    @Override
    public Optional<Attribute> getAttribute(AttributeType type) {
        return this.attributes.stream()
                .filter(attribute -> type.equals(attribute.getType()))
                .findFirst();
    }

    @Override
    public Boolean hasAttribute(AttributeType type) {
        return this.attributes.stream()
                .anyMatch(attribute -> attribute.getType().equals(type));
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ComponentImpl component = (ComponentImpl) obj;
        return Objects.equals(name, component.name)
                && Objects.equals(attributes, component.attributes);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, attributes);
    }
}
