package ar.fiuba.tdd.tp;

/**
 * Created by kevin on 28/05/16.
 */
public interface Descriptible {
    String getDescription();

    void setProperty(String property, String value);

    String getProperty(String property);
}
