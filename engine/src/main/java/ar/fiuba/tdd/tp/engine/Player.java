package ar.fiuba.tdd.tp.engine;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manuelcruz on 02/05/2016.
 */
public class Player {
    private List<Item> items;
    private String name;
    private Room room;

    public Player(String name) {
        this.name = name;
        items = new LinkedList<>();
    }

    private String pick(String string) {
        if (room.hasItem(string)) {
            items.add(room.getItem(string));
            return "Picked " + string;
        } else {
            return "Can't pick " + string;
        }
    }

    private String open(String string) {
        this.room = this.room.doTransaction();
        return "Entered room2";
    }

    private String lookAround() {
        return "In " + room.getName() + " there is" + room.getItemList();
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public boolean hasItem(Item item) {
        return items.contains(item);
    }

    public boolean isInRoom(Room room) {
        return room.equals(this.room);
    }

    public String doCommand(String command) {
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
}
