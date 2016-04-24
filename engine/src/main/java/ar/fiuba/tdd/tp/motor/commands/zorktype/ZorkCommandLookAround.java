package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandLookAround implements GameCommand {
    private ZorkTypeGame game;

    public ZorkCommandLookAround(ZorkTypeGame game) {
        this.game = game;
    }

    @Override
    public String execute() {
        //TODO ver como hacer para que este execute devuelva las cosas que tiene el juego
        //Puede ser con un this.game.getElementsOfCurrentRoom(); y le hacemos un foreach un print de lo que hay
        return this.game.getCurrentRoom().getDescription();
    }
}
