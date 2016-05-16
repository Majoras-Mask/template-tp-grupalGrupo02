
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.game.Game;
import org.junit.Test;

public class MainTest {

    @Test
    public void testWinGame() {
        GameBuilder fetchQuestBuilder = new FetchQuestBuilder();
        Game fetchQuest = fetchQuestBuilder.build();
        System.out.print(fetchQuest.doCommand("pick stick"));
    }
}
