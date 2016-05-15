package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.player.action.Action;
import ar.fiuba.tdd.tp.game.player.action.Constrain;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 *
 * This behavior can be used when no other behavior can interpret the command received
 */
public class FallBackAction implements Action {

    private static final Random random = new Random();
    private final List<String> responses;

    public FallBackAction(List<String> responses) {
        this.responses = responses;
    }

    public FallBackAction() {
        this.responses = new ArrayList<>();
        this.responses.add("I'm not going to do that!");
        this.responses.add("mmm I think i don't get it.. what?");
        this.responses.add("Can't do that action!");
        this.responses.add("Go to hell, tell me something I can do!");
    }

    @Override
    public Boolean canExecute(String action) {
        return Boolean.TRUE;
    }

    @Override
    public String doExecute(String action) {
        return this.responses.get(random.nextInt(this.responses.size()));
    }

    @Override
    public List<Constrain> getConstrains() {
        return new ArrayList<>();
    }
}
