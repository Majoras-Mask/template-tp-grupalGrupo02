package ar.fiuba.tdd.tp.motor;

public class GameCreatorConcrete implements GameCreator {
    public Game createGame(Game gameToCreate) {
        return gameToCreate.getInstance();
    }
}
