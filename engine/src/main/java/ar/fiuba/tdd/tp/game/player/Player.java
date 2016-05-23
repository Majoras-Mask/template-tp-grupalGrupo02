package ar.fiuba.tdd.tp.game.player;

import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

public interface Player {

    String doCommand(String message);

    Boolean has(String componentName);

    Context getCurrentContext();

    Inventory getInventory();

    void setActionResolver(ActionResolver actionResolver);

}
