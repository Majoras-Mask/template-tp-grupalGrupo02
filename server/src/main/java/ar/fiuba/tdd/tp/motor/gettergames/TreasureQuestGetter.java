package ar.fiuba.tdd.tp.motor.gettergames;

import ar.fiuba.tdd.tp.motor.games.GameTreasureQuest;

public class TreasureQuestGetter extends GameGetter {
    public TreasureQuestGetter() {
        this.nameOfGame = "Treasure Quest";
        this.game = new GameTreasureQuest();
    }
}
