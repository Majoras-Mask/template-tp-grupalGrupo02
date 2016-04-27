package ar.fiuba.tdd.tp.motor.game.games.zorktype.cursedobject;

import ar.fiuba.tdd.tp.motor.game.components.*;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class GameCursedObject extends ZorkTypeGame {

    private ComponentRoom winningRoom;

    public GameCursedObject() {
        ComponentRoom roomOne = new ComponentRoom();
        ComponentKey key = new ComponentKey();
        roomOne.addComponent(key);
        ComponentRoom roomTwo = new ComponentRoom();
        ComponentDoor doorZero = new ComponentNormalDoor(roomOne, roomTwo, key);
        roomOne.addComponent(doorZero);

        this.winningRoom = new ComponentRoom();
        ComponentDoor doorOne = new ComponentCursedDoor(roomTwo, this.winningRoom, key);
        roomTwo.addComponent(doorOne);
        ComponentThief thief = new ComponentThief();
        roomTwo.addComponent(thief);

        this.currentRoom = roomOne;
    }

    @Override
    public boolean checkIfGameIsFinished() {
        return (getCurrentRoom() == this.winningRoom);
    }

    @Override
    public String welcomeMessage() {
        return "Welcome to the Cursed Object game, here an item is cursed and won't let you go through a door.";
    }
}
