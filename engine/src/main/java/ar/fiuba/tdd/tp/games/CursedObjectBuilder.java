package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.game.GameCommand;
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
        Content cursedObject = new Content("cursedObject");
        Content door1 = new Content("door");
        Content door2 = new Content("door");
        Content thief = new Content("thief");
        room1.put(player);
        room1.put(cursedObject);
        room1.put(door1);
        room2.put(door2);
        room2.put(thief);
        addContentCommands(player, door1, door2, room1, room2, room3, thief, cursedObject);
        setGameCommands(player, game);
        game.setWinCondition(() -> room3.has("player"));
        return game;
    }

    private void setGameCommands(Content player, Game game) {
        game.setCommand(makePick(player));
        game.setCommand(makeLookAround(player));
        game.setCommand(makeOpen(player));
        game.setCommand(makeHello(player));
    }

    private void addContentCommands(Content player, Content door1, Content door2, Content room1, Content room2, Content room3, Content thief, Content cursedObject) {
        cursedObject.addCommand("pick", (params) -> true, (params) -> {
            player.put(player.getContainer().take("cursedObject"));
            return "You picked a cursedObject";
        });
        door1.addCommand("open", (params) -> player.has("cursedObject"), (params) -> {
            room2.put(room1.take("player"));
            return "You opened a door and walked to room2";
        });
        door2.addCommand("open", (params) -> !player.has("cursedObject"), (params) -> {
            room3.put(room2.take("player"));
            return "You opened a door and walked to room3";
        });
        thief.addCommand("hello", (params) -> true, (params) -> {
            player.take("cursedObject");
            room2.take("thief");
            return "The thief stoled your cursedObject and ran away";
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

    private GameCommand makeHello(Content player) {
        return new GameCommand((command) -> {
            Pattern helloPattern = Pattern.compile("say hello .*");
            Matcher helloMatcher = helloPattern.matcher(command);
            return helloMatcher.find();
        }, (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("hello");
            } else {
                return "Can't do hello on " + params[0];
            }
        }, (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[1];
            params[0] = split[2];
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
        return new GameCommand((command) -> "look around".equals(command), (empty) -> {
            Content playerRoom = player.getContainer();
            return playerRoom.getName() + " has " + playerRoom.getContentsList();
        }, (command) -> new String[0]);
    }

}
