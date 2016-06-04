import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.elements.Content;
import ar.fiuba.tdd.tp.engine.utils.CommandsUtils;
import ar.fiuba.tdd.tp.engine.utils.ConditionUtils;

@SuppressWarnings("CPD-START")
public class FetchBuilder implements GameBuilder {
    private static final int MAXPLAYERS = 3;

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
                this.addWinCondition(playerID, ConditionUtils.contentHasItem(player, stick.getName()));
                this.addLoseCondition(playerID, ConditionUtils.neverHappens());
                this.setCommand(playerID, CommandsUtils.getSameRoomCommand("pick .*", "pick", player, 1));
                this.setCommand(playerID, CommandsUtils.getLookAroundCommand("look around", player));
            }
        };
        return game;
    }

    private void addContentCommands(Content player, Content stick) {
        CommandsUtils.addPickCommand(player, stick, "pick");
    }
}
