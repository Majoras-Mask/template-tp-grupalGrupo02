
import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.GameDriver;
import ar.fiuba.tdd.tp.engine.GameState;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

@SuppressWarnings("CPD-START")
public class MainTest {

    public GameDriver setup() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("");
        return driver;
    }

    @Test
    public void testLoseIfGoingDownUsingStairs() {
        GameDriver driver = setup();
        driver.sendCommand("goto acceso biblioteca");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto salon 3");
        driver.sendCommand("pick llave");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto salon 1");
        driver.sendCommand("move cuadro de barco");
        driver.sendCommand("open caja fuerte");
        driver.sendCommand("pick credencial");
        driver.sendCommand("put foto credencial");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto acceso biblioteca");
        driver.sendCommand("show credencial bibliotecario");
        driver.sendCommand("goto biblioteca");
        driver.sendCommand("move libro viejo");
        driver.sendCommand("goto sotano");
        driver.sendCommand("use escalera");

        assertEquals(GameState.LOST, driver.getGameState());
    }

    @Test
    public void testLoseIfGoingDownWithoutHammerTime() {
        GameDriver driver = setup();
        driver.sendCommand("goto acceso biblioteca");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto salon 3");
        driver.sendCommand("pick llave");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto salon 1");
        driver.sendCommand("move cuadro de barco");
        driver.sendCommand("open caja fuerte");
        driver.sendCommand("pick credencial");
        driver.sendCommand("put foto credencial");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto acceso biblioteca");
        driver.sendCommand("show credencial bibliotecario");
        driver.sendCommand("goto biblioteca");
        driver.sendCommand("move libro viejo");
        driver.sendCommand("goto sotano");
        driver.sendCommand("use baranda");

        assertEquals(GameState.LOST, driver.getGameState());
    }

    @Test
    public void testWinWhenGoingDownWithHammerTime() {
        GameDriver driver = setup();
        driver.sendCommand("goto acceso biblioteca");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto salon 3");
        driver.sendCommand("pick llave");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto salon 2");
        driver.sendCommand("pick martillo");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto salon 1");
        driver.sendCommand("move cuadro de barco");
        driver.sendCommand("open caja fuerte");
        driver.sendCommand("pick credencial");
        driver.sendCommand("put foto credencial");
        driver.sendCommand("goto pasillo");
        driver.sendCommand("goto acceso biblioteca");
        driver.sendCommand("show credencial bibliotecario");
        driver.sendCommand("goto biblioteca");
        driver.sendCommand("move libro viejo");
        driver.sendCommand("goto sotano");
        driver.sendCommand("use baranda");
        driver.sendCommand("break ventana");

        assertEquals(GameState.WON, driver.getGameState());
    }

    private class ConcreteGameDriver implements GameDriver {
        Game game;

        @Override
        public void initGame(String jarPath) {
            GameBuilder ejercicioIt2Builder = new EjercicioIt2Builder();
            game = ejercicioIt2Builder.build();
        }

        @Override
        public String sendCommand(String cmd) {
            return game.command(cmd);
        }

        @Override
        public GameState getGameState() {
            return game.getGameState();
        }
    }
}

