package ar.fiuba.tdd.tp.game.player.action.impl;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.action.Action;

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
        this.responses.add("I do not know what you tell me to do!");
        this.responses.add("mmm I think i don't get it.. what?");
        this.responses.add("I Can't do that!");
        this.responses.add("What the heck? I can't do that...");
    }

    @Override
    public Boolean canExecute(String action) {
        return Boolean.TRUE;
    }

    @Override
    public String execute(String action) {
        return this.responses.get(random.nextInt(this.responses.size()));
    }

    @Override
    public List<Constraint> getConstrains() {
        return new ArrayList<>();
    }
}
