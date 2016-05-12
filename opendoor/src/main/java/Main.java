import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        GameBuilder openDoorBuilder = new OpenDoorBuilder();
        Game game = openDoorBuilder.build();

        Scanner input = new Scanner(System.in);

        while (input.hasNext()) {
            System.out.print(game.command(input.nextLine()));
        }
        System.out.println();
    }
}
