import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.elements.Content;
import ar.fiuba.tdd.tp.engine.utils.CommandsUtils;

public class FetchBuilder implements GameBuilder {

    @SuppressWarnings("CPD-START")
    @Override
    public Game build() {
        Game game = new Game();
        Content room = new Content("room");
        Content player = new Content("player");
        Content stick = new Content("stick");
        room.put(player);
        room.put(stick);
        addContentCommands(player, stick);
        game.setWinCondition(() -> player.has("stick"));
        game.setLoseCondition(() -> false);
        game.setCommand(CommandsUtils.getSameRoomCommand("pick .*", "pick", player, 1));
        game.setCommand(CommandsUtils.getLookAroundCommand("look around", player));
        return game;
    }

    @SuppressWarnings("CPD-END")
    private void addContentCommands(Content player, Content stick) {
        CommandsUtils.addPickCommand(player, stick, "stick", "pick");
    }

}
