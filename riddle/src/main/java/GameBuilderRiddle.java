import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditions.Condition;

@SuppressWarnings("CPD-START")
public class GameBuilderRiddle implements GameBuilder {

    private static final String PLAYER = "player";

    private static final String SHEEP = "sheep";
    private static final String WOLF = "wolf";
    private static final String CABBAGE = "cabbage";
    private static final String NORTH_SHORE = "north-shore";
    private static final String SOUTH_SHORE = "south-shore";

    private static final String CURRENT_SHORE = "current shore";

    private static final String RESPONSE_NOT_EXIST_OBJECT = "No existe ese objeto.";

    private static final String COMMAND_TAKE = "take (animal)";
    private static final String ANIMAL = "(animal)";
    private static final String RESPONSE_ALREADY_HAVE = "You can’t do that! You already have it.";
    private static final String RESPONSE_BOAT_FULL = "You can’t do that! The boat is full.";
    private static final String RESPONSE_OK = "Ok";

    private static final String COMMAND_LEAVE = "leave (animal)";
    private static final String RESPONSE_DONT_HAVE = "You don't have it";

    private static final String COMMAND_CROSS = "cross (shore)";
    private static final String SHORE = "(shore)";
    private static final String RESPONSE_CROSSED = "You have crossed!";
    private static final String RESPONSE_WOLF_SHEEP = "You can’t do that! The wolf will eat the sheep!";
    private static final String RESPONSE_SHEEP_CABBAGE = "You can’t do that! The sheep will eat the wolf!";
    private static final String RESPONSE_ALREADY_THERE = "You are there already.";

    private void createObjects(Builder builder) {
        ObjectInterface sheep = builder.createObject(SHEEP);
        ObjectInterface wolf = builder.createObject(WOLF);
        ObjectInterface cabbage = builder.createObject(CABBAGE);

        builder.createObject(NORTH_SHORE);
        ObjectInterface southShore = builder.createObject(SOUTH_SHORE);

        southShore.add(sheep);
        southShore.add(wolf);
        southShore.add(cabbage);
    }

    private void createPlayers(Builder builder) {
        ObjectInterface player = builder.createPlayer(PLAYER);
        player.setProperty(CURRENT_SHORE, SOUTH_SHORE);
    }

    @Override
    public Game build() {
        GameConcrete gameConcrete = new GameConcrete();
        Builder builder = new Builder(gameConcrete);

        createObjects(builder);
        createPlayers(builder);

        createCommandCross(builder);
        createCommandTake(builder);
        createCommandLeave(builder);

        setWinCondition(gameConcrete, builder);

        return gameConcrete;
    }

    private void setWinCondition(GameConcrete gameConcrete, Builder builder) {
        Condition conditionShoreSize = builder.createConditionSizeEqual(NORTH_SHORE, 3);
        gameConcrete.setWinCondition(PLAYER, conditionShoreSize);
    }

    private void createCommandLeave(Builder builder) {
        Command commandLeave = builder.createCommandConcreteRegex(COMMAND_LEAVE);

        Condition conditionValidAnimal = createConditionValidAnimal(builder, ANIMAL);

        commandLeave.setCondition(
                conditionValidAnimal.not(),
                builder.createActionNull(),
                RESPONSE_NOT_EXIST_OBJECT
        );

        Condition conditionPlayerHasAnimal = builder.createConditionHasItem(PLAYER, ANIMAL);

        commandLeave.setCondition(
                conditionPlayerHasAnimal.not(),
                builder.createActionNull(),
                RESPONSE_DONT_HAVE
        );

        setConditionLeave(commandLeave, SOUTH_SHORE, builder, conditionPlayerHasAnimal);
        setConditionLeave(commandLeave, NORTH_SHORE, builder, conditionPlayerHasAnimal);

    }

    private void setConditionLeave(Command commandCross, String shore, Builder builder, Condition conditionPlayerHasAnimal) {
        Condition conditionX_Shore = builder.createConditionPropertyEquals(PLAYER, CURRENT_SHORE, shore);

        ActionContainer actionLeaveAnimalX = new ActionContainer();
        actionLeaveAnimalX.addAction(builder.createActionAddObject(shore, ANIMAL));
        actionLeaveAnimalX.addAction(builder.createActionRemoveObject(PLAYER, ANIMAL));

        commandCross.setCondition(
                conditionX_Shore.and(conditionPlayerHasAnimal),
                actionLeaveAnimalX,
                RESPONSE_OK
        );
    }

    private Condition createConditionValidAnimal(Builder builder, String objectID) {
        Condition conditionValidAnimal = builder.createConditionSameObject(objectID, SHEEP).or(
                builder.createConditionSameObject(objectID, WOLF).or(
                        builder.createConditionSameObject(objectID, CABBAGE)
                )
        );
        return conditionValidAnimal;
    }

    private void createCommandTake(Builder builder) {
        Command commandTake = builder.createCommandConcreteRegex(COMMAND_TAKE);

        Condition conditionValidAnimal = createConditionValidAnimal(builder, ANIMAL);

        commandTake.setCondition(
                conditionValidAnimal.not(),
                builder.createActionNull(),
                RESPONSE_NOT_EXIST_OBJECT
        );

        Condition conditionPlayerHasAnimal = builder.createConditionHasItem(PLAYER, ANIMAL);

        commandTake.setCondition(
                conditionPlayerHasAnimal,
                builder.createActionNull(),
                RESPONSE_ALREADY_HAVE
        );

        Condition conditionPlayerSize = builder.createConditionSizeGreaterThan(PLAYER, 0);

        commandTake.setCondition(
                conditionPlayerSize,
                builder.createActionNull(),
                RESPONSE_BOAT_FULL
        );

        setConditionShoreAndThereIsAnimal(builder, commandTake, SOUTH_SHORE);
        setConditionShoreAndThereIsAnimal(builder, commandTake, NORTH_SHORE);
    }

    private void setConditionShoreAndThereIsAnimal(Builder builder, Command commandTake, String shore) {
        Condition conditionCorrectShore = builder.createConditionPropertyEquals(PLAYER, CURRENT_SHORE, shore);

        Condition conditionSouthShoreHasAnimal = builder.createConditionHasItem(shore, ANIMAL);
        Condition conditionCorrectShoreAndThereIsAnimal = conditionCorrectShore.and(conditionSouthShoreHasAnimal);

        ActionContainer actionTakeAnimalSouth = new ActionContainer();
        actionTakeAnimalSouth.addAction(builder.createActionAddObject(PLAYER, ANIMAL));
        actionTakeAnimalSouth.addAction(builder.createActionRemoveObject(shore, ANIMAL));

        commandTake.setCondition(
                conditionCorrectShoreAndThereIsAnimal,
                actionTakeAnimalSouth,
                RESPONSE_OK);
    }

    private Condition createConditionValidShore(Builder builder, String objectID) {
        Condition conditionValidShore = builder.createConditionSameObject(objectID, NORTH_SHORE).or(
                builder.createConditionSameObject(objectID, SOUTH_SHORE));
        return conditionValidShore;
    }

    private void createCommandCross(Builder builder) {
        Command commandCross = builder.createCommandConcreteRegex(COMMAND_CROSS);

        Condition conditionValidShore = createConditionValidShore(builder, SHORE);

        commandCross.setCondition(
                conditionValidShore.not(),
                builder.createActionNull(),
                RESPONSE_NOT_EXIST_OBJECT
        );

        setConditionSameShore(builder, SOUTH_SHORE, commandCross);
        setConditionSameShore(builder, NORTH_SHORE, commandCross);

        setConditionCantCrossShore(commandCross, SOUTH_SHORE, builder);
        setConditionCantCrossShore(commandCross, NORTH_SHORE, builder);

        setConditionCurrentShore(builder, SOUTH_SHORE, commandCross);
        setConditionCurrentShore(builder, NORTH_SHORE, commandCross);
    }

    private void setConditionCurrentShore(Builder builder, String shore, Command commandCross) {
        Condition conditionCurrent = builder.createConditionSameObject(shore, SHORE);

        commandCross.setCondition(
                conditionCurrent,
                builder.createActionSetProperty(PLAYER, CURRENT_SHORE, shore),
                RESPONSE_CROSSED
        );
    }

    private void setConditionCantCrossShore(Command commandCross, String shore, Builder builder) {
        Condition conditionCurrent = builder.createConditionPropertyEquals(PLAYER, CURRENT_SHORE, shore);

        Condition conditionSouthHasSheep = builder.createConditionHasItem(shore,SHEEP);
        Condition conditionSouthHasWolf = builder.createConditionHasItem(shore, WOLF);
        Condition conditionSouthHasCabbage = builder.createConditionHasItem(shore, CABBAGE);

        Condition conditionCantCrossWolfSheep = conditionCurrent.and(conditionSouthHasSheep.and(conditionSouthHasWolf));
        Condition conditionCantCrossSheepCabbage = conditionCurrent.and(conditionSouthHasSheep.and(conditionSouthHasCabbage));

        commandCross.setCondition(
                conditionCantCrossWolfSheep,
                builder.createActionNull(),
                RESPONSE_WOLF_SHEEP
        );

        commandCross.setCondition(
                conditionCantCrossSheepCabbage,
                builder.createActionNull(),
                RESPONSE_SHEEP_CABBAGE
        );
    }

    private void setConditionSameShore(Builder builder, String shore, Command commandCross) {
        Condition conditionCurrent = builder.createConditionSameObject(SHORE, shore);
        Condition conditionSameShoreProp = builder.createConditionPropertyEquals(PLAYER, CURRENT_SHORE, shore);
        Condition conditionSameShore = conditionCurrent.and(conditionSameShoreProp);
        commandCross.setCondition(
                conditionSameShore,
                builder.createActionNull(),
                RESPONSE_ALREADY_THERE
        );
    }
}
