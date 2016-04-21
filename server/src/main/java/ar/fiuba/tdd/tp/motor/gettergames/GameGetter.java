package ar.fiuba.tdd.tp.motor.gettergames;


import ar.fiuba.tdd.tp.motor.Game;
import ar.fiuba.tdd.tp.motor.GameDefault;

public abstract class GameGetter {
    private GameGetter nextGame;
    protected String nameOfGame;
    Game game;

    public void setNextGameGetter(GameGetter nextGame) {
        this.nextGame = nextGame;
    }

    public Game getGame(String stringOfGame) {
        if (stringOfGame.equals(this.nameOfGame)) {
            return game;
        }
        if (nextGame != null) {
            return nextGame.getGame(stringOfGame);
        }
        return new GameDefault();
    }

    public static GameGetter getChainOfGameGetters() {
        GameGetter fetch = new FetchQuestGetter();
        GameGetter openDoor = new OpenDoorGetter();
        fetch.setNextGameGetter(openDoor);
        GameGetter openDoor2 = new OpenDoorTwoGetter();
        openDoor.setNextGameGetter(openDoor2);
        GameGetter cursed = new CursedItemGetter();
        openDoor2.setNextGameGetter(cursed);
        GameGetter riddle = new RiddleWolfLambCabbageGetter();
        cursed.setNextGameGetter(riddle);
        GameGetter hanoi = new HanoiGetter();
        riddle.setNextGameGetter(hanoi);
        GameGetter treasure = new TreasureQuestGetter();
        hanoi.setNextGameGetter(treasure);

        return fetch;
    }
}
