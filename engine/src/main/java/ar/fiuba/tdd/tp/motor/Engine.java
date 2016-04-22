package ar.fiuba.tdd.tp.motor;


public abstract class Engine {
    protected Game game;

    String request(String message) {
        return getChainOfCommands(game).createCommand(message).execute();
    }

    protected abstract ChainCommandCreator getChainOfCommands(Game game);

}
