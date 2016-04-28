package ar.fiuba.tdd.tp.motor.game.games.zorktype;

import ar.fiuba.tdd.tp.motor.game.components.ComponentRoom;
import ar.fiuba.tdd.tp.motor.game.components.ComponentUtilities;
import ar.fiuba.tdd.tp.motor.game.components.GameComponent;
import ar.fiuba.tdd.tp.motor.game.components.GamePlayer;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.gamestatus.GameStatus;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.gamestatus.GameStatusPlaying;
import ar.fiuba.tdd.tp.motor.games.Game;

import java.util.ArrayList;
import java.util.List;

public abstract class ZorkTypeGame implements Game {
    protected ComponentRoom currentRoom;
    protected List<GameComponent> playerItems = new ArrayList<>();
    protected GamePlayer gamePlayer = new GamePlayer(this);
    protected GameStatus gameStatus = new GameStatusPlaying();

    public ComponentRoom getCurrentRoom() {
        return this.currentRoom;
    }

    protected void setGamePlayer(GamePlayer gamePlayer) {
        this.gamePlayer = gamePlayer;
    }

    public GamePlayer getGamePlayer() {
        return gamePlayer;
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

    public GameComponent getPlayerItem(GameComponent component) {
        return this.playerItems.get(this.playerItems.indexOf(component));
    }

    public GameComponent getPlayerItem(String whatToGet) {
        return ComponentUtilities.getComponent(whatToGet, this.playerItems);
    }

    public void removePlayerItem(GameComponent component) {
        this.playerItems.remove(component);
    }

    public void removeAllPlayerItems() {
        this.playerItems.clear();
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

    public GameComponent getComponentFromRoom(String componentName) {
        return getCurrentRoom().getComponent(componentName);
    }

    public boolean hasRoomComponent(String id) {
        return this.getCurrentRoom().hasComponent(id);
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
        GameComponent component = getComponentFromRoom(whatToClose);

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
        GameComponent component = getComponentFromRoom(whatToOpen);

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
        GameComponent component = getComponentFromRoom(whatToPick);

        if (component == null) {
            return "There is no such thing to pick.";
        }
        if (gamePlayer.canPick() && component.pick(this)) {
            return "You picked " + component.getDescription() + ".";
        } else {
            return "Can't pick " + component.getDescription() + ".";
        }
    }

    public String talk(String whoToTalkTo) {
        GameComponent component = getComponentFromRoom(whoToTalkTo);

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
        GameComponent component = getComponentFromRoom(whoToDoActionWith);

        if (component == null) {
            return "There is no such thing in here.";
        }
        return component.whatCanIDo();
    }

    public String consume(String whatToConsume) {
        if (!hasPlayerComponent(whatToConsume)) {
            return "There is no such thing to consume.";
        }
        GameComponent component = getPlayerItem(whatToConsume);
        if (component.consume(this)) {
            return "Consumed it.";
        } else {
            return "Can't consume " + component.getDescription() + ".";
        }
    }

    public String store(String whatToStore, String whereToStore) {
        if (hasPlayerComponent(whatToStore) && hasRoomComponent(whereToStore)) {
            GameComponent playerItem = getPlayerItem(whatToStore);
            GameComponent itemDestination = getComponentFromRoom(whereToStore);
            if (itemDestination.store(getPlayerItem(whatToStore))) {
                removePlayerItem(playerItem);
                return "Stored " + whatToStore + "in " + whereToStore + ".";
            }
            return "Can't store in " + whereToStore + ".";
        }
        return "Either you don't have that item, or there is no such thing as a " + whereToStore + ".";
    }

    public abstract String welcomeMessage();

    public String finishedMessage() {
        return this.gameStatus.statusMessage();
    }
}