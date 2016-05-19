package ar.fiuba.tdd.tp.engine.commands.content;

/**
 * Created by manuelcruz on 17/05/2016.
 */
public interface CommandValidator {
    boolean check(String[] params);
}
