package ar.fiuba.tdd.tp;

/**
 * Created by kevin on 05/06/16.
 */
public class ThreadUpdater extends Thread {

    private Game game;
    private long timeTick;

    ThreadUpdater(Game game, long timeTick) {
        this.game = game;
        this.timeTick = timeTick;
    }

    @Override
    public void run() {
        while (game.getGameState() == GameState.Running) {
            long startTime = System.currentTimeMillis();
            game.update();
            long timeElapsed = System.currentTimeMillis() - startTime;

            if (timeElapsed < timeTick) {
                try {
                    sleep(timeTick - timeElapsed);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
