package ar.fiuba.tdd.tp.game.player.behavior;

public interface Behavior {

    String execute(String command);

    Boolean canExecute(String command);

}
