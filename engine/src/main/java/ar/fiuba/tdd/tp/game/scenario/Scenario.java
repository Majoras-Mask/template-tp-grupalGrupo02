package ar.fiuba.tdd.tp.game.scenario;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.player.Player;

import java.util.List;

public class Scenario {

    private final List<Player> players;
    private final List<Component> components;

    public Scenario(List<Player> players, List<Component> components) {
        this.players = players;
        this.components = components;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public List<Component> getComponents() {
        return components;
    }
}
