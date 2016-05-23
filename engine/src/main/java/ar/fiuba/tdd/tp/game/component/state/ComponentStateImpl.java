package ar.fiuba.tdd.tp.game.component.state;

public class ComponentStateImpl implements ComponentState {

    String value;

    public ComponentStateImpl(String value) {
        this.value = value;
    }

    @Override
    public void changeValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return value;
    }
}
