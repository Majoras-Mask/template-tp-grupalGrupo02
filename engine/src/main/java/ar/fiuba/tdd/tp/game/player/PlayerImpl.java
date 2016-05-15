package ar.fiuba.tdd.tp.game.player;

import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;

public class PlayerImpl implements Player {

    private final ActionResolver actionResolver;

    public PlayerImpl(ActionResolver actionResolver) {
        this.actionResolver = actionResolver;
    }

    public String doCommand(String command) {
        return this.actionResolver.execute(command);
    }

}