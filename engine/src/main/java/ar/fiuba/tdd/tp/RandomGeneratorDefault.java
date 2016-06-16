package ar.fiuba.tdd.tp;

import java.util.Random;

/**
 * Created by kevin on 15/06/16.
 */
public class RandomGeneratorDefault implements RandomGenerator {
    private static Random random = new Random();

    @Override
    public int getRandom(int max) {
        return random.nextInt(max);
    }
}
