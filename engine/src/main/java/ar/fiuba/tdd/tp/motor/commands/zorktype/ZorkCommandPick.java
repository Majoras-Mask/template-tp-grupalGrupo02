package ar.fiuba.tdd.tp.motor.commands.zorktype;

import ar.fiuba.tdd.tp.motor.commands.GameCommand;
import ar.fiuba.tdd.tp.motor.game.components.GameComponents;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandPick implements GameCommand {
    private final String whatToPick;
    private ZorkTypeGame game;
    
    public ZorkCommandPick(ZorkTypeGame game, String whatToPick) {
        this.game = game;
        this.whatToPick = whatToPick;
    }

    private GameComponents getComponent(String nameOfComponent) {
        return this.game.getCurrentRoom().getComponent(nameOfComponent);
    }

    @Override
    public String execute() {
        GameComponents component = getComponent(this.whatToPick);

        if (component == null) {
            return "There is no such thing to pick.";
        }
        if (component.pick()) {
            this.game.getCurrentRoom().removeComponent(this.whatToPick);
            return "Picked " + component.getDescription();
        } else {
            return "Can't pick " + component.getDescription();
        }
    }
}
