import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import org.junit.Test;

public class MainTest {

    @Test
    public void testWinGame() {
        GameBuilder fetchQuestBuilder = new FetchQuestBuilder();
        Game fetchQuest = fetchQuestBuilder.build();
        System.out.print(fetchQuest.command("pick stick"));
    }
}
