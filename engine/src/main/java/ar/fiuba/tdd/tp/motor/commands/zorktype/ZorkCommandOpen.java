package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandOpen implements GameCommand {

    private final String whatToOpen;
    private ZorkTypeGame game;

    public ZorkCommandOpen(ZorkTypeGame game, String whatToOpen) {
        this.game = game;
        this.whatToOpen = whatToOpen;
    }

    @Override
    public String execute() {
        GameComponents component = this.game.getCurrentRoom().getComponent(this.whatToOpen);

        if (component == null) {
            return "There is no such thing to pick.";
        }
        if (component.open()) {
            return "You opened " + component.getDescription() + ".";
        } else {
            return "Can't open.";
        }
    }
}
