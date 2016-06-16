import ar.fiuba.tdd.tp.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("CPD-START")
public class GameBuilderElEscape2Test {

    private GameConcrete game;
    private String playerId1;
    private String playerId2;
    private String playerId3;
    private String playerId4;
    private static final long TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILLISECONDS = 2 * 60 * 1000;
    private static final long TIME_NEEDED_TO_MOVE_MILLISECONDS = 4 * 60 * 1000;

    @Before
    public void setUp() {
        this.game = (GameConcrete) new GameBuilderElEscape2().build();

        this.playerId1 = this.game.getPlayerIDAvailable();
        this.playerId2 = this.game.getPlayerIDAvailable();
        this.playerId3 = this.game.getPlayerIDAvailable();
        this.playerId4 = this.game.getPlayerIDAvailable();

    }


    @Test
    public void testEjercicioClase1() {
        this.game.executeCommand(this.playerId1, "goto Salon1");
        this.game.executeCommand(this.playerId1, "pick botella-licor");
        this.game.executeCommand(this.playerId1, "goto pasillo");
        this.game.executeCommand(this.playerId1, "goto biblioteca-acceso");
        this.game.executeCommand(this.playerId1, "drug bibliotecario");
        this.game.executeCommand(this.playerId1, "goto biblioteca");
        this.game.executeCommand(this.playerId1, "goto biblioteca-acceso");

        this.game.update(TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILLISECONDS);

        Assert.assertTrue(this.game.playerLose(this.playerId1));
    }

    @Test
    public void testEjercicioClase2() {
        this.game.setRandomGenerator(max -> 1); // Para que vaya a la biblioteca.

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

        this.game.update(TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILLISECONDS);
        this.game.update(TIME_NEEDED_TO_MOVE_MILLISECONDS);

        Assert.assertTrue(this.game.playerLose(this.playerId1));
        Assert.assertFalse(this.game.playerLose(this.playerId2));
    }

    @Test
    public void testShouldLostIfUseStairs() {
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon3");
        game.executeCommand(playerId1, "pick llave");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "move cuadro-barco");
        game.executeCommand(playerId1, "open caja-fuerte using llave");
        game.executeCommand(playerId1, "pick credencial");
        game.executeCommand(playerId1, "put foto1 in credencial");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "show credencial in bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");
        game.executeCommand(playerId1, "move libro-viejo");
        game.executeCommand(playerId1, "goto sotano");
        game.executeCommand(playerId1, "use escalera");

        Assert.assertTrue(game.playerLose(playerId1));
        // Perdio solo el jugador1, el resto no. Continua el juego.
        Assert.assertTrue(game.getGameState() == GameState.Running);
    }

    @Test
    public void testShouldLostIfGotoBasamentWithOutAHammer() {
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon3");
        game.executeCommand(playerId1, "pick llave");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "move cuadro-barco");
        game.executeCommand(playerId1, "open caja-fuerte using llave");
        game.executeCommand(playerId1, "pick credencial");
        game.executeCommand(playerId1, "put foto1 in credencial");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "show credencial in bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");
        game.executeCommand(playerId1, "move libro-viejo");
        game.executeCommand(playerId1, "goto sotano");
        game.executeCommand(playerId1, "use baranda");

        Assert.assertTrue(game.playerLose(playerId1));
        Assert.assertTrue(game.getGameState() == GameState.Running);
    }

    @Test
    public void testShouldWinIfGotoBasamentWithHammer() {
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon3");
        game.executeCommand(playerId1, "pick llave");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon2");
        game.executeCommand(playerId1, "pick martillo");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "move cuadro-barco");
        game.executeCommand(playerId1, "open caja-fuerte using llave");
        game.executeCommand(playerId1, "pick credencial");
        game.executeCommand(playerId1, "put foto1 in credencial");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "show credencial in bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");
        game.executeCommand(playerId1, "move libro-viejo");
        game.executeCommand(playerId1, "goto sotano");
        game.executeCommand(playerId1, "use baranda");
        game.executeCommand(playerId1, "break ventana using martillo");
        game.executeCommand(playerId1, "goto afuera");

        Assert.assertFalse(game.playerLose(playerId1));
        Assert.assertTrue(game.getGameState() == GameState.Win);
    }

    @Test
    public void testShowAIncorrectCredential() {
        /*
        Se muestra la credencial que se encontro en la caja fuerte, se verifica que no se puede pasar.
         */
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon3");
        game.executeCommand(playerId1, "pick llave");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon2");
        game.executeCommand(playerId1, "pick martillo");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "move cuadro-barco");
        game.executeCommand(playerId1, "open caja-fuerte using llave");
        game.executeCommand(playerId1, "pick credencial");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "show credencial in bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");

        ObjectInterface player = game.getObject(playerId1);
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA_ACCESO, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    @Test
    public void testShowAIncorrectCredential2() {
        /*
        El jugador1 pone su foto en la credencia, pasa a la biblioteca, luego regresa a la biblioteca acceso, tira su
        credencial. Luego el jugador2 agarra la credencia y NO pone su foto, el player2 no deberia poder pasar.
        */
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon3");
        game.executeCommand(playerId1, "pick llave");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon2");
        game.executeCommand(playerId1, "pick martillo");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "move cuadro-barco");
        game.executeCommand(playerId1, "open caja-fuerte using llave");
        game.executeCommand(playerId1, "pick credencial");
        game.executeCommand(playerId1, "put foto1 in credencial");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "show credencial in bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");

        ObjectInterface player1 = game.getObject(playerId1);
        ObjectInterface player2 = game.getObject(playerId2);

        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player1.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));

        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "drop credencial");
        game.executeCommand(playerId1, "goto biblioteca");

        game.executeCommand(playerId2, "goto biblioteca-acceso");
        game.executeCommand(playerId2, "pick credencial");
        game.executeCommand(playerId2, "show credencial in bibliotecario");
        game.executeCommand(playerId2, "goto biblioteca");

        // Player1 y player2 en biblioteca.
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player1.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA_ACCESO, player2.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    @Test
    public void testShouldLostIfPlayerShowCredentialOk() {
        /*
        El jugador1 pone su foto en la credencia, pasa a la biblioteca, luego regresa a la biblioteca acceso, tira su
        credencial. Luego el jugador2 agarra la credencia y pone su foto, ambos deberian poder pasar.
        */
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon3");
        game.executeCommand(playerId1, "pick llave");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon2");
        game.executeCommand(playerId1, "pick martillo");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "move cuadro-barco");
        game.executeCommand(playerId1, "open caja-fuerte using llave");
        game.executeCommand(playerId1, "pick credencial");
        game.executeCommand(playerId1, "put foto1 in credencial");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "show credencial in bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");

        ObjectInterface player1 = game.getObject(playerId1);
        ObjectInterface player2 = game.getObject(playerId2);

        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player1.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));

        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "drop credencial");
        game.executeCommand(playerId1, "goto biblioteca");

        game.executeCommand(playerId2, "goto biblioteca-acceso");
        game.executeCommand(playerId2, "pick credencial");
        game.executeCommand(playerId2, "put foto2 in credencial");
        game.executeCommand(playerId2, "show credencial in bibliotecario");
        game.executeCommand(playerId2, "goto biblioteca");

        // Player1 y player2 en biblioteca.
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player1.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player2.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    @Test
    public void testShowInvalidCredentialAndDrugLibrarian() {
        /*
        Se muestra la credencial que se encontro en la caja fuerte, se verifica que no se puede pasar. Luego
        se droga al bibliotecario y se pasa a la biblioteca.
         */
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon3");
        game.executeCommand(playerId1, "pick llave");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon2");
        game.executeCommand(playerId1, "pick martillo");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "pick botella-licor");
        game.executeCommand(playerId1, "move cuadro-barco");
        game.executeCommand(playerId1, "open caja-fuerte using llave");
        game.executeCommand(playerId1, "pick credencial");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "show credencial in bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");

        ObjectInterface player = game.getObject(playerId1);
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA_ACCESO, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));

        game.executeCommand(playerId1, "drug bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    @Test
    public void testCantGoToBasementIfNotMoveOldBook() {
        /*
        Se verifica que el jugador no pueda pasar al sotano si no movio el libro.
         */
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon3");
        game.executeCommand(playerId1, "pick llave");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "move cuadro-barco");
        game.executeCommand(playerId1, "open caja-fuerte using llave");
        game.executeCommand(playerId1, "pick credencial");
        game.executeCommand(playerId1, "put foto1 in credencial");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "show credencial in bibliotecario");
        game.executeCommand(playerId1, "goto biblioteca");
        game.executeCommand(playerId1, "goto sotano");

        ObjectInterface player = game.getObject(playerId1);
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));

        game.executeCommand(playerId1, "move libro-viejo");
        game.executeCommand(playerId1, "goto sotano");
        Assert.assertEquals(GameBuilderElEscape2.ROOM_SOTANO, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    private void pathToStairs(String playerID) {
        game.executeCommand(playerID, "goto biblioteca-acceso");
        game.executeCommand(playerID, "goto biblioteca");
        game.executeCommand(playerID, "move libro-viejo");
        game.executeCommand(playerID, "goto sotano");
        game.executeCommand(playerID, "use escalera");

        Assert.assertTrue(game.playerLose(playerID));
    }

    @Test
    public void testGameStateLost() {
        /*
        Se va a hacer que todos los jugadores usen la escalera, para verificar que el estado del juego sea perdido.
         */
        // Primero vamos a drogar al bibliotecario, para que todos puedan hacer el mismo recorrido.
        game.executeCommand(playerId1, "goto salon1");
        game.executeCommand(playerId1, "pick botella-licor");
        game.executeCommand(playerId1, "goto pasillo");
        game.executeCommand(playerId1, "goto biblioteca-acceso");
        game.executeCommand(playerId1, "drug bibliotecario");
        game.executeCommand(playerId1, "goto pasillo");

        pathToStairs(playerId1);
        pathToStairs(playerId2);
        pathToStairs(playerId3);
        pathToStairs(playerId4);

        Assert.assertTrue(game.getGameState() == GameState.Lost);
    }

}