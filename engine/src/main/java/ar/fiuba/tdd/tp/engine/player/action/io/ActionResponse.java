package ar.fiuba.tdd.tp.engine.player.action.io;

public class ActionResponse {

    private final Boolean success;
    private final String message;

    public ActionResponse(Boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public Boolean success() {
        return success;
    }

    public String getMessage() {
        return message;
    }

}
