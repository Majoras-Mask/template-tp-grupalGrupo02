package ar.fiuba.tdd.tp.motor.game.games.zorktype.commands;


import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkType;

public abstract class ZorkTypeCommand {
    public ZorkType game;
    public String receiverOfAction;

    public ZorkTypeCommand(ZorkType game, String whatToOpen) {
        this.game = game;
        this.receiverOfAction = whatToOpen;
    }
}
