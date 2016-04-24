package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentDoor extends GameComponents {

    ComponentKey keyRequired = null;
    ComponentRoom roomItLeadsTo;
    ZorkTypeGame game;

    public ComponentDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo, ComponentKey keyRequired) {
        this.roomItLeadsTo = roomItLeadsTo;
        this.keyRequired = keyRequired;
        this.game = game;
    }

    public ComponentDoor(ZorkTypeGame game, ComponentRoom roomItLeadsTo) {
        this(game, roomItLeadsTo, null);
    }

    public Boolean matchingKey(GameComponentsSimple component) {
        return component.getDescription().equals(this.keyRequired.getDescription());
    }

    public void unlockDoor() {
        this.keyRequired = null;
    }

    public ComponentRoom getWhereItLeadsTo() {
        return this.roomItLeadsTo;
    }

    public Boolean isThisDoorUnlocked() {
        return this.keyRequired == null;
    }

    @Override
    public String getDescription() {
        return "Door" + String.valueOf(this.id);
    }

    @Override
    public Boolean pick() {
        return false;
    }

    @Override
    public Boolean close() {
        return true;
    }

    @Override
    public Boolean open() {
        if (isThisDoorUnlocked()) {
            return true;
        }
        for (GameComponentsSimple component: this.game.getPlayerItems()) {
            if (matchingKey(component)) {
                unlockDoor();
                //TODO aca tendriamos que ponerle una funcion que diga setCurrentRoom al juego?
                //goToRoom(ZorkTypeGame game) {
                //  game.setCurrentRoom(getWhereItLeadsTo)
                //}
                return true;
            }
        }
        return false;
    }
}
