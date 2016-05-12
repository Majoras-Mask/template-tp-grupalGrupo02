package ar.fiuba.tdd.tp.engine.elements;

public class Content extends Container {
    protected Container container;

    public Content(String name) {
        super(name);
        container = null;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Container getContainer() {
        return container;
    }
}