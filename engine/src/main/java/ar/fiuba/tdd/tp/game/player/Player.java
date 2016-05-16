package ar.fiuba.tdd.tp.game.player;

public interface Player {

    String doCommand(String message);

    Boolean has(String componentName);

}
