package ar.fiuba.tdd.tp;

import java.util.List;

/**
 * Created by kevin on 28/05/16.
 */
public interface ObjectInterface extends Container, Descriptible {
    List<ObjectInterface> getRelatedObjects();

    void addRelatedObject(ObjectInterface object);
}
