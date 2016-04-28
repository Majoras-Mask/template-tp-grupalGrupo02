package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

/**
 * Created by kevin on 27/04/16.
 */
public class PickConditionLessThanTwoItems implements PickCondition {
    @Override
    public boolean canPick(ZorkTypeGame game) {
        return game.getPlayerItems().size() < 2;
    }
}
