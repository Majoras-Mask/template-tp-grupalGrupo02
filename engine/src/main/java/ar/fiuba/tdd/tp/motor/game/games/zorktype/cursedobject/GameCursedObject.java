package ar.fiuba.tdd.tp.motor.game.games.zorktype.cursedobject;

import ar.fiuba.tdd.tp.motor.game.components.*;
import ar.fiuba.tdd.tp.motor.game.components.gamestatus.GameStatusWon;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;


public class GameCursedObject extends ZorkTypeGame {

    private ComponentRoom winningRoom;

    public GameCursedObject() {
        ComponentRoom roomOne = new ComponentRoom();
        ComponentKey key = new ComponentKey();
        roomOne.addComponent(key);
        ComponentRoom roomTwo = new ComponentRoom();
        ComponentDoor doorZero = new ComponentDoor(roomOne, roomTwo,
                new OpenConditionObject(key, true));
        roomOne.addComponent(doorZero);

        this.winningRoom = new ComponentRoom();
        ComponentDoor doorOne = new ComponentDoor(roomTwo, this.winningRoom,
                new OpenConditionObject(key, false));
        roomTwo.addComponent(doorOne);
        ComponentThief thief = new ComponentThief();
        roomTwo.addComponent(thief);

        this.currentRoom = roomOne;
    }

    @Override
    public boolean checkIfGameIsFinished() {
        if (getCurrentRoom() == this.winningRoom) {
            this.gameStatus = new GameStatusWon();
            return true;
        }
        return false;
    }

    @Override
    public String welcomeMessage() {
        return "Welcome to the Cursed Object game, here an item is cursed and won't let you go through a door.";
    }
}
