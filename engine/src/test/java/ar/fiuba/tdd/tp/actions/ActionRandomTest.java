package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.RandomGenerator;
import ar.fiuba.tdd.tp.values.ValueConstant;
import org.junit.Test;

import static org.junit.Assert.*;

public class ActionRandomTest {
    @Test
    public void execute() throws Exception {
        GameConcrete game = new GameConcrete();
        ObjectInterface object = new ObjectConcrete("objeto");
        object.setProperty("estado","ningunEstado");
        game.addObject(object);

        game.setRandomGenerator(max -> 0);

        ActionRandom action = new ActionRandom(game);
        action.addAction(new ActionSetProperty(new ValueConstant("objeto"), new ValueConstant("estado"), new ValueConstant("bueno")));
        action.addAction(new ActionSetProperty(new ValueConstant("objeto"), new ValueConstant("estado"), new ValueConstant("malo")));

        action.execute(game);

        assertTrue(object.getProperty("estado").equals("bueno"));

    }

}