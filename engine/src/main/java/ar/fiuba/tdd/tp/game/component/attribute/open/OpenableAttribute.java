package ar.fiuba.tdd.tp.game.component.attribute.open;

import ar.fiuba.tdd.tp.game.component.attribute.Attribute;

public interface OpenableAttribute extends Attribute {

    Boolean isOpen();

    void open();

}
