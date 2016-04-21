package ar.fiuba.tdd.tp.motor.gettergames;

import ar.fiuba.tdd.tp.motor.games.GameCursedItem;

public class CursedItemGetter extends GameGetter{
    public CursedItemGetter() {
        this.nameOfGame = "Cursed Item";
        this.game = new GameCursedItem();
    }
}
