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
        cursedObject.addCommand("pick", () -> true, () -> {
            player.put(player.getContainer().take("cursed object"));
            return "You picked a cursed object";
        });
        door1.addCommand("open", () -> player.has("cursed object"), () -> {
            room2.put(room1.take("player"));
            return "You opened a door and walked to room2";
        });
        door2.addCommand("open", () -> !player.has("cursed object"), () -> {
            room3.put(room2.take("player"));
            return "You opened a door and walked to room3";
        });
        thief.addCommand("hello", () -> true, () -> {
            player.take("cursed object");
            room2.take("thief");
            return "The thief stoled your cursed object and ran away";
        });
        room1.put(player);
        room1.put(cursedObject);
        room1.put(door1);
        room2.put(door2);
        room2.put(thief);
        VoidToBoolean playerIsInRoom2 = () -> room3.has("player");
        GameCommand pick = makePick(player);
        GameCommand lookAround = makeLookAround(player);
        GameCommand open = makeOpen(player);
        GameCommand hello = makeHello(player);
        game.setWinCondition(playerIsInRoom2);
        game.setCommand(pick);
        game.setCommand(lookAround);
        game.setCommand(open);
        game.setCommand(hello);
        return game;
    }

    private GameCommand makeOpen(Content player) {
        StringToString openParser = (command) -> {
            Pattern openPattern = Pattern.compile("open .*");
            Matcher openMatcher = openPattern.matcher(command);
            return openMatcher.find() ? command.substring(5) : command;
        };
        StringToString openExecutor = (itemName) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(itemName)) {
                return playerRoom.get(itemName).doCommand("open");
            } else {
                return "Can't do open on " + itemName;
            }
        };

        return new GameCommand(openParser, openExecutor);
    }

    private GameCommand makeHello(Content player) {
        StringToString openParser = (command) -> {
            Pattern openPattern = Pattern.compile("say hello .*");
            Matcher openMatcher = openPattern.matcher(command);
            return openMatcher.find() ? command.substring(10) : command;
        };
        StringToString openExecutor = (itemName) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(itemName)) {
                return playerRoom.get(itemName).doCommand("hello");
            } else {
                return "Can't do hello on " + itemName;
            }
        };

        return new GameCommand(openParser, openExecutor);
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
