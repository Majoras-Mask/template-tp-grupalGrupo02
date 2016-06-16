
public class Main {

}

/*
import ar.fiuba.tdd.tp.Game;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {

    public void run() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Game game = new GameBuilderElEscape2().build();
        game.startLoop();
        String playerID = game.getPlayerIDAvailable();
        while (true) {
            try {
                System.out.print(">");
                String str = br.readLine();
                String response = game.executeCommand(playerID, str);
                if (response != null) {
                    System.out.println(">" + response);
                }
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }
}
*/