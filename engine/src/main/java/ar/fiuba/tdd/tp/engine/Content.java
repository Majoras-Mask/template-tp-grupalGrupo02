package ar.fiuba.tdd.tp.engine;

/**
 * Created by manuelcruz on 09/05/2016.
 */
public abstract class Content extends Container {
    protected Container container;

    Content() {
        super();
        container = null;
    }

    Content(String name) {
        super(name);
        container = null;
    }

    protected void setContainer(Container container) {
        this.container = container;
    }

    protected boolean isContained() {
        return container != null;
    }

    public Container getContainer() {
        return container;
    }
}