package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.*;
import ar.fiuba.tdd.tp.engine.elements.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class FetchBuilder implements GameBuilder{

    @Override
    public Game build() {
        Game game = new Game();
        Content room = new Content("room");
        Content player = new Content("player");
        Content stick = new Content("stick");
        stick.addCommand("pick", (params) -> true, (params) -> {
            player.put(player.getContainer().take("stick"));
            return "You picked a stick";
        });
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
        StringToString pickParser = (command) -> {
            Pattern pickPattern = Pattern.compile("pick .*");
            Matcher pickMatcher = pickPattern.matcher(command);
            return pickMatcher.find() ? command.substring(5) : command;
        };
        StringToString pickExecutor = (itemName) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(itemName)) {
                return playerRoom.get(itemName).doCommand("pick");
            } else {
                return "Can't do pick on " + itemName;
            }
        };
        return new GameCommand(pickParser, pickExecutor);
    }

    private GameCommand makeLookAround(Content player) {
        StringToString lookAroundParser = (command) -> "look around".equals(command) ? "" : command;
        StringToString lookAroundExecutor = (empty) -> {
            Content playerRoom = player.getContainer();
            return playerRoom.getName() + " has " + playerRoom.getContentsList();
        };
        return new GameCommand(lookAroundParser, lookAroundExecutor);
    }
}
