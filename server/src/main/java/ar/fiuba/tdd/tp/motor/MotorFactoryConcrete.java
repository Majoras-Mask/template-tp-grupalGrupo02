package ar.fiuba.tdd.tp.motor;


public class MotorFactoryConcrete implements MotorFactory {
    //Game currentGame = null;

    public MotorFactoryConcrete(/*String msgFromServer*/) {
        /* Según lo que reciba del server crea el juego correspondiente?
        getGame(msgFromServer);
        */
    }

    public void doAction(String action) {
        //Command commandToUse = parseCommand(action);
        //currentGame.do(commandToUse);
    }

    public Motor createMotor() {
        return new Motor();
    }

}
