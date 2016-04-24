package ar.fiuba.tdd.tp.motor;


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
