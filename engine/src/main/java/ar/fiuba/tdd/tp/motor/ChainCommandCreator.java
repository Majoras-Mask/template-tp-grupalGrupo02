package ar.fiuba.tdd.tp.motor;

/**
 * Created by kevin on 22/04/16.
 */
public abstract class ChainCommandCreator {

    protected ChainCommandCreator nextChain;

    public void setNextChain(ChainCommandCreator chain) {
        this.nextChain = chain;
    }

    public GameCommand createCommand(String message) {
        if (this.nextChain != null) {
            return this.nextChain.createCommand(message);
        } else {
            return new GameCommandNull();
        }
    }

}
