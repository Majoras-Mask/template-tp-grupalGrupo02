import ar.fiuba.tdd.tp.game.player.Inventory;
import ar.fiuba.tdd.tp.game.player.PlayerImpl;
import ar.fiuba.tdd.tp.game.player.action.Action;
import ar.fiuba.tdd.tp.game.player.action.impl.LookAround;
import ar.fiuba.tdd.tp.game.player.action.impl.Open;
import ar.fiuba.tdd.tp.game.player.action.impl.Pick;
import ar.fiuba.tdd.tp.game.player.action.impl.WhatToDoWith;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayerBuilder {
    private static final int INVENTORY_LIMIT = 10;

    public static final PlayerImpl playerImplementation(Context playerContext) {
        final Inventory inventory = new Inventory(new ArrayList<>(), INVENTORY_LIMIT);
        return (new PlayerImpl(playerActions(inventory, playerContext), inventory));
    }

    private static ActionResolver playerActions(Inventory inventory, Context context) {
        final Set<Action> actions = new HashSet<>();

        final LookAround lookAround = new LookAround(context);
        final WhatToDoWith whatToDoWith = new WhatToDoWith(context);
        final Pick pick = new Pick(inventory, context);
        final Open open = new Open(context);

        actions.add(lookAround);
        actions.add(whatToDoWith);
        actions.add(pick);
        actions.add(open);

        return new ActionResolver(actions);
    }
}
