import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.Sender;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class GameBuilderElEscape2Test implements Sender {

    private Game game;
    private String playerId1;
    private String playerId2;

    @Before
    public void setUp() {
        this.game = new GameBuilderElEscape2().build();
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

        for (int i = 0; i < 120; i++) {
            this.game.update();
        }

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

        for (int i = 0; i < 120; i++) {
            this.game.update();
        }

        for (int i = 0; i < 240; i++) {
            this.game.update();
        }

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