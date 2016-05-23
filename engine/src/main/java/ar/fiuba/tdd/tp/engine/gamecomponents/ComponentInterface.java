package ar.fiuba.tdd.tp.engine.gamecomponents;

import ar.fiuba.tdd.tp.game.player.action.ActionDecider;

import java.util.List;

public interface ComponentInterface {
    void addBehavior(String actionString, ActionDecider actionDecider);

    String doAction(String command, String commandModifiers);

    void setName(String name);

    String getName();

    List<String> getListOfActions();
}
