package ar.fiuba.tdd.tp.engine;

import ar.fiuba.tdd.tp.engine.commands.GameCommand;
import ar.fiuba.tdd.tp.engine.commands.VoidToBoolean;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Game {
    private VoidToBoolean winCondition;
    private List<GameCommand> commands;

    public Game() {
        commands = new ArrayList<>();
    }

    private String doCommand(String command) {
        String response = command;
        Iterator iterator = commands.iterator();
        while (iterator.hasNext() && response.equals(command)) {
            GameCommand gameCommand = (GameCommand) iterator.next();
            String parameter;
            if (!command.equals(parameter = gameCommand.checkCommand(command))) {
                response = gameCommand.doCommand(parameter);
            }
        }
        return response.equals(command) ? "invalid command" : response;
    }

    public void setWinCondition(VoidToBoolean winCondition) {
        this.winCondition = winCondition;
    }

    public String command(String string) {
        if (winCondition.convert()) {
            return "You won the game!";
        } else {
            String response = doCommand(string);
            if (winCondition.convert()) {
                response = "You won the game!";
            }
            return response;
        }
    }

    public void setCommand(GameCommand command) {
        commands.add(command);
    }

}
