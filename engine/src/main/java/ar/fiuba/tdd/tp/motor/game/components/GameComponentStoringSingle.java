package ar.fiuba.tdd.tp.motor.game.components;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public abstract class GameComponentStoringSingle extends GameComponentStoring {
    protected GameComponent componentIHave;
    protected GameComponent componentIHad;

    public void setComponent(GameComponent component) {
        this.componentIHave = component;
    }

    public boolean hasComponent(String id) {
        if (this.componentIHave.getDescription().equals(id)) {
            return true;
        }

        return false;
    }

    public GameComponent getComponent() {
        return this.componentIHave;
    }

    public void removeComponent() {
        this.componentIHave = null;
    }

    @Override
    public String open(ZorkTypeGame game) {
        this.componentIHad = this.componentIHave;
        if (this.componentIHave != null) {
            componentIHave.addedToRoom(game);
            String descriptionOfComponent = componentIHave.getDescription();
            removeComponent();
            return "You opened " + getDescription() + ". There is a " + descriptionOfComponent + ".";
        }
        return "You opened " + getDescription() + ".";
    }

    @Override
    public String close(ZorkTypeGame game) {
        if (this.componentIHad != null && game.getCurrentRoom().hasComponent(this.componentIHad.getDescription())) {
            game.removeItemFromRoom(this.componentIHad);
            setComponent(this.componentIHad);
            this.componentIHad = null;
        }
        return "You closed " + getDescription() + ".";
    }

    @Override
    public boolean store(GameComponent component) {
        if (this.componentIHave == null) {
            this.componentIHave = component;
            return true;
        }
        return false;
    }
}
