package ar.fiuba.tdd.tp.engine.player;

import ar.fiuba.tdd.tp.engine.player.action.resolver.ActionResolver;
import ar.fiuba.tdd.tp.engine.scenario.context.Context;

public interface Player {

    String doCommand(String message);

    Boolean has(String componentName);

    Context getCurrentContext();

    Inventory getInventory();

    void setActionResolver(ActionResolver actionResolver);

}
