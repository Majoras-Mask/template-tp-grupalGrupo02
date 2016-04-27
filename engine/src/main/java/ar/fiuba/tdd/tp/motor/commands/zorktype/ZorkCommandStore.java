package ar.fiuba.tdd.tp.motor.commands.zorktype;


import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;

public class ZorkCommandStore extends ZorkCommand {
    private final String whatToStore;
    private final String whereToStore;

    public ZorkCommandStore(ZorkTypeGame game, String whatToStore, String whereToStore) {
        this.game = game;
        this.whatToStore = whatToStore;
        this.whereToStore = whereToStore;
    }

    @Override
    public String execute() {
        return this.game.store(this.whatToStore, this.whereToStore);
    }
}
