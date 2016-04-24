package ar.fiuba.tdd.tp.motor.commands.zorktype;


import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ZorkCommand implements GameCommand {

    protected ZorkTypeGame game;
    public String actionName;

    public GameComponents getComponent(String nameOfComponent) {
        return this.game.getCurrentRoom().getComponent(nameOfComponent);
    }
}
