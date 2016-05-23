package ar.fiuba.tdd.tp.game.test;

import ar.fiuba.tdd.tp.game.Game;

public class FetchQuest implements Game {
    @Override
    public String getName() {
        return "Fetch Quest";
    }

    @Override
    public Boolean isFinished() {
        return false;
    }

    @Override
    public String help() {
        return "send help";
    }

    @Override
    public String doCommand(String clientMessage) {
        return null;
    }
}
