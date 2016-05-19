package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.*;
import ar.fiuba.tdd.tp.engine.elements.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class CursedObjectBuilder implements GameBuilder {

    @Override
    public Game build() {
        Game game = new Game();
        Content room1 = new Content("room1");
        Content room2 = new Content("room2");
        Content room3 = new Content("room3");
        Content player = new Content("player");
        Content cursedObject = new Content("cursed object");
        Content door1 = new Content("door");
        Content door2 = new Content("door");
        Content thief = new Content("thief");
        cursedObject.addCommand("pick", (params) -> true, (params) -> {
            player.put(player.getContainer().take("cursed object"));
            return "You picked a cursed object";
        });
        door1.addCommand("open", (params) -> player.has("cursed object"), (params) -> {
            room2.put(room1.take("player"));
            return "You opened a door and walked to room2";
        });
        door2.addCommand("open", (params) -> !player.has("cursed object"), (params) -> {
            room3.put(room2.take("player"));
            return "You opened a door and walked to room3";
        });
        thief.addCommand("hello", (params) -> true, (params) -> {
            player.take("cursed object");
            room2.take("thief");
            return "The thief stoled your cursed object and ran away";
        });
        room1.put(player);
        room1.put(cursedObject);
        room1.put(door1);
        room2.put(door2);
        room2.put(thief);
        WinCondition playerIsInRoom3 = () -> room3.has("player");
        GameCommand pick = makePick(player);
        GameCommand lookAround = makeLookAround(player);
        GameCommand open = makeOpen(player);
        GameCommand hello = makeHello(player);
        game.setWinCondition(playerIsInRoom3);
        game.setCommand(pick);
        game.setCommand(lookAround);
        game.setCommand(open);
        game.setCommand(hello);
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

    private GameCommand makeHello(Content player) {
        CommandValidator helloParser = (command) -> {
            Pattern helloPattern = Pattern.compile("say hello .*");
            Matcher helloMatcher = helloPattern.matcher(command);
            return helloMatcher.find();
        };
        CommandExecutor helloExecutor = (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("hello");
            } else {
                return "Can't do hello on " + params[0];
            }
        };

        return new GameCommand(helloParser, helloExecutor, (command) -> new String[0]);
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
        CommandExecutor lookAroundExecutor = (empty) -> {
            Content playerRoom = player.getContainer();
            return playerRoom.getName() + " has " + playerRoom.getContentsList();
        };
        return new GameCommand(lookAroundParser, lookAroundExecutor, (command) -> new String[0]);
    }

}
