package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class GameComponent {
    protected int id;

    public GameComponent() {
        this.id = 0;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return (String.format("%s%s", getBasicName(), String.valueOf(this.id)));
    }

    public abstract String getBasicName();

    public String pick(ZorkTypeGame game) {
        return "Can't pick " + getDescription() + ".";
    }

    public String close(ZorkTypeGame game) {
        return "Can't close " + getDescription() + ".";
    }

    public String open(ZorkTypeGame game) {
        return "Can't open " + getDescription() + ".";
    }

    public String talk(ZorkTypeGame game) {
        return "It doesn't answer back. Maybe " + getDescription() + " is not the talkative type.";
    }

    public String consume(ZorkTypeGame game) {
        return "Can't consume " + getDescription() + ".";
    }

    public abstract String whatCanIDo();

    public boolean store(GameComponent playerItem) {
        return false;
    }

    public void addedToRoom(ZorkTypeGame game) {
        game.addItemToRoom(this);
    }
}
