package ar.fiuba.tdd.tp.motor.games;


import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;

public abstract class Engine {

    private ChainCommandCreator chain;

    String request(String message) {
        if (this.chain == null) {
            this.chain = this.createChain();
        }
        return this.chain.createCommand(message).execute();
    }

    protected abstract ChainCommandCreator createChain();

}
