package ar.fiuba.tdd.tp.motor.commands.zorktype;


import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class ZorkCommandActionable extends ZorkCommand {
    public final String whoToDoActionWith;

    public ZorkCommandActionable(ZorkTypeGame game, String whoToDoActionWith, String actionName) {
        this.game = game;
        this.whoToDoActionWith = whoToDoActionWith;
        this.actionName = actionName;
    }

    public abstract Boolean componentAction(GameComponents component);

    public String noSuchThing() {
        return "There is no such thing to " + this.actionName + ".";
    }

    public String actionSuccess(GameComponents component) {
        return "You " + this.actionName + "ed " + component.getDescription() + ".";
    }

    public String notAplicable() {
        return "Can't " + this.actionName + " it.";
    }

    @Override
    public String execute() {
        GameComponents component = this.game.getCurrentRoom().getComponent(this.whoToDoActionWith);

        if (component == null) {
            return noSuchThing();
        }
        component.setGame(this.game);
        if (componentAction(component)) {
            return actionSuccess(component);
        } else {
            return notAplicable();
        }
    }

}
