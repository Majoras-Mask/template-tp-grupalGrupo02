package ar.fiuba.tdd.tp.motor.game.games.zorktype.commands;


import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ZorkTypeCommand {
    public ZorkTypeGame game;
    public String receiverOfAction;

    public ZorkTypeCommand(ZorkTypeGame game, String whatToOpen) {
        this.game = game;
        this.receiverOfAction = whatToOpen;
    }
}
