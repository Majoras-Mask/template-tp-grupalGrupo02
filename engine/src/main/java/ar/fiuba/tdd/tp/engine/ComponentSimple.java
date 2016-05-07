package ar.fiuba.tdd.tp.engine;

import java.util.HashMap;
import java.util.Map;

public class ComponentSimple implements ComponentGeneric {

    private String name;

    private Map<String, Behavior> actions = new HashMap<>();

    @Override
    public void addBehavior(String actionMsg, Behavior behavior) {
        actions.put(actionMsg, behavior);
    }

    @Override
    public String doAction(String message) {
        Behavior behavior = actions.get(message);
        if (behavior != null) {
            return behavior.execute();
        }
        return null;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }
}