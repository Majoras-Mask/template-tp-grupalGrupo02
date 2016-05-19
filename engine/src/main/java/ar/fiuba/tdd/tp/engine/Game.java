package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.commands.GameCommand;
import ar.fiuba.tdd.tp.engine.commands.CommandValidation;
import ar.fiuba.tdd.tp.engine.commands.WinCondition;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private WinCondition winCondition;
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

    public void setWinCondition(WinCondition winCondition) {
        this.winCondition = winCondition;
    }

    public String command(String string) {
        if (winCondition.check()) {
            return "You won the game!";
        } else {
            String response = processInput(string);
            if (winCondition.check()) {
                response = "You won the game!";
            }
            return response;
        }
    }

    public void setCommand(GameCommand command) {
        commands.add(command);
    }

}
