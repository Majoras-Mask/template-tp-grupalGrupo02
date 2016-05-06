package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 05/05/2016.
 */
public class OpenDoor1 {
    private final Room room1;
    private final Room room2;
    private final Item key;
    private final Player player;
    private final Door door;

    public OpenDoor1() {
        room1 = new Room("room1");
        room2 = new Room("room2");
        player = new Player("player");
        key = new Item("key");
        door = new Door("door", room1, room2);
        room1.putItem(key);
        room1.setDoor(door);
        room2.setDoor(door);
        player.setRoom(room1);
    }

    private boolean winCondition() {
        return player.isInRoom(room2);
    }

    public String command(String string) {
        if (winCondition()) {
            return "You won the game!";
        } else {
            String response = player.doCommand(string);
            if (winCondition()) {
                response = "You won the game!";
            }
            return response;
        }
    }
}
