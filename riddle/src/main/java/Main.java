import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;

import java.util.Scanner;

@SuppressWarnings("CPD-START")
public class Main {
    private static final int PLAYERID = 1;

    public static void main(String[] args) {
        GameBuilder fetchQuestBuilder = new RiddleBuilder();
        Game game = fetchQuestBuilder.build();
        game.joinPlayer(PLAYERID);

        Scanner input = new Scanner(System.in, "UTF-8");

        while (input.hasNext()) {
            System.out.print(game.command(PLAYERID, input.nextLine()));
        }
        System.out.println();
    }
}