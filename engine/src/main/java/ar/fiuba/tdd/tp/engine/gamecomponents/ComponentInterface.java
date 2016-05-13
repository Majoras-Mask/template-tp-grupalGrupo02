package ar.fiuba.tdd.tp.engine.gamecomponents;

import ar.fiuba.tdd.tp.game.player.behavior.Behavior;

import java.util.List;

public interface ComponentInterface {
    void addBehavior(String actionString, Behavior behavior);

    String doAction(String command, String commandModifiers);

    void setName(String name);

    String getName();

    List<String> getListOfActions();
}
