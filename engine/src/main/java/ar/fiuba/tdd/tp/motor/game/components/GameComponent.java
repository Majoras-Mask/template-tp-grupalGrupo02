package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class GameComponent {
    public int id;
    private volatile int idCounter = 0;

    public GameComponent() {
        this.id = idCounter++;
    }

    public String getDescription() {
        return (String.format("%s%s", getBasicName(), String.valueOf(this.id)));
    }

    public abstract String getBasicName();

    public Boolean pick(ZorkTypeGame game) {
        return false;
    }

    public Boolean close(ZorkTypeGame game) {
        return false;
    }

    public Boolean open(ZorkTypeGame game) {
        return false;
    }

    public Boolean talk(ZorkTypeGame game) {
        return false;
    }

    public Boolean consume(ZorkTypeGame game) {
        return false;
    }

    public abstract String whatCanIDo();

    public boolean store(GameComponent playerItem) {
        return false;
    }

    public void addedToRoom(ZorkTypeGame game) {
        game.addItemToRoom(this);
    }
}
