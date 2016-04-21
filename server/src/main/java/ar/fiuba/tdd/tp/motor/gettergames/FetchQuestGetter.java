package ar.fiuba.tdd.tp.motor.gettergames;

import ar.fiuba.tdd.tp.motor.games.GameFetchQuest;

public class FetchQuestGetter extends GameGetter {
    public FetchQuestGetter() {
        this.nameOfGame = "Fetch Quest";
        this.game = new GameFetchQuest();
    }
}
