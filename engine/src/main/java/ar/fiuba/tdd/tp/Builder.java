package ar.fiuba.tdd.tp;

import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.commands.*;
import ar.fiuba.tdd.tp.conditions.*;
import ar.fiuba.tdd.tp.timer.*;
import ar.fiuba.tdd.tp.values.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by kevin on 06/06/16.
 */
public class Builder {


    private final GameConcrete game;

    public Builder(GameConcrete game) {
        this.game = game;
    }

    public ObjectInterface getObjectConcrete(String objectID) {
        return game.getObject(objectID);
    }

    private ObjectInterface createObjectConcrete(String objectID) {
        ObjectConcrete object = new ObjectConcrete(objectID);
        return object;
    }

    public ObjectInterface createPlayer(String playerID) {
        ObjectInterface objectConcrete = createObjectConcrete(playerID);
        game.addPlayer(objectConcrete);
        return objectConcrete;
    }

    public ObjectInterface createObject(String objectID) {
        ObjectInterface object = createObjectConcrete(objectID);
        game.addObject(object);
        return object;
    }

    /* Creation of actions */
    private void loadContainer(ActionContainer container, Action[] actions) {
        for (Action action: actions) {
            container.addAction(action);
        }
    }

    public Action createActionContainer(Action... actions) {
        ActionContainer container = new ActionContainer();
        loadContainer(container, actions);
        return container;
    }

    public Action createActionRandom(Action... actions) {
        ActionRandom container = new ActionRandom(game);
        loadContainer(container, actions);
        return container;
    }

    public Action createActionSetGameState(GameState gameState) {
        return new ActionSetGameState(game, gameState);
    }

    public Action createActionAddObject(String objectDescription, String objectToAddDescription) {
        ActionAddObject action = new ActionAddObject(new ValueConstant(objectDescription),
                new ValueConstant(objectToAddDescription));
        return action;
    }

    public Action createActionRemoveObject(String objectDescription, String objectToRemoveDescription) {
        ActionRemoveObject action = new ActionRemoveObject(new ValueConstant(objectDescription),
                new ValueConstant(objectToRemoveDescription));
        return action;
    }

    public Action createActionNull() {
        return new ActionNull();
    }

    public Action createActionSetProperty(String objectDescription, String property, String value) {
        ActionSetProperty action = new ActionSetProperty(new ValueConstant(objectDescription), new ValueConstant(property),
                new ValueConstant(value));
        return action;
    }

    public Action createActionAddTimer(Timer timer) {
        return new ActionAddTimer(timer, game);
    }

    /* Creation of conditions */
    public Condition createConditionAlwaysTrue() {
        return new ConditionAlwaysTrue();
    }

    public Condition createConditionCheckGameState(GameState gameState) {
        return new ConditionCheckGameState(game, gameState);
    }

    public Condition createConditionPropertyEquals(String objectDescription, String property, String value) {
        return new ConditionPropertyEquals(
                new ValueConstant(objectDescription),
                new ValueConstant(property),
                new ValueConstant(value)
        );
    }

    public Condition createConditionSizeEqual(String objectDescription, int size) {
        return new ConditionSize(new ValueConstant(objectDescription), size, new ConditionSizeOperationEqual());
    }

    public Condition createConditionSizeLessThanEqual(String objectDescription, int size) {
        Condition conditionLessThan = new ConditionSize(new ValueConstant(objectDescription), size, new ConditionSizeOperationLessThan());
        return conditionLessThan.or(createConditionSizeEqual(objectDescription,size));
    }

    public Condition createConditionSizeGreaterThan(String objectDescription, int size) {
        return createConditionSizeLessThanEqual(objectDescription,size).not();
    }

    public Condition createConditionGameHasObject(String objectDescription) {
        return new ConditionGameHasObject(new ValueConstant(objectDescription));
    }

    public Condition createConditionSameObject(String description1, String description2) {
        return new ConditionSameObject(new ValueConstant(description1), new ValueConstant(description2));
    }

    public Condition createConditionHasItem(String objectDescription, String item) {
        return new ConditionHasItem(new ValueConstant(objectDescription), new ValueConstant(item));
    }

    public Condition createConditionComparePropertyEqual(String object, String propertyObject, String other, String propertyOther) {
        return new ConditionPropertyEquals(new ValueConstant(object), new ValueConstant(propertyObject),
                new ValueFromProperty(new ValueConstant(other), new ValueConstant(propertyOther)));
    }

    /* Creation of timers */
    public TimerConcrete createTimerConcrete(int ticks) {
        TimerConcrete timerConcrete = new TimerConcrete(ticks);
        return timerConcrete;
    }

    public PeriodicTimer createPeriodicTimer(int ticks) {
        PeriodicTimer periodicTimer = new PeriodicTimer(ticks);
        return periodicTimer;
    }

    /* Creation of commands */
    public Command createCommandConcrete(String command) {
        CommandConcrete commandConcrete = new CommandConcrete(command);
        game.addCommand(commandConcrete);
        return commandConcrete;
    }

    public Command createCommandConcreteRegex(String commandRegex) {
        CommandConcreteRegex commandConcreteRegex = new CommandConcreteRegex(commandRegex);
        game.addCommand(commandConcreteRegex);
        return commandConcreteRegex;
    }

    /* Utils */
    public void addTimer(Timer timer) {
        game.addTimer(timer);
    }

}
