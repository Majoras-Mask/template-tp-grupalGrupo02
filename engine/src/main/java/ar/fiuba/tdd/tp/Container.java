package ar.fiuba.tdd.tp;

import java.util.List;

/**
 * Created by kevin on 28/05/16.
 */
public interface Container {
    void add(ObjectInterface object);

    void remove(ObjectInterface object);

    void removeAll();

    boolean hasObject(ObjectInterface object);

    List<ObjectInterface> getObjects();

    int getSize();
}
