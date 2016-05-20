import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.player.Player;

public class BuildGame {

    public static void build(Game ejercicioIt2) {
        Player player = BuildPlayer.build(ejercicioIt2);
        player.setRoom(BuildScenario.build(ejercicioIt2));
    }

}
