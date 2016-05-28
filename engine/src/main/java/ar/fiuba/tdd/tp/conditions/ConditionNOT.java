package ar.fiuba.tdd.tp.conditions;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionNOT implements Condition {

    private Condition condition;

    public ConditionNOT(Condition condition) {
        this.condition = condition;
    }
    
    @Override
    public boolean check() {
        return !condition.check();
    }
}
