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
        room.put(player);
        room.put(stick);
        game.setWinCondition(() -> player.has("stick"));
        game.setCommand(makePick(player, stick));
        game.setCommand(makeLookAround(player));
        return game;
    }

    private GameCommand makePick(Content player, Content stick) {
        stick.addCommand("pick", (params) -> true, (params) -> {
            player.put(player.getContainer().take("stick"));
            return "You picked a stick";
        });
        return new GameCommand((command) -> {
            Pattern pickPattern = Pattern.compile("pick .*");
            Matcher pickMatcher = pickPattern.matcher(command);
            return pickMatcher.find();
        }, (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("pick");
            } else {
                return "Can't do pick on " + params[0];
            }
        }, (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[1];
            params[0] = split[1];
            return params;
        });
    }

    private GameCommand makeLookAround(Content player) {
        return new GameCommand((command) -> "look around".equals(command), (params) -> {
            Content playerRoom = player.getContainer();
            return playerRoom.getName() + " has " + playerRoom.getContentsList();
        }, (command) -> new String[0]);
    }
}
