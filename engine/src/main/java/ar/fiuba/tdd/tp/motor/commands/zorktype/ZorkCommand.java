package ar.fiuba.tdd.tp.motor.commands.zorktype;


import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.components.GameComponent;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ZorkCommand implements GameCommand {
    protected ZorkTypeGame game;
}
