import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.GameState;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.driver.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

@SuppressWarnings("CPD-START")
public class ElEscape2Test {

    private GameConcrete game;
    private GameDriver gameDriver;
    private String playerId1;
    private String playerId2;
    private String playerId3;
    private String playerId4;
    private static final long TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILLISECONDS = 2 * 60 * 1000;
    private static final long TIME_NEEDED_TO_MOVE_MILLISECONDS = 4 * 60 * 1000;
    private static final String PATH_TO_EL_ESCAPE2 = "../tp3.jar";

    @Before
    public void setUp() throws GameLoadFailedException, PlayerJoinFailedException {
        gameDriver = new ConcreteGameDriver();
        gameDriver.initGame(PATH_TO_EL_ESCAPE2);
        game = (GameConcrete) gameDriver.getGame();

        playerId1 = gameDriver.joinPlayer();
        playerId2 = gameDriver.joinPlayer();
        playerId3 = gameDriver.joinPlayer();
        playerId4 = gameDriver.joinPlayer();

    }

    @Test
    public void testEjercicioClase1() throws UnknownPlayerException {
        gameDriver.sendCommand(playerId1, "goto Salon1");
        gameDriver.sendCommand(playerId1, "pick botella-licor");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "drug bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");

        game.update(TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILLISECONDS);

        Assert.assertTrue(gameDriver.checkIfPlayerHasLost(playerId1));
    }

    @Test
    public void testEjercicioClase2() throws UnknownPlayerException {
        this.game.setRandomGenerator(max -> 1); // Para que vaya a la biblioteca.

        gameDriver.sendCommand(playerId1, "goto Salon1");
        gameDriver.sendCommand(playerId1, "pick botella-licor");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "drug bibliotecario");

        gameDriver.sendCommand(playerId1, "goto biblioteca");
        gameDriver.sendCommand(playerId2, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId2, "goto biblioteca");

        gameDriver.sendCommand(playerId2, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId2, "goto pasillo");

        game.update(TIME_NEEDED_TO_WAKE_UP_LIBRARIAN_MILLISECONDS);
        game.update(TIME_NEEDED_TO_MOVE_MILLISECONDS);

        Assert.assertTrue(gameDriver.checkIfPlayerHasLost(playerId1));
        Assert.assertFalse(gameDriver.checkIfPlayerHasLost(playerId2));
    }

    @Test
    public void testShouldLostIfUseStairs() throws UnknownPlayerException {
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon3");
        gameDriver.sendCommand(playerId1, "pick llave");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "move cuadro-barco");
        gameDriver.sendCommand(playerId1, "open caja-fuerte using llave");
        gameDriver.sendCommand(playerId1, "pick credencial");
        gameDriver.sendCommand(playerId1, "put foto1 in credencial");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");
        gameDriver.sendCommand(playerId1, "move libro-viejo");
        gameDriver.sendCommand(playerId1, "goto sotano");
        gameDriver.sendCommand(playerId1, "use escalera");

        Assert.assertTrue(gameDriver.checkIfPlayerHasLost(playerId1));
        // Perdio solo el jugador1, el resto no. Continua el juego.
        Assert.assertTrue(gameDriver.getCurrentState() == GameState.Running);
        Assert.assertFalse(gameDriver.checkIfPlayerHasLost(playerId2));
        Assert.assertFalse(gameDriver.checkIfPlayerHasLost(playerId3));
        Assert.assertFalse(gameDriver.checkIfPlayerHasLost(playerId4));
    }

    @Test
    public void testShouldLostIfGotoBasamentWithOutAHammer() throws UnknownPlayerException {
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon3");
        gameDriver.sendCommand(playerId1, "pick llave");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "move cuadro-barco");
        gameDriver.sendCommand(playerId1, "open caja-fuerte using llave");
        gameDriver.sendCommand(playerId1, "pick credencial");
        gameDriver.sendCommand(playerId1, "put foto1 in credencial");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");
        gameDriver.sendCommand(playerId1, "move libro-viejo");
        gameDriver.sendCommand(playerId1, "goto sotano");
        gameDriver.sendCommand(playerId1, "use baranda");

        Assert.assertTrue(gameDriver.checkIfPlayerHasLost(playerId1));
        Assert.assertTrue(gameDriver.getCurrentState() == GameState.Running);
        Assert.assertFalse(gameDriver.checkIfPlayerHasLost(playerId2));
        Assert.assertFalse(gameDriver.checkIfPlayerHasLost(playerId3));
        Assert.assertFalse(gameDriver.checkIfPlayerHasLost(playerId4));
    }

    @Test
    public void testShouldWinIfGotoBasamentWithHammer() throws UnknownPlayerException {
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon3");
        gameDriver.sendCommand(playerId1, "pick llave");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon2");
        gameDriver.sendCommand(playerId1, "pick martillo");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "move cuadro-barco");
        gameDriver.sendCommand(playerId1, "open caja-fuerte using llave");
        gameDriver.sendCommand(playerId1, "pick credencial");
        gameDriver.sendCommand(playerId1, "put foto1 in credencial");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");
        gameDriver.sendCommand(playerId1, "move libro-viejo");
        gameDriver.sendCommand(playerId1, "goto sotano");
        gameDriver.sendCommand(playerId1, "use baranda");
        gameDriver.sendCommand(playerId1, "break ventana using martillo");
        gameDriver.sendCommand(playerId1, "goto afuera");

        Assert.assertFalse(gameDriver.checkIfPlayerHasLost(playerId1));
        Assert.assertTrue(game.getGameState() == GameState.Win);
    }

    @Test
    public void testShowAIncorrectCredential() throws UnknownPlayerException {
        /*
        Se muestra la credencial que se encontro en la caja fuerte, se verifica que no se puede pasar.
         */
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon3");
        gameDriver.sendCommand(playerId1, "pick llave");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon2");
        gameDriver.sendCommand(playerId1, "pick martillo");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "move cuadro-barco");
        gameDriver.sendCommand(playerId1, "open caja-fuerte using llave");
        gameDriver.sendCommand(playerId1, "pick credencial");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");

        ObjectInterface player = game.getObject(playerId1);
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA_ACCESO, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    @Test
    public void testShowAIncorrectCredential2() throws UnknownPlayerException {
        /*
        El jugador1 pone su foto en la credencia, pasa a la biblioteca, luego regresa a la biblioteca acceso, tira su
        credencial. Luego el jugador2 agarra la credencia y NO pone su foto, el player2 no deberia poder pasar.
        */
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon3");
        gameDriver.sendCommand(playerId1, "pick llave");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon2");
        gameDriver.sendCommand(playerId1, "pick martillo");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "move cuadro-barco");
        gameDriver.sendCommand(playerId1, "open caja-fuerte using llave");
        gameDriver.sendCommand(playerId1, "pick credencial");
        gameDriver.sendCommand(playerId1, "put foto1 in credencial");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");

        ObjectInterface player1 = game.getObject(playerId1);
        ObjectInterface player2 = game.getObject(playerId2);

        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player1.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));

        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "drop credencial");
        gameDriver.sendCommand(playerId1, "goto biblioteca");

        gameDriver.sendCommand(playerId2, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId2, "pick credencial");
        gameDriver.sendCommand(playerId2, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId2, "goto biblioteca");

        // Player1 y player2 en biblioteca.
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player1.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA_ACCESO, player2.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    @Test
    public void testShouldLostIfPlayerShowCredentialOk() throws UnknownPlayerException {
        /*
        El jugador1 pone su foto en la credencia, pasa a la biblioteca, luego regresa a la biblioteca acceso, tira su
        credencial. Luego el jugador2 agarra la credencia y pone su foto, ambos deberian poder pasar.
        */
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon3");
        gameDriver.sendCommand(playerId1, "pick llave");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon2");
        gameDriver.sendCommand(playerId1, "pick martillo");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "move cuadro-barco");
        gameDriver.sendCommand(playerId1, "open caja-fuerte using llave");
        gameDriver.sendCommand(playerId1, "pick credencial");
        gameDriver.sendCommand(playerId1, "put foto1 in credencial");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");

        ObjectInterface player1 = game.getObject(playerId1);
        ObjectInterface player2 = game.getObject(playerId2);

        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player1.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));

        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "drop credencial");
        gameDriver.sendCommand(playerId1, "goto biblioteca");

        gameDriver.sendCommand(playerId2, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId2, "pick credencial");
        gameDriver.sendCommand(playerId2, "put foto2 in credencial");
        gameDriver.sendCommand(playerId2, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId2, "goto biblioteca");

        // Player1 y player2 en biblioteca.
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player1.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player2.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    @Test
    public void testShowInvalidCredentialAndDrugLibrarian() throws UnknownPlayerException {
        /*
        Se muestra la credencial que se encontro en la caja fuerte, se verifica que no se puede pasar. Luego
        se droga al bibliotecario y se pasa a la biblioteca.
         */
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon3");
        gameDriver.sendCommand(playerId1, "pick llave");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon2");
        gameDriver.sendCommand(playerId1, "pick martillo");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "pick botella-licor");
        gameDriver.sendCommand(playerId1, "move cuadro-barco");
        gameDriver.sendCommand(playerId1, "open caja-fuerte using llave");
        gameDriver.sendCommand(playerId1, "pick credencial");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");

        ObjectInterface player = game.getObject(playerId1);
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA_ACCESO, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));

        gameDriver.sendCommand(playerId1, "drug bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    @Test
    public void testCantGoToBasementIfNotMoveOldBook() throws UnknownPlayerException {
        /*
        Se verifica que el jugador no pueda pasar al sotano si no movio el libro.
         */
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon3");
        gameDriver.sendCommand(playerId1, "pick llave");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "move cuadro-barco");
        gameDriver.sendCommand(playerId1, "open caja-fuerte using llave");
        gameDriver.sendCommand(playerId1, "pick credencial");
        gameDriver.sendCommand(playerId1, "put foto1 in credencial");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "show credencial in bibliotecario");
        gameDriver.sendCommand(playerId1, "goto biblioteca");
        gameDriver.sendCommand(playerId1, "goto sotano");

        ObjectInterface player = game.getObject(playerId1);
        Assert.assertEquals(GameBuilderElEscape2.ROOM_BIBLIOTECA, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));

        gameDriver.sendCommand(playerId1, "move libro-viejo");
        gameDriver.sendCommand(playerId1, "goto sotano");
        Assert.assertEquals(GameBuilderElEscape2.ROOM_SOTANO, player.getProperty(GameBuilderElEscape2.PROPERTY_ROOM));
    }

    private void pathToStairs(String playerID) throws UnknownPlayerException {
        gameDriver.sendCommand(playerID, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerID, "goto biblioteca");
        gameDriver.sendCommand(playerID, "move libro-viejo");
        gameDriver.sendCommand(playerID, "goto sotano");
        gameDriver.sendCommand(playerID, "use escalera");

        Assert.assertTrue(gameDriver.checkIfPlayerHasLost(playerID));
    }

    @Test
    public void testGameStateLost() throws UnknownPlayerException {
        /*
        Se va a hacer que todos los jugadores usen la escalera, para verificar que el estado del juego sea perdido.
         */
        // Primero vamos a drogar al bibliotecario, para que todos puedan hacer el mismo recorrido.
        gameDriver.sendCommand(playerId1, "goto salon1");
        gameDriver.sendCommand(playerId1, "pick botella-licor");
        gameDriver.sendCommand(playerId1, "goto pasillo");
        gameDriver.sendCommand(playerId1, "goto biblioteca-acceso");
        gameDriver.sendCommand(playerId1, "drug bibliotecario");
        gameDriver.sendCommand(playerId1, "goto pasillo");

        pathToStairs(playerId1);
        pathToStairs(playerId2);
        pathToStairs(playerId3);
        pathToStairs(playerId4);

        Assert.assertTrue(gameDriver.getCurrentState() == GameState.Lost);
    }

}
