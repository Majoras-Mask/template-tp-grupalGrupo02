package ar.fiuba.tdd.tp.engine;

public interface ComponentGeneric {
    //Maybe add remove behavior?
    void addBehavior(String actionString, Behavior behavior);

    String doAction(String message);

    void setName(String name);

    String getName();
}
