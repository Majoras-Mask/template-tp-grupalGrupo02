package ar.fiuba.tdd.tp.motor.games;


import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;

public abstract class Engine {

    protected ChainCommandCreator chain;

    private Game gameEngine;

    public Engine(Game gameEngine) {
        this.gameEngine = gameEngine;
    }

    private String createResponse(String message) {

        String response = this.chain.createCommand(message).execute();
        if (gameEngine.checkIfGameIsFinished()) {
            response = response.concat(getFinishedGameMessage());
        }
        return response;
    }

    private String getFinishedGameMessage() {
        return gameEngine.finishedMessage();
    }


    public String request(String message) {
        if (gameEngine.checkIfGameIsFinished()) {
            return getFinishedGameMessage();
        }
        if (this.chain == null) {
            this.chain = this.createChain();
        }
        return createResponse(message);

    }

    protected abstract ChainCommandCreator createChain();

    public abstract String getWelcoming();
}
