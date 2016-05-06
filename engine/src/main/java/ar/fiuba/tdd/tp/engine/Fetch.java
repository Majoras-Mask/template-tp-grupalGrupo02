package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 02/05/2016.
 */
public class Fetch {
    private final Room room;
    private final Item stick;
    private final Player player;

    public Fetch() {
        room = new Room("room");
        player = new Player("player");
        stick = new Item("stick");
        room.putItem(stick);
        player.setRoom(room);
    }

    private boolean winCondition() {
        return player.hasItem(stick);
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
