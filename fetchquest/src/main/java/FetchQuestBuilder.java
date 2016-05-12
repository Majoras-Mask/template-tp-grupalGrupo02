import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.Player;
import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FetchQuestBuilder implements GameBuilder {

    private ComponentInterface winningCondition;

    //Names of items, commands and responses
    private static final String GAME_NAME = "Fetch Quest";
    private static final String STICK_NAME = "stick";
    private static final String ROOM_NAME = "room";

    private static final String PICK = "pick";
    private static final String LOOK_AROUND = "look around";

    private static final String CANT_PICK = "Can't pick.";
    private static final String PICK_SUCCESS = "You have picked ";
    private static final String NO_ITEM_ROOM = "There is no such item in this room.";
    private static final String WON_GAME = "You picked the stick and won the game!";

    public Game build() {
        Game fetchQuest = new Game() {
            public Player player = new Player();

            @Override
            public boolean winCondition() {
                return this.getPlayer().playerHasItem(winningCondition);
            }

            @Override
            public Player getPlayer() {
                return player;
            }

            @Override
            public String getName() {
                return GAME_NAME;
            }

            @Override
            public String command(String clientMessage) {
                String response = player.doCommand(clientMessage);
                if (winCondition()) {
                    response = WON_GAME;
                }
                return response;
            }
        };

        fetchQuest.getPlayer().addBehavior(LOOK_AROUND, new LookAround(fetchQuest));
        fetchQuest.getPlayer().addBehavior(PICK, new DirectAction(fetchQuest));

        ComponentInterface stick = new ComponentSimple(STICK_NAME);
        stick.addBehavior(PICK, new NormalPick(fetchQuest, stick));

        ComponentContainer room = new ComponentContainer(ROOM_NAME);
        room.addItem(stick);
        fetchQuest.getPlayer().setRoom(room);

        winningCondition = stick;

        return fetchQuest;
    }

    //Behaviors
    public static class DirectAction implements Behavior {
        private static final String DIRECT_ACTION_REGEX = "(^.*) (.*)";
        Game game;

        public DirectAction(Game game) {
            this.game = game;
        }

        public String execute(String completeMessage) {
            Pattern commandPattern = Pattern.compile(DIRECT_ACTION_REGEX);
            Matcher commandMatcher = commandPattern.matcher(completeMessage);
            ComponentInterface component = null;
            if (commandMatcher.find()) {
                component = game.getPlayer().obtainItemRoom(commandMatcher.group(2));
            }
            if (component != null) {
                return component.doAction(commandMatcher.group(1), completeMessage);
            }

            return NO_ITEM_ROOM;
        }
    }

    private static class LookAround implements Behavior {
        Game game;

        LookAround(Game game) {
            this.game = game;
        }

        public String execute(String modifier) {
            StringBuffer message = new StringBuffer();
            message.append(game.getPlayer().currentRoomName() + " has:");
            for (String component : game.getPlayer().listOfWhatISee()) {
                message.append(" A " + component + ".");
            }

            return message.toString();
        }
    }

    private static class NormalPick implements Behavior {
        Game game;
        ComponentInterface item;

        NormalPick(Game game, ComponentInterface item) {
            this.game = game;
            this.item = item;
        }

        public String execute(String modifier) {
            if (this.game.getPlayer().removeItemFromRoom(item)) {
                this.game.getPlayer().addItemToInventory(item);
                return PICK_SUCCESS + item.getName();
            }
            return CANT_PICK;
        }
    }
}
