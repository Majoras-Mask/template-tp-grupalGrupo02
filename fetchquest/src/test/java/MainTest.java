import ar.fiuba.tdd.tp.engine.Game;
import org.junit.Test;

public class MainTest {

    @Test
    public void testWinGame() {
        Game fetchQuest = new FetchQuest();
        System.out.print(fetchQuest.command("pick stick"));
    }
}
