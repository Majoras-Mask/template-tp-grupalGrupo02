package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

/**
 * Created by kevin on 27/04/16.
 */
public class GamePlayer extends GameComponent {

    private ZorkTypeGame game;
    private boolean isPoisoned;
    private ComponentRoom roomWhereIWasPoisoned;
    private PickCondition pickCondition = new PickConditionAlwaysTrue();

    public GamePlayer(ZorkTypeGame game) {
        this.game = game;
        this.isPoisoned = false;
        this.roomWhereIWasPoisoned = null;
    }


    @Override
    public String getBasicName() {
        return "player";
    }

    @Override
    public String whatCanIDo() {
        return "Im the one";
    }

    public void setPickCondition(PickCondition pickCondition) {
        this.pickCondition = pickCondition;
    }

    public Boolean canPick() {
        return pickCondition.canPick(game);
    }

    public void setPoisson() {
        this.isPoisoned = true;
        this.roomWhereIWasPoisoned = game.getCurrentRoom();
    }

    public void setAntidote() {
        clearPoison();
    }

    public boolean isDead() {
        if (isPoissoned() && game.getCurrentRoom() != roomWhereIWasPoisoned) {
            return true;
        }
        return false;
    }

    public boolean isPoissoned() {
        return this.isPoisoned;
    }

    void clearPoison() {
        isPoisoned = false;
        roomWhereIWasPoisoned = null;
    }
}
