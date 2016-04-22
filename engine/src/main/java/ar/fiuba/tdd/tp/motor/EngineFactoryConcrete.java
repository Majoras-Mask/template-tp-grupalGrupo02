package ar.fiuba.tdd.tp.motor;


public class EngineFactoryConcrete implements EngineFactory {
    //Game currentGame = null;

    public EngineFactoryConcrete(/*String msgFromServer*/) {
        /* Según lo que reciba del server crea el juego correspondiente?
        getGame(msgFromServer);
        */
    }

    public void doAction(String action) {
        //Command commandToUse = parseCommand(action);
        //currentGame.do(commandToUse);
    }

    public Engine createEngine() {
        return new Engine();
    }

}
