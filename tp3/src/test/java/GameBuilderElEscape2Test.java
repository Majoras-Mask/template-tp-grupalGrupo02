import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.Sender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("CPD-START")
public class GameBuilderElEscape2Test implements Sender {

    private GameConcrete game;
    private String playerId1;
    private String playerId2;
    private static final long TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILISECONDS = 120000;
    private static final String NOMBRE_BIBLIOTECARIO = "bibliotecario";
    private static final String PROPERTY_ROOM = "room";
    private static final String ROOM_BIBLIOTECA = "biblioteca";


    @Before
    public void setUp() {
        this.game = (GameConcrete)new GameBuilderElEscape2().build();
        this.game.setSender(this);

        this.playerId1 = this.game.getPlayerIDAvailable();
        this.playerId2 = this.game.getPlayerIDAvailable();
    }


    @Test
    public void test1() {

        this.game.executeCommand(this.playerId1, "goto Salon1");
        this.game.executeCommand(this.playerId1, "pick botella-licor");
        this.game.executeCommand(this.playerId1, "goto pasillo");
        this.game.executeCommand(this.playerId1, "goto biblioteca-acceso");
        this.game.executeCommand(this.playerId1, "drug bibliotecario");
        this.game.executeCommand(this.playerId1, "goto biblioteca");
        this.game.executeCommand(this.playerId1, "goto biblioteca-acceso");


        this.game.update(TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILISECONDS);


        Assert.assertTrue(this.game.playerLose(this.playerId1));
    }

    @Test
    public void test2() {
        this.game.executeCommand(this.playerId1, "goto Salon1");
        this.game.executeCommand(this.playerId1, "pick botella-licor");
        this.game.executeCommand(this.playerId1, "goto pasillo");
        this.game.executeCommand(this.playerId1, "goto biblioteca-acceso");
        this.game.executeCommand(this.playerId1, "drug bibliotecario");

        this.game.executeCommand(this.playerId1, "goto biblioteca");
        this.game.executeCommand(this.playerId2, "goto biblioteca-acceso");
        this.game.executeCommand(this.playerId2, "goto biblioteca");

        this.game.executeCommand(this.playerId2, "goto biblioteca-acceso");
        this.game.executeCommand(this.playerId2, "goto pasillo");

        this.game.update(TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILISECONDS);

        //Avoid the RNGesus and make the library man go directly to the room library
        this.game.getObject(NOMBRE_BIBLIOTECARIO).setProperty(PROPERTY_ROOM, ROOM_BIBLIOTECA);
        this.game.update();

        Assert.assertTrue(this.game.playerLose(this.playerId1));
        Assert.assertFalse(this.game.playerLose(this.playerId2));
    }

    @Override
    public void sendAll(String message) {

    }

    @Override
    public void send(String playerID, String message) {

    }
}