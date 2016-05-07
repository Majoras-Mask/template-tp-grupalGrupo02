package ar.fiuba.tdd.tp.engine;

public interface ComponentInterface {
    //Maybe add remove behavior?
    void addBehavior(String actionString, Behavior behavior);

    String doAction(String message);

    void setName(String name);

    String getName();
}
