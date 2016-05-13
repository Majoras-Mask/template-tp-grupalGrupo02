package ar.fiuba.tdd.tp.game.context;

import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Player;

public class GameContext {

    private final Player player;
    private final ComponentContainer currentRoom;

    public GameContext(Player player, ComponentContainer currentRoom) {
        this.player = player;
        this.currentRoom = currentRoom;
    }

    public Player getPlayer() {
        return player;
    }

    public ComponentContainer getCurrentRoom() {
        return currentRoom;
    }

    public Component findComponentByName(String name) {
        //TODO usar una sola interfaz Component
//        ComponentInterface item = this.currentRoom.getItem(name);

        return null;
    }

    public void addItemToPlayer(Component component) {
        this.player.addItem(component);
    }

    public void removeComponent(Component component) {
        this.currentRoom.removeComponent(component);
    }
}
