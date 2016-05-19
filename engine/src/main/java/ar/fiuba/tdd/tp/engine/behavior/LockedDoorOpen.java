package ar.fiuba.tdd.tp.engine.behavior;


import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class LockedDoorOpen implements Behavior {
    private static final String OPEN_DOOR_SUCCESS = "You open the door and go to the other side.";
    private static final String OPEN_DOOR_FAIL = "The door is locked, it requires a key.";

    String openSuccess = OPEN_DOOR_SUCCESS;
    String openFail = OPEN_DOOR_FAIL;
    Game game;
    ComponentInterface keyRequired;
    ComponentContainer roomItLeadsTo;

    public LockedDoorOpen(Game game, ComponentInterface key, ComponentContainer roomItLeadsTo) {
        this.game = game;
        this.keyRequired = key;
        this.roomItLeadsTo = roomItLeadsTo;
    }

    public String execute(String modifier) {
        if (game.getPlayer().playerHasItem(keyRequired)) {
            game.getPlayer().setRoom(roomItLeadsTo);
            return openSuccess;
        }
        return openFail;
    }
}
