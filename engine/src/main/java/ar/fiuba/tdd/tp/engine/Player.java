package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 02/05/2016.
 */
public class Player extends Content{

    public Player(String name) {
        super(name);
    }
    /*public String pick(String string) {
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

    public String lookAround() {
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
    }*/
}
