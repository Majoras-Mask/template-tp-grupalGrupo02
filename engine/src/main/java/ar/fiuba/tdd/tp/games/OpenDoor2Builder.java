package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.*;
import ar.fiuba.tdd.tp.engine.elements.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class OpenDoor2Builder implements GameBuilder {

    @Override
    public Game build() {
        Game game = new Game();
        Content room1 = new Content("room1");
        Content room2 = new Content("room2");
        Content player = new Content("player");
        Content box = new Content("box");
        Content key = new Content("key");
        Content door = new Content("door");
        box.addCommand("open", (params) -> true, (params) -> {
            room1.put(box.take("key"));
            return "You opened a box";
        });
        key.addCommand("pick", (params) -> true, (params) -> {
            player.put(player.getContainer().take("key"));
            return "You picked a key";
        });
        door.addCommand("open", (params) -> player.has("key"), (params) -> {
            room2.put(room1.take("player"));
            return "You opened a door and walked to room2";
        });
        room1.put(player);
        room1.put(box);
        room1.put(door);
        box.put(key);
        WinCondition playerIsInRoom2 = () -> room2.has("player");
        GameCommand pick = makePick(player);
        GameCommand lookAround = makeLookAround(player);
        GameCommand open = makeOpen(player);
        game.setWinCondition(playerIsInRoom2);
        game.setCommand(pick);
        game.setCommand(lookAround);
        game.setCommand(open);
        return game;
    }

    private GameCommand makeOpen(Content player) {
        CommandValidator openParser = (command) -> {
            Pattern openPattern = Pattern.compile("open .*");
            Matcher openMatcher = openPattern.matcher(command);
            return openMatcher.find();
        };
        CommandExecutor openExecutor = (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("open");
            } else {
                return "Can't do open on " + params[0];
            }
        };

        return new GameCommand(openParser, openExecutor, (command) -> new String[0]);
    }

    private GameCommand makePick(Content player) {
        CommandValidator pickParser = (command) -> {
            Pattern pickPattern = Pattern.compile("pick .*");
            Matcher pickMatcher = pickPattern.matcher(command);
            return pickMatcher.find();
        };
        CommandExecutor pickExecutor = (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("pick");
            } else {
                return "Can't do pick on " + params[0];
            }
        };
        return new GameCommand(pickParser, pickExecutor, (command) -> new String[0]);
    }

    private GameCommand makeLookAround(Content player) {
        CommandValidator lookAroundParser = (command) -> "look around".equals(command);
        CommandExecutor lookAroundExecutor = (params) -> {
            Content playerRoom = player.getContainer();
            return playerRoom.getName() + " has " + playerRoom.getContentsList();
        };
        return new GameCommand(lookAroundParser, lookAroundExecutor, (command) -> new String[0]);
    }

}
