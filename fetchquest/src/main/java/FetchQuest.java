import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.behavior.DirectAction;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;

public class FetchQuest extends Game {

    private ComponentInterface winningCondition;
    private static final String STICK_NAME = "stick";
    private static final String ROOM_NAME = "room";

    private static final String PICK = "pick";
    private static final String LOOK_AROUND = "look around";

    private static final String CANT_PICK = "Can't pick.";
    private static final String PICK_SUCCESS = "You have picked ";
    private static final String NO_ITEM = "There is no such item in this room.";

    public FetchQuest() {
        super();
        player.addBehavior(LOOK_AROUND, new LookAround(this));
        player.addBehavior(PICK, new DirectAction(this));

        ComponentInterface stick = new ComponentSimple(STICK_NAME);
        stick.addBehavior(PICK, new NormalPick(this, stick));

        ComponentContainer room = new ComponentContainer(ROOM_NAME);
        room.addItem(stick);
        player.setRoom(room);

        winningCondition = stick;
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

    @Override
    public boolean winCondition() {
        return this.getPlayer().playerHasItem(winningCondition);
    }

    @Override
    public String noItemInRoom() {
        return NO_ITEM;
    }
}
