package ar.fiuba.tdd.tp.motor;

public class GameCommandHelper implements GameCommand {

    private String helpMessage;

    public GameCommandHelper(String helpMessage) {
        this.helpMessage = helpMessage;
    }

    @Override
    public String execute() {
        return this.helpMessage;
    }
}
