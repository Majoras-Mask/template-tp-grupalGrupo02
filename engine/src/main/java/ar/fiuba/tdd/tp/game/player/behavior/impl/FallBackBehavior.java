package ar.fiuba.tdd.tp.game.player.behavior.impl;

import ar.fiuba.tdd.tp.game.player.behavior.Behavior;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
 *
 * This behavior can be used when no other behavior can interpret the command received
 */
public class FallBackBehavior implements Behavior {

    private static final Random random = new Random();
    private final List<String> responses;

    public FallBackBehavior(List<String> responses) {
        this.responses = responses;
    }

    public FallBackBehavior() {
        this.responses = new ArrayList<>();
        this.responses.add("I'm not going to do that!");
        this.responses.add("mmm I think i don't get it.. what?");
        this.responses.add("Can't do that action!");
        this.responses.add("Go to hell, tell me something I can do!");
    }

    @Override
    public String execute(String command) {
        return this.responses.get(random.nextInt(this.responses.size()));
    }

    @Override
    public Boolean canExecute(String command) {
        return Boolean.TRUE;
    }
}
