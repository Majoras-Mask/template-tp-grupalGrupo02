package ar.fiuba.tdd.tp.conditions;

/**
 * Created by kevin on 28/05/16.
 */
public class ConditionSizeOperationEqual implements ConditionSizeOperation {
    @Override
    public boolean compare(int actualSize, int expectedSize) {
        return actualSize == expectedSize;
    }
}
