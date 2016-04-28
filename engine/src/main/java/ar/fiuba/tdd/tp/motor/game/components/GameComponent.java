package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class GameComponent {
    protected int id;
    protected String response;

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

    public Boolean pick(ZorkTypeGame game) {
        setResponse("Can't pick " + getDescription() + ".");
        return false;
    }

    public Boolean close(ZorkTypeGame game) {
        setResponse("Can't close " + getDescription() + ".");
        return false;
    }

    public Boolean open(ZorkTypeGame game) {
        setResponse("Can't open " + getDescription() + ".");
        return false;
    }

    public Boolean talk(ZorkTypeGame game) {
        setResponse("It doesn't answer back. Maybe " + getDescription() + " is not the talkative type.");
        return false;
    }

    public Boolean consume(ZorkTypeGame game) {
        setResponse("Can't consume " + getDescription() + ".");
        return false;
    }

    public abstract String whatCanIDo();

    public boolean store(GameComponent playerItem) {
        return false;
    }

    public void addedToRoom(ZorkTypeGame game) {
        game.addItemToRoom(this);
    }

    public void setResponse(String response) {
        this.response = response;
    }

    private void clearResponse() {
        this.response = "";
    }

    public String getResponse() {
        String response = this.response;
        clearResponse();
        return response;
    }
}
