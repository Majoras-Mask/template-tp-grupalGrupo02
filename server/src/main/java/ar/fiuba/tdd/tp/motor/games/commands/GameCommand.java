package ar.fiuba.tdd.tp.motor.games.commands;

import ar.fiuba.tdd.tp.motor.games.Game;

public interface GameCommand {
    void execute(String executorModificator);
}
