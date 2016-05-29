import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.elements.Content;
import ar.fiuba.tdd.tp.engine.utils.CommandsUtils;

@SuppressWarnings("CPD-START")
public class FetchBuilder implements GameBuilder {
    private static final int MAXPLAYERS = 1;

    @Override
    public Game build() {

        Content room = new Content("room");
        Content stick = new Content("stick");
        room.put(stick);

        Game game = new Game(MAXPLAYERS) {
            @Override
            public void addPlayer(int playerID) {
                Content player = new Content("player" + playerID);
                room.put(player);
                addContentCommands(player, stick);
                this.addWinCondition(playerID, () -> player.has("stick"));
                this.addLoseCondition(playerID, () -> false);
                this.setCommand(playerID, CommandsUtils.getSameRoomCommand("pick .*", "pick", player, 1));
                this.setCommand(playerID, CommandsUtils.getLookAroundCommand("look around", player));
            }
        };
        return game;
    }

    private void addContentCommands(Content player, Content stick) {
        CommandsUtils.addPickCommand(player, stick, "stick", "pick");
    }
}
