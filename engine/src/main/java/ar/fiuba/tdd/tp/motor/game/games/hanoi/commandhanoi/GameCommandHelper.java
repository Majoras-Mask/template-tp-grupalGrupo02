package ar.fiuba.tdd.tp.motor.game.games.hanoi.commandhanoi;

import ar.fiuba.tdd.tp.motor.GameCommand;


public class GameCommandHelper implements GameCommand {
    @Override
    public String execute() {
        return "You can check top/move top.";
    }
}
