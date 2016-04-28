package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;
import ar.fiuba.tdd.tp.motor.games.Engine;
import ar.fiuba.tdd.tp.motor.games.EngineFactoryConcrete;

import java.io.BufferedReader;
import java.io.InputStreamReader;



public class Main {
    public static void main(String[] args) {
        System.out.println("This is just an engine!");
    }
}

/*
public class Main {
    public static void main(String[] args) {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Engine engine = EngineFactoryConcrete.getInstance().createEngineTreasureHunt();
        while (true) {
            try {
                System.out.print(">");
                String str = br.readLine();
                String response = engine.request(str);
                if (response != null) {
                    System.out.println(">" + response);
                }
            } catch ( Exception e) {
                e.printStackTrace();
            }
        }
    }
}
*/