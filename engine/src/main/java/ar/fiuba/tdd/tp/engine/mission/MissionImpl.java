package ar.fiuba.tdd.tp.engine.mission;

import ar.fiuba.tdd.tp.engine.commons.condition.Condition;

import java.util.List;

/**
 * An implementation of a {@link Mission} that is accomplished when all the winConditions are accomplished.
 * It also fails whenever any loseCondition is true
 */
public abstract class MissionImpl implements Mission {

    private final List<Condition> winConditions;
    private final List<Condition> loseConditions;

    public MissionImpl(List<Condition> winConditions, List<Condition> loseConditions) {
        this.winConditions = winConditions;
        this.loseConditions = loseConditions;
    }

    @Override
    public Boolean isAccomplished() {
        return this.winConditions.stream().allMatch(Condition::accomplished);
    }

    @Override
    public Boolean isFailed() {
        return this.loseConditions.stream().anyMatch(Condition::accomplished);
    }

}
