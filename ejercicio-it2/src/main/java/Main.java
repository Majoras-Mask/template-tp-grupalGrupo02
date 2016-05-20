import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;

import java.util.Scanner;

@SuppressWarnings("CPD-START")
public class Main {
    public static void main(String[] args) {
        GameBuilder openDoorBuilder = new EjercicioIt2Builder();
        Game game = openDoorBuilder.build();

        Scanner input = new Scanner(System.in, "UTF-8");

        while (input.hasNext()) {
            System.out.print(game.command(input.nextLine()));
        }
        System.out.println();
    }
}
