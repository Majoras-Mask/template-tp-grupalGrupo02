package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.engine.FetchQuest;
import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTests {

    @Test
    public void dummy() {
        Game fetchQuest = new FetchQuest();
        System.out.print(fetchQuest.command("pick stick"));
    }
}
