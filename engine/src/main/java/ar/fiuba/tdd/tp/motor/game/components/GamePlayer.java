package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

/**
 * Created by kevin on 27/04/16.
 */
public class GamePlayer extends GameComponent {

    private ZorkTypeGame game;
    private boolean isPoissoned;
    private ComponentRoom roomWhereIWasPoissoned;
    private PickCondition pickCondition = new PickConditionAlwaysTrue();

    public GamePlayer(ZorkTypeGame game) {
        this.game = game;
        this.isPoissoned = false;
        this.roomWhereIWasPoissoned = null;
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
        this.isPoissoned = true;
        this.roomWhereIWasPoissoned = game.getCurrentRoom();
    }

    public void setAntidoto() {
        clearPoisson();
    }

    public boolean isDead() {
        if (isPoissoned() && game.getCurrentRoom() != roomWhereIWasPoissoned) {
            return true;
        }
        return false;
    }

    public boolean isPoissoned() {
        return this.isPoissoned;
    }

    void clearPoisson() {
        isPoissoned = false;
        roomWhereIWasPoissoned = null;
    }
}
