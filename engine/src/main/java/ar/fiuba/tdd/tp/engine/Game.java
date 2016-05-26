package ar.fiuba.tdd.tp.engine;

/*
 * Interface used by the engine to doExecute a game
 */
public interface Game {

    String getName();

    Boolean isFinished();

    String help();

    String doCommand(String clientMessage);

    String getWelcomeMessage();
}
