package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
import ar.fiuba.tdd.tp.game.player.Player;
import ar.fiuba.tdd.tp.game.player.action.resolver.ActionAbstract;

import java.util.List;
import java.util.regex.Pattern;

/**
 * An action that doesn't take any objects is called an "intransitive" action.
 * Examples of this type are: LOOK AROUND or SLEEP that expresses an action without
 * mentioning any objects.
 */
public abstract class NoObjectAction extends ActionAbstract {

    protected NoObjectAction(Player player, String pattern) {
        super(player,pattern);
    }

    @Override
    public String execute(String action) {
        if (satisfiesActionConstraints()) {
            return this.doExecute();
        }
        return "No cumple una condicion";
    }

    protected abstract String doExecute();
}
