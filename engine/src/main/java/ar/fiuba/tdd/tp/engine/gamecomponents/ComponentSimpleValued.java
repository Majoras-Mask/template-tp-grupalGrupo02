package ar.fiuba.tdd.tp.engine.gamecomponents;

public class ComponentSimpleValued extends ComponentSimple {
    private int value;

    public ComponentSimpleValued(String name) {
        super(name);
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
