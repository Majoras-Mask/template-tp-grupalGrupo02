package ar.fiuba.tdd.tp.engine.scenario;

import ar.fiuba.tdd.tp.engine.component.Component;
import ar.fiuba.tdd.tp.engine.player.Player;

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
