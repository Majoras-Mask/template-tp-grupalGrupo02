package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public interface PickCondition  {
    boolean canPick(ZorkTypeGame game);
}
