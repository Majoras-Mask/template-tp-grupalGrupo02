package ar.fiuba.tdd.tp.values;

/**
 * Created by kevin on 28/05/16.
 */
public class ValueConstant implements Value {

    private String value;

    public ValueConstant(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
