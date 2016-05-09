package ar.fiuba.tdd.tp.engine.gamecomponents;

import ar.fiuba.tdd.tp.engine.behavior.Behavior;

public interface ComponentInterface {
    void addBehavior(String actionString, Behavior behavior);

    String doAction(String command, String commandModifiers);

    void setName(String name);

    String getName();
}
