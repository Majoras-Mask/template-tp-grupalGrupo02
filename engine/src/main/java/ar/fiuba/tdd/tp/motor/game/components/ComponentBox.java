package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

import java.util.LinkedList;
import java.util.List;

public class ComponentBox extends GameComponentStoringMultiple {

    public ComponentBox() {
        super();
    }

    @Override
    public String getBasicName() {
        return "box";
    }
}
