
import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.GameDriver;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MainTest {

    public GameDriver setup() {
        GameDriver driver = new ConcreteGameDriver();
        driver.initGame("");
        return driver;
    }

    @Test
    public void dummy() {
        GameDriver driver = setup();
        System.out.println(driver.sendCommand("goto acceso biblioteca"));
        System.out.println(driver.sendCommand("goto pasillo"));
        System.out.println(driver.sendCommand("goto salon 3"));
        System.out.println(driver.sendCommand("pick llave"));
        System.out.println(driver.sendCommand("goto pasillo"));
        System.out.println(driver.sendCommand("goto salon 1"));
        System.out.println(driver.sendCommand("move cuadro de barco"));
        System.out.println(driver.sendCommand("open caja fuerte"));
        System.out.println(driver.sendCommand("pick credencial"));
        System.out.println(driver.sendCommand("put foto credencial"));
        System.out.println(driver.sendCommand("goto pasillo"));
        System.out.println(driver.sendCommand("goto acceso biblioteca"));
        System.out.println(driver.sendCommand("show credencial bibliotecario"));

        assertEquals(0, 0);
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
    }
}

