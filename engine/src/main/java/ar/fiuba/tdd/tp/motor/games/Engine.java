package ar.fiuba.tdd.tp.motor.games;


import ar.fiuba.tdd.tp.motor.chains.ChainCommandCreator;

public abstract class Engine {

    protected ChainCommandCreator chain;

    public String request(String message) {
        if (this.chain == null) {
            this.chain = this.createChain();
        }
        return this.chain.createCommand(message).execute();
    }

    protected abstract ChainCommandCreator createChain();

    protected abstract String getWelcoming();
}
