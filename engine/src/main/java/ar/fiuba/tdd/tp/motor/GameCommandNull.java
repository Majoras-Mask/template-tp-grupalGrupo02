package ar.fiuba.tdd.tp.motor;

import ar.fiuba.tdd.tp.motor.GameCommand;

/**
 * Created by kevin on 22/04/16.
 */
public class GameCommandNull implements GameCommand {
    @Override
    public String execute() {
        return "No es posible procesar el comando.";
    }
}
