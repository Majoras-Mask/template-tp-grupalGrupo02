package ar.fiuba.tdd.tp.game.test;

import org.junit.Test;

public class FetchQuestTest {


    @Test
    public void dummyTest() {
        FetchQuest fetchQuest = new FetchQuest();
        String s;

        s = fetchQuest.doCommand("look around");
        s = fetchQuest.doCommand("what to do with stick1");
        s = fetchQuest.doCommand("pick stick2");

        s = fetchQuest.doCommand("look around");
        s = fetchQuest.doCommand("what to do with stick1");
        s = fetchQuest.doCommand("pick stick1");

        s = fetchQuest.doCommand("look around");
        s.equals("");
    }

}