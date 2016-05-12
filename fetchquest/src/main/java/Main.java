import ar.fiuba.tdd.tp.engine.GameBuilder;

public class Main {
    public static void main(String[] args) {
        GameBuilder fetchQuestBuilder = new FetchQuestBuilder();
        fetchQuestBuilder.build();
    }
}
