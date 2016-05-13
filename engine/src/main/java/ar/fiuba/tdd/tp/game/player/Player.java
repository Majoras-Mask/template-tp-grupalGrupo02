package ar.fiuba.tdd.tp.game.player;

import ar.fiuba.tdd.tp.game.component.Component;

public interface Player {
    String doCommand(String message);

    void addItem(Component component);
}
