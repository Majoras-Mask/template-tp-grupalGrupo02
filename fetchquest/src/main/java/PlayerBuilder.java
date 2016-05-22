import ar.fiuba.tdd.tp.game.player.Inventory;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.PlayerImpl;
import ar.fiuba.tdd.tp.game.player.action.Action;
import ar.fiuba.tdd.tp.game.player.action.impl.LookAround;
import ar.fiuba.tdd.tp.game.player.action.impl.Pick;
import ar.fiuba.tdd.tp.game.player.action.impl.WhatToDoWith;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;
import ar.fiuba.tdd.tp.game.scenario.context.Context;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class PlayerBuilder {
    private static final int INVENTORY_LIMIT = 10;

    public static final Player playerImplementation(Context playerContext) {
        final Inventory inventory = new Inventory(new ArrayList<>(), INVENTORY_LIMIT);
        Player player = new PlayerImpl(playerContext, inventory);
        playerActions(inventory, player);
        return player;
    }

    private static void playerActions(Inventory inventory, Player player) {
        final Set<Action> actions = new HashSet<>();

        final LookAround lookAround = new LookAround(player);
        final WhatToDoWith whatToDoWith = new WhatToDoWith(player, "what can i do with");
        final Pick pick = new Pick(inventory, player, "pick");

        actions.add(lookAround);
        actions.add(whatToDoWith);
        actions.add(pick);

        player.setActionResolver(new ActionResolver(actions));
        return;
    }
}
