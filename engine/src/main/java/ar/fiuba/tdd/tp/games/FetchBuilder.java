package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.*;
import ar.fiuba.tdd.tp.engine.elements.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchBuilder implements GameBuilder{

    @Override
    public Game build() {
        Game game = new Game();
        Container room = new Container("room");
        Content player = new Content("player");
        Content stick = new Content("stick");
        room.put(player);
        room.put(stick);
        WinCondition playerHasStick = () -> player.has("stick");
        GameCommand pick = makePick(player);
        GameCommand lookAround = makeLookAround(player);
        game.setWinCondition(playerHasStick);
        game.setCommand(pick);
        game.setCommand(lookAround);
        return game;
    }

    private GameCommand makePick(Content player) {
        CommandParser pickParser = (command) -> {
            Pattern pickPattern = Pattern.compile("pick .*");
            Matcher pickMatcher = pickPattern.matcher(command);
            return pickMatcher.find() ? command.substring(5) : command;
        };
        CommandExecutor pickExecutor = (itemName) -> {
            Container playerRoom = player.getContainer();
            if (playerRoom.has(itemName)) {
                player.put(playerRoom.take(itemName));
                return "You picked " + itemName;
            } else {
                return "Can't pick " + itemName;
            }
        };
        return new GameCommand(pickParser, pickExecutor);
    }

    private GameCommand makeLookAround(Content player) {
        CommandParser lookAroundParser = (command) -> "look around".equals(command) ? "" : command;
        CommandExecutor lookAroundExecutor = (empty) -> {
            Container playerRoom = player.getContainer();
            return playerRoom.getName() + " has " + playerRoom.getContentsList();
        };
        return new GameCommand(lookAroundParser, lookAroundExecutor);
    }
}
