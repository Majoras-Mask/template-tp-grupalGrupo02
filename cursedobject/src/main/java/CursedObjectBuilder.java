import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.elements.Content;
import ar.fiuba.tdd.tp.engine.utils.CommandsUtils;

@SuppressWarnings("CPD-START")
public class CursedObjectBuilder implements GameBuilder {
    private static final int MAXPLAYERS = 2;

    @Override
    public Game build() {
        Content room1 = new Content("room1");
        Content room2 = new Content("room2");
        Content room3 = new Content("room3");
        Content cursedObject = new Content("cursedObject");
        Content door1 = new Content("door");
        Content door2 = new Content("door");
        Content thief = new Content("thief");
        room1.put(cursedObject);
        room1.put(door1);
        room2.put(door2);
        room2.put(thief);

        Game game = new Game(MAXPLAYERS) {
            @Override
            public void addPlayer(int playerID) {
                Content player = new Content("player" + playerID);
                room1.put(player);
                addContentCommands(player, door1, door2, room1, room2, room3, thief, cursedObject);
                setGameCommands(playerID, player, this);
                this.addWinCondition(playerID, () -> room3.has(player.getName()));
                this.addLoseCondition(playerID, () -> false);
            }
        };
        return game;
    }

    private void setGameCommands(int playerID, Content player, Game game) {
        game.setCommand(playerID, CommandsUtils.getSameRoomCommand("pick .*", "pick", player, 1));
        game.setCommand(playerID, CommandsUtils.getLookAroundCommand("look around", player));
        game.setCommand(playerID, CommandsUtils.getSameRoomCommand("open .*", "open", player, 1));
        game.setCommand(playerID, CommandsUtils.getSameRoomCommand("say hello .*", "hello", player, 2));
    }

    private void addContentCommands(Content player, Content door1, Content door2, Content room1, Content room2, Content room3, Content thief, Content cursedObject) {
        CommandsUtils.addPickCommand(player, cursedObject, "cursedObject", "pick");
        door1.addCommand("open", (params) -> player.has("cursedObject"), (params) -> {
            room2.put(room1.take(player.getName()));
            return "You opened a door and walked to room2";
        });
        door2.addCommand("open", (params) -> !player.has("cursedObject"), (params) -> {
            room3.put(room2.take(player.getName()));
            return "You opened a door and walked to room3";
        });
        thief.addCommand("hello", (params) -> true, (params) -> {
            player.take("cursedObject");
            room2.take(thief.getName());
            return "The thief stoled your cursedObject and ran away";
        });
    }
}