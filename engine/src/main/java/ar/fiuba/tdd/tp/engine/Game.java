package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.commands.GameCondition;
import ar.fiuba.tdd.tp.engine.commands.game.GameCommand;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private GameCondition winCondition;
    private GameCondition loseCondition;
    private List<GameCommand> commands;

    public Game() {
        commands = new ArrayList<>();
    }

    private String processInput(String input) {
        String response = input;
        Iterator iterator = commands.iterator();
        while (iterator.hasNext() && response.equals(input)) {
            GameCommand gameCommand = (GameCommand) iterator.next();
            if (gameCommand.checkCommand(input)) {
                response = gameCommand.doCommand(gameCommand.parseCommand(input));
            }
        }
        return response.equals(input) ? "invalid command" : response;
    }

    public void setWinCondition(GameCondition winCondition) {
        this.winCondition = winCondition;
    }

    public void setLoseCondition(GameCondition loseCondition) {
        this.loseCondition = loseCondition;
    }

    public String command(String string) {
        if (winCondition.check()) {
            return "You won the game!";
        } else if (loseCondition.check()) {
            return "You lose the game!";
        } else {
            String response = processInput(string);
            if (winCondition.check()) {
                response = "You won the game!";
            } else if (loseCondition.check()) {
                response = "You lose the game!";
            }
            return response;
        }
    }

    public void setCommand(GameCommand command) {
        commands.add(command);
    }

}
