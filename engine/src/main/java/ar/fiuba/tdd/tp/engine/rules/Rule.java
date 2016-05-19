package ar.fiuba.tdd.tp.engine.rules;

public interface Rule {

    boolean satisfiesRule();

    String reasonsOfRuleFail();
}
