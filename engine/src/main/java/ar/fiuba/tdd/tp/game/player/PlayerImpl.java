package ar.fiuba.tdd.tp.game.player;

import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;

public class PlayerImpl implements Player {

    private final ActionResolver actionResolver;
    private final Inventory inventory;

    public PlayerImpl(ActionResolver actionResolver, Inventory inventory) {
        this.actionResolver = actionResolver;
        this.inventory = inventory;
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

}