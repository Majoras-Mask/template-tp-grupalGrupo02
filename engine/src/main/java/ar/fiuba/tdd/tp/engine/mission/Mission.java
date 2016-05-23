package ar.fiuba.tdd.tp.engine.mission;

public interface Mission {

    Boolean isAccomplished();

    Boolean isFailed();

    String finishedMessage();
}
