package ar.fiuba.tdd.tp.engine.player;

import ar.fiuba.tdd.tp.engine.player.action.ActionDecider;
import ar.fiuba.tdd.tp.engine.player.action.resolver.ActionResolver;
import ar.fiuba.tdd.tp.engine.scenario.context.Context;

public class PlayerImpl implements Player {

    private final Inventory inventory;
    private Context currentContext;
    private ActionResolver actionResolver;

    public PlayerImpl() {
        this.inventory = new Inventory();
        actionResolver = new ActionResolver();
    }

    @Override
    public String doCommand(String command) {
        return this.actionResolver.execute(command);
    }

    @Override
    public Boolean has(String componentName) {
        return this.inventory.getComponents().stream()
                .anyMatch(component -> component.getName().equals(componentName));
    }

    @Override
    public Context getCurrentContext() {
        return currentContext;
    }


    @Override
    public Inventory getInventory() {
        return inventory;
    }

    @Override
    public void limitInventory(int limit) {
        this.inventory.setLimit(limit);
    }

    @Override
    public void putInRoom(Context room) {
        currentContext = room;
    }

    @Override
    public void addAction(ActionDecider actionDecider) {
        actionResolver.addActionDecider(actionDecider);
    }

}