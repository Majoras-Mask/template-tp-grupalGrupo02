package ar.fiuba.tdd.tp.motor.game.games.zorktype.opendoor;

import ar.fiuba.tdd.tp.motor.game.components.ComponentDoor;
import ar.fiuba.tdd.tp.motor.game.components.ComponentKey;
import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.OpenConditionObject;
import ar.fiuba.tdd.tp.motor.game.components.gamestatus.GameStatusWon;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;


public class GameOpenDoor extends ZorkTypeGame {

    private ComponentRoom winningRoom;

    public GameOpenDoor() {
        ComponentRoom roomOne = new ComponentRoom();
        this.winningRoom = new ComponentRoom();
        ComponentKey key = new ComponentKey();
        roomOne.addComponent(key);
        ComponentDoor door = new ComponentDoor(roomOne, this.winningRoom,
                new OpenConditionObject(key, true));
        roomOne.addComponent(door);
        this.currentRoom = roomOne;
    }

    public boolean checkIfGameIsFinished() {
        if (this.getCurrentRoom() == this.winningRoom) {
            this.gameStatus = new GameStatusWon();
            return true;
        }
        return false;
    }

    @Override
    public String welcomeMessage() {
        return "Welcome to Open Door Game, to win you have to open the door!\\n Simple as that, right?";
    }
}
