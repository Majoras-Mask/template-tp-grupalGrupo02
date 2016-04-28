package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ComponentThief extends GameComponent {

    public ComponentThief() {
        super();
    }

    @Override
    public String getBasicName() {
        return "thief";
    }

    @Override
    public String talk(ZorkTypeGame game) {
        game.removeAllPlayerItems();
        return "*YOINK* The thief stole all your items!!!";
    }

    @Override
    public String whatCanIDo() {
        return "You can talk to this.";
    }
}
