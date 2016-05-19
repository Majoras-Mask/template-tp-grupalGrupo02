package ar.fiuba.tdd.tp.engine.gamecomponents;

import ar.fiuba.tdd.tp.engine.behavior.Behavior;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentSimple implements ComponentInterface {
    protected String name;

    protected Map<String, Behavior> actions = new HashMap<>();

    private static final String CANT_DO_THAT_ACTION = "Can't do that action with this.";

    public ComponentSimple(String name) {
        setName(name);
    }

    public void addBehavior(String actionMsg, Behavior behavior) {
        actions.put(actionMsg, behavior);
    }

    public String doAction(String command, String commandModifier) {
        Behavior behavior = actions.get(command);
        if (behavior != null) {
            return behavior.execute(commandModifier);
        }
        return CANT_DO_THAT_ACTION;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public List<String> getListOfActions() {
        return  new ArrayList<String>(actions.keySet());
    }
}