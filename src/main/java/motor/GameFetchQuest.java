package motor;

public class GameFetchQuest extends Game {

    public GameFetchQuest() {
    }

    public Game getInstance() {
        if (game == null) {
            game = new GameFetchQuest();
        }
        return game;
    }

    @Override
    public String getDescription() {
        return "Game Fetch Quest";
    }
}
