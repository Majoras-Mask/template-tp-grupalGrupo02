package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 09/05/2016.
 */
public class Content extends Container {
    protected Container container;

    public Content(String name) {
        super(name);
        container = null;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public boolean isContained() {
        return container != null;
    }

    public Container getContainer() {
        return container;
    }
}