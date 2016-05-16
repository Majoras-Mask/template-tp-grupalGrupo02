import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.game.Game;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBuilder openDoorBuilder = new OpenDoorBuilder();
        Game game = openDoorBuilder.build();

        Scanner input = new Scanner(System.in, "UTF-8");

        while (input.hasNext()) {
            System.out.print(game.doCommand(input.nextLine()));
        }
        System.out.println();
    }
}
