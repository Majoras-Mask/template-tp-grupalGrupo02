package ar.fiuba.tdd.tp.engine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manuelcruz on 02/05/2016.
 */
public class Fetch extends Game{
    private final Room room;
    private final Item stick;

    public Fetch() {
        super();
        room = new Room("room");
        stick = new Item("stick");
        room.put(stick);
        room.put(player);
    }

    @Override
    protected String doCommand(String command) {
        Pattern pickPattern = Pattern.compile("pick .*");
        Matcher pickMatcher = pickPattern.matcher(command);
        if (pickMatcher.find()) {
            return pick(command.substring(5));
        } else if ("look around".equals(command)) {
            return lookAround();
        } else {
            return "invalid command";
        }
    }

    @Override
    protected boolean winCondition() {
        return player.has("stick");
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

    private String lookAround() {
        return room.getName() + " has " + room.getItemList();
    }
}
