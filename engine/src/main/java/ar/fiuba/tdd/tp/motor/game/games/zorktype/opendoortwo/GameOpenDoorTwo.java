package ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoortwo;

import ar.fiuba.tdd.tp.motor.game.components.*;
import ar.fiuba.tdd.tp.motor.game.components.gamestatus.GameStatusWon;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class GameOpenDoorTwo extends ZorkTypeGame {

    private ComponentRoom winningRoom;

    public GameOpenDoorTwo() {
        ComponentRoom roomOne = new ComponentRoom();
        this.winningRoom = new ComponentRoom();
        ComponentBox box = new ComponentBox();
        ComponentKey key = new ComponentKey();
        box.setComponent(key);
        roomOne.addComponent(box);
        ComponentDoor door = new ComponentDoor(roomOne, this.winningRoom,
                new OpenConditionObject(key, true));
        roomOne.addComponent(door);
        this.currentRoom = roomOne;
    }

    @Override
    public boolean checkIfGameIsFinished() {
        if ((getCurrentRoom() == this.winningRoom)) {
            this.gameStatus = new GameStatusWon();
            return true;
        }
        return false;
    }

    @Override
    public String welcomeMessage() {
        return "Welcome to the sequel of Open Door.\\n Your mission is to open the door to win this game.";
    }
}
