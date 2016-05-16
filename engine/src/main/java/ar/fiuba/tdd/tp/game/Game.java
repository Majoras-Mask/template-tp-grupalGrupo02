package ar.fiuba.tdd.tp.game;

/*
 * Interface used by the engine to doExecute a game
 */
public interface Game {

    String getName();

    Boolean isWon();

    Boolean isLost();

    String help();

    String doCommand(String clientMessage);

}
