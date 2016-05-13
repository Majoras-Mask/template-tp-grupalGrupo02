package ar.fiuba.tdd.tp.game.component;

import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;

import java.util.List;
import java.util.Optional;

/**
 * Interface for all the components that a Game needs to work.
 * Every component has a list of {@link Attribute} that describes it.
 * For example, a Door can be Openable, or Lockable.
 */
public interface Component {

    String getName();

    Optional<Attribute> getAttribute(AttributeType type);

    List<Attribute> getAttributes();

    Boolean hasAttribute(Attribute attribute);

}
