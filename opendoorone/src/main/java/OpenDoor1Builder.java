import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.elements.Content;
import ar.fiuba.tdd.tp.engine.utils.CommandsUtils;
import ar.fiuba.tdd.tp.engine.utils.ConditionUtils;

@SuppressWarnings("CPD-START")
public class OpenDoor1Builder implements GameBuilder {
    private static final int MAXPLAYERS = 2;

    @Override
    public Game build() {

        Content room1 = new Content("room1");
        Content room2 = new Content("room2");
        Content key = new Content("key");
        Content door = new Content("door");
        room1.put(key);
        room1.put(door);

        Game game = new Game(MAXPLAYERS) {
            @Override
            public void addPlayer(int playerID) {
                Content player = new Content("player" + playerID);
                room1.put(player);
                addContentCommands(player, key, door, room1, room2);
                this.addWinCondition(playerID, ConditionUtils.contentHasItem(room2, player.getName()));
                this.addLoseCondition(playerID, ConditionUtils.neverHappens());
                this.setCommand(playerID, CommandsUtils.getSameRoomCommand("pick .*", "pick", player, 1));
                this.setCommand(playerID, CommandsUtils.getLookAroundCommand("look around", player));
                this.setCommand(playerID, CommandsUtils.getSameRoomCommand("open .*", "open", player, 1));
            }
        };
        return game;
    }

    private void addContentCommands(Content player, Content key, Content door, Content room1, Content room2) {
        CommandsUtils.addPickCommand(player, key, "pick");
        door.addCommand("open", CommandsUtils.contentHasItem(player, key.getName()), CommandsUtils.removeFromHerePutOnThere(room1, room2, player, "You opened a door and walked to room2"));
    }
}