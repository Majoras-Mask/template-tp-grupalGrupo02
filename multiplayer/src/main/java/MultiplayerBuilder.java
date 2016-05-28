import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.elements.Content;
import ar.fiuba.tdd.tp.engine.utils.CommandsUtils;

@SuppressWarnings("CPD-START")
public class MultiplayerBuilder implements GameBuilder {

    @Override
    public Game build() {
        Game game = new Game(1) {
            @Override
            public void addPlayer(int playerID) {
                Content room = new Content("room");
                Content player = new Content("player");
                Content stick = new Content("stick");
                room.put(player);
                room.put(stick);
                addContentCommands(player, stick);
                this.setWinCondition(() -> player.has("stick"));
                this.setLoseCondition(() -> false);
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