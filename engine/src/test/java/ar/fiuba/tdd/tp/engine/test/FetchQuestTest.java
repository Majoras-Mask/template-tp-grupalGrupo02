package ar.fiuba.tdd.tp.engine.test;

import org.junit.Test;

public class FetchQuestTest {


    @Test
    public void dummyTest() {
        FetchQuest fetchQuest = new FetchQuest();
        String response;

        response = fetchQuest.doCommand("look around");
        response = fetchQuest.doCommand("what to do with stick1");
        response = fetchQuest.doCommand("pick stick2");

        response = fetchQuest.doCommand("pick airplane");

        response = fetchQuest.doCommand("look around");
        response = fetchQuest.doCommand("what to do with stick1");
        response = fetchQuest.doCommand("pick stick1");

        response = fetchQuest.doCommand("look around");

//        response.equals("");
    }

}
