package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.GameComponent;
import ar.fiuba.tdd.tp.motor.games.Game;

import java.util.LinkedList;
import java.util.List;

public abstract class ZorkTypeGame implements Game {
    public ComponentRoom currentRoom;
    public List<GameComponent> playerItems = new LinkedList<>();

    public ComponentRoom getCurrentRoom() {
        return this.currentRoom;
    }

    public List<GameComponent> getPlayerItems() {
        return this.playerItems;
    }

    public boolean hasPlayerComponent(String id) {
        for (GameComponent component : this.playerItems) {
            if (component.getDescription().equals(id)) {
                return true;
            }
        }
        return false;
    }

    public void addPlayerItem(GameComponent component) {
        this.playerItems.add(component);
    }

    public void removePlayerItem(GameComponent component) {
        this.playerItems.remove(component);
    }

    public void removeAllPlayerItems() {
        this.playerItems = null;
    }

    public void setCurrentRoom(ComponentRoom room) {
        this.currentRoom = room;
    }

    public void removeItemFromRoom(GameComponent component) {
        this.currentRoom.removeComponent(component.getDescription());
    }

    public void addItemToRoom(GameComponent component) {
        this.currentRoom.addComponent(component);
    }

    public GameComponent getComponent(String componentName) {
        return getCurrentRoom().getComponent(componentName);
    }

    public String lookAround() {
        StringBuffer message = new StringBuffer();
        message.append(getCurrentRoom().getDescription() + " has:");
        for (GameComponent component : getCurrentRoom().getListOfComponents()) {
            message.append(" A " + component.getDescription() + ".");
        }
        return message.toString();
    }

    private String noClose() {
        return "There is no such thing to close.";
    }

    private String closeSuccess(GameComponent component) {
        return "You closed " + component.getDescription() + ".";
    }

    private String closeFail(GameComponent component) {
        return "Can't close " + component.getDescription() + ".";
    }

    public String close(String whatToClose) {
        GameComponent component = getComponent(whatToClose);

        if (component == null) {
            return noClose();
        }
        if (component.close(this)) {
            return closeSuccess(component);
        } else {
            return closeFail(component);
        }
    }

    public String open(String whatToOpen) {
        GameComponent component = getComponent(whatToOpen);

        if (component == null) {
            return "There is no such thing to open.";
        }
        if (component.open(this)) {
            return "Opened it.";
        } else {
            return "Can't open " + component.getDescription() + ".";
        }
    }

    public String pick(String whatToPick) {
        GameComponent component = getComponent(whatToPick);

        if (component == null) {
            return "There is no such thing to pick.";
        }
        if (component.pick(this)) {
            return "You picked " + component.getDescription() + ".";
        } else {
            return "Can't pick " + component.getDescription() + ".";
        }
    }

    public String talk(String whoToTalkTo) {
        GameComponent component = getComponent(whoToTalkTo);

        if (component == null) {
            return "There is no such thing to talk to.";
        }
        if (component.talk(this)) {
            return "You talked to " + component.getDescription() + ".";
        } else {
            return "It doesn't answer back.";
        }
    }

    public String whatCanIDoWith(String whoToDoActionWith) {
        GameComponent component = getComponent(whoToDoActionWith);

        if (component == null) {
            return "There is no such thing in here.";
        }
        return component.whatCanIDo();
    }

    public abstract String welcomeMessage();
}
