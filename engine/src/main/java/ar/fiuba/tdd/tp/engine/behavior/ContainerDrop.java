package ar.fiuba.tdd.tp.engine.behavior;


import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class ContainerDrop implements Behavior {
    Game game;
    ComponentContainer item;

    public ContainerDrop(Game game, ComponentContainer item) {
        this.game = game;
        this.item = item;
    }

    public String execute(String modifier) {
        StringBuffer message = new StringBuffer();
        message.append(modifier + " reveals: ");
        for (String itemName : item.listOfComponents()) {
            ComponentInterface component = item.removeItem(itemName);
            game.getPlayer().putItemInRoom(component);
            message.append(itemName + ". ");
        }

        return message.toString();
    }
}
