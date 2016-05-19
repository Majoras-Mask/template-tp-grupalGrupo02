import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;

@SuppressWarnings("CPD-START")
public class OpenDoorBuilder implements GameBuilder {

    private String winningRoomName;

    private static final String GAME_NAME = "Open Door";

    private static final String WON_GAME = "You won the game by opening the door!";
    private static final String HELP_MESSAGE = "help!!!";

    public Game build() {
        Game openDoor = new Game() {
            @Override
            public boolean winCondition() {
                return this.getPlayer().currentRoomName().equals(winningRoomName);
            }

            @Override
            public String getName() {
                return GAME_NAME;
            }

            @Override
            public String help() {
                return HELP_MESSAGE;
            }

            @Override
            protected String wonMessage() {
                return WON_GAME;
            }

        };

        BuildLevel.build(openDoor);

        winningRoomName = BuildLevel.getWinningRoomName();

        return openDoor;
    }
}