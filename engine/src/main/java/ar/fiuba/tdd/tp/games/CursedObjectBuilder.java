package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.elements.*;
import ar.fiuba.tdd.tp.engine.utils.CommandsUtils;

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
        game.setLoseCondition(() -> false);
        return game;
    }

    private void setGameCommands(Content player, Game game) {
        game.setCommand(CommandsUtils.getSameRoomCommand("pick .*", "pick", player, 1));
        game.setCommand(CommandsUtils.getLookAroundCommand("look around", player));
        game.setCommand(CommandsUtils.getSameRoomCommand("open .*", "open", player, 1));
        game.setCommand(CommandsUtils.getSameRoomCommand("say hello .*", "hello", player, 2));
    }

    private void addContentCommands(Content player, Content door1, Content door2, Content room1, Content room2, Content room3, Content thief, Content cursedObject) {
        CommandsUtils.addPickCommand(player, cursedObject, "cursedObject", "pick");
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
}
