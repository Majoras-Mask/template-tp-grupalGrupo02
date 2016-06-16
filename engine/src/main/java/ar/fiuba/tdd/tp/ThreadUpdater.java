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
            try {
                sleep(timeTick);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            long timeElapsed = System.currentTimeMillis() - startTime;
            game.update(timeElapsed);
        }
    }
}
