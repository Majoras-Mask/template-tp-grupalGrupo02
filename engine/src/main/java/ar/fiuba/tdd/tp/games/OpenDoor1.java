package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manuelcruz on 09/05/2016.
 */
public class OpenDoor1 extends Game {
    private final Room room1;
    private final Room room2;
    private final Transaction door;
    private final Content key;

    public OpenDoor1() {
        super();
        room1 = new Room("room1");
        room2 = new Room("room2");
        key = new Content("key");
        door = new Transaction("door");
        door.setContainers(room1, room2);
        door.hasContentCondition("key");
        room1.put(key);
        room1.put(player);
    }

    @Override
    protected String doCommand(String command) {
        Pattern pickPattern = Pattern.compile("pick .*");
        Matcher pickMatcher = pickPattern.matcher(command);
        Pattern openPattern = Pattern.compile("open .*");
        Matcher openMatcher = openPattern.matcher(command);
        if (pickMatcher.find()) {
            return pick(command.substring(5));
        } else if (openMatcher.find()) {
            return open(command.substring(5));
        } else if ("look around".equals(command)) {
            return lookAround();
        } else {
            return "invalid command";
        }
    }

    @Override
    protected boolean winCondition() {
        return room2.has("player");
    }

    private String pick(String itemName) {
        Container room = player.getContainer();
        if(room.has(itemName)) {
            player.put(room.take(itemName));
            return "You picked " + itemName;
        } else {
            return "Can't pick " + itemName;
        }
    }

    private String open(String transactionName) {
        return door.doTransactionWith("player");
    }

    private String lookAround() {
        Room room = (Room) player.getContainer();
        return room.getName() + " has " + room.getItemList();
    }
}
