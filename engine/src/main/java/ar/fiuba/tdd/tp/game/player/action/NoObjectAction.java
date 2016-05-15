package ar.fiuba.tdd.tp.game.player.action;

/**
 * An action that doesn't take any objects is called an "intransitive" action.
 * Examples of this type are: LOOK AROUND or SLEEP that expresses an action without
 * mentioning any objects.
 */
public interface NoObjectAction extends Action {

    String execute();

}
