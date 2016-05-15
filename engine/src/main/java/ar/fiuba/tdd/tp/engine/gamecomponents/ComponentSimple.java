package ar.fiuba.tdd.tp.engine.gamecomponents;

import ar.fiuba.tdd.tp.game.player.action.Action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComponentSimple implements ComponentInterface {
    protected String name;

    protected Map<String, Action> actions = new HashMap<>();

    public ComponentSimple(String name) {
        setName(name);
    }

    public void addBehavior(String actionMsg, Action action) {
        actions.put(actionMsg, action);
    }

    @Override
    public String doAction(String command, String commandModifiers) {
        return null;
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