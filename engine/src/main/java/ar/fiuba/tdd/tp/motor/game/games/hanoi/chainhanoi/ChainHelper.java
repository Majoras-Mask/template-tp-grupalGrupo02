package ar.fiuba.tdd.tp.motor.game.games.hanoi.chainhanoi;

import ar.fiuba.tdd.tp.motor.ChainCommandCreator;
import ar.fiuba.tdd.tp.motor.GameCommand;
import ar.fiuba.tdd.tp.motor.game.games.hanoi.commandhanoi.GameCommandHelper;

/**
 * Created by kevin on 22/04/16.
 */
public class ChainHelper extends ChainCommandCreator{
    public ChainHelper() {

    }

    @Override
    public GameCommand createCommand(String message) {
        if ( message.toLowerCase().startsWith("what can i do with stack") ) {
            // TODO sacar esto a un factory de comandos. por que esta en el de hanoi?
            return new GameCommandHelper();
        } else {
            return super.createCommand(message);
        }
    }
}
