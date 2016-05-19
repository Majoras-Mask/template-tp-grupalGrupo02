package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.*;
import ar.fiuba.tdd.tp.engine.elements.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OpenDoor1Builder implements GameBuilder {

    @Override
    public Game build() {
        Game game = new Game();
        Content room1 = new Content("room1");
        Content room2 = new Content("room2");
        Content player = new Content("player");
        Content key = new Content("key");
        Content door = new Content("door");
        room1.put(player);
        room1.put(key);
        room1.put(door);
        addContentCommands(player, key, door, room1, room2);
        setGameCommands(player, game);
        game.setWinCondition(() -> room2.has("player"));
        return game;
    }

    private void setGameCommands(Content player, Game game) {
        game.setCommand(makePick(player));
        game.setCommand(makeLookAround(player));
        game.setCommand(makeOpen(player));
    }

    private void addContentCommands(Content player, Content key, Content door, Content room1, Content room2) {
        key.addCommand("pick", (params) -> true, (params) -> {
            player.put(player.getContainer().take("key"));
            return "You picked a key";
        });
        door.addCommand("open", (params) -> player.has("key"), (params) -> {
            room2.put(room1.take("player"));
            return "You opened a door and walked to room2";
        });
    }

    private GameCommand makeOpen(Content player) {
        return new GameCommand((command) -> {
            Pattern openPattern = Pattern.compile("open .*");
            Matcher openMatcher = openPattern.matcher(command);
            return openMatcher.find();
        }, (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("open");
            } else {
                return "Can't do open on " + params[0];
            }
        }, (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[1];
            params[0] = split[1];
            return params;
        });
    }

    private GameCommand makePick(Content player) {
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
