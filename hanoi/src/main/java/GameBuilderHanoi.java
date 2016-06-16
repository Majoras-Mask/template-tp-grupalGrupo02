import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditions.Condition;

/**
 * Created by kevin on 06/06/16.
 */
@SuppressWarnings("CPD-START")
public class GameBuilderHanoi implements GameBuilder {
    private static final String PLAYER = "player";

    private static final String STACK1 = "stack1";
    private static final String STACK2 = "stack2";
    private static final String STACK3 = "stack3";
    private static final String DISCO1 = "disco1";
    private static final String DISCO2 = "disco2";
    private static final String DISCO3 = "disco3";

    private static final String COMMAND_WHAT_CAN_I_DO = "What can i do with stack";
    private static final String COMMAND_WHAT_CAN_I_DO_RESPONSE = "You can check top/move top";

    private static final String COMMAND_CHECK_TOP = "check top (stack)";
    private static final String STACK = "(stack)";
    private static final String RESPONSE_NOT_EXIST_OBJECT = "No existe ese objeto.";
    private static final String SIZE_ZERO = "The stack is empty";
    private static final String SIZE_OF_TOP_1 = "Size of top is 1";
    private static final String SIZE_OF_TOP_2 = "Size of top is 2";
    private static final String SIZE_OF_TOP_3 = "Size of top is 3";

    private static final String COMMAND_MOVE_TOP = "move top (stackfrom) (stackto)";
    private static final String STACK_FROM = "(stackfrom)";
    private static final String STACK_TO = "(stackto)";
    private static final String RESPONSE_INVALID_STACKS = "No existe el stack.";
    private static final String RESPONSE_MOVED = "Moved!";
    private static final String CANT_BE_MOVED = "Can't be moved.";

    private static final String COMMAND_HELP = "help";
    private static final String MESSAGE_HELP = "Commandos: check top stackI, move top stackI stackJ, what can i do with stack";

    private void createObjects(Builder builder) {
        ObjectInterface stack1 = builder.createObject(STACK1);
        builder.createObject(STACK2);
        builder.createObject(STACK3);

        ObjectInterface disco1 = builder.createObject(DISCO1);
        ObjectInterface disco2 = builder.createObject(DISCO2);
        ObjectInterface disco3 = builder.createObject(DISCO3);

        stack1.add(disco1);
        stack1.add(disco2);
        stack1.add(disco3);
    }

    private void createPlayers(Builder builder) {
        builder.createPlayer(PLAYER);
    }

    private void createCommandWhatCanIDo(Builder builder) {
        Command commandWhatCanIDo = builder.createCommandConcreteRegex(COMMAND_WHAT_CAN_I_DO);
        commandWhatCanIDo.setCondition(builder.createConditionAlwaysTrue(),builder.createActionNull(),
                COMMAND_WHAT_CAN_I_DO_RESPONSE);

    }

    private Condition createConditionStackValid(Builder builder, String objectID) {
        Condition conditionStackValid = builder.createConditionSameObject(objectID, STACK1).or(
                builder.createConditionSameObject(objectID, STACK2).or(
                        builder.createConditionSameObject(objectID, STACK3)
                )
        );
        return conditionStackValid;
    }

    private void createCommandCheckTopStack(Builder builder) {
        Command commandCheckTopStack = builder.createCommandConcreteRegex(COMMAND_CHECK_TOP);

        Condition conditionStackValid = createConditionStackValid(builder, STACK);

        commandCheckTopStack.setCondition(
                conditionStackValid.not(),
                builder.createActionNull(),
                RESPONSE_NOT_EXIST_OBJECT
        );

        commandCheckTopStack.setCondition(
                builder.createConditionSizeEqual(STACK, 0),
                builder.createActionNull(),
                SIZE_ZERO
        );

        commandCheckTopStack.setCondition(
                builder.createConditionHasItem(STACK, DISCO1),
                builder.createActionNull(),
                SIZE_OF_TOP_1
        );

        commandCheckTopStack.setCondition(
                builder.createConditionHasItem(STACK, DISCO2),
                builder.createActionNull(),
                SIZE_OF_TOP_2
        );

        commandCheckTopStack.setCondition(
                builder.createConditionHasItem(STACK, DISCO3),
                builder.createActionNull(),
                SIZE_OF_TOP_3
        );
    }

    private void createCommandMoveTopStack(Builder builder) {
        Command command = builder.createCommandConcreteRegex(COMMAND_MOVE_TOP);
        Condition conditionStackFromValid = createConditionStackValid(builder, STACK_FROM);
        Condition conditionStackToValid = createConditionStackValid(builder, STACK_TO);
        Condition conditionBothStackValid = conditionStackFromValid.and(conditionStackToValid);

        command.setCondition(
                conditionBothStackValid.not(),
                builder.createActionNull(),
                RESPONSE_INVALID_STACKS
        );
        
        command.setCondition(
                builder.createConditionSizeEqual(STACK_FROM, 0),
                builder.createActionNull(),
                SIZE_ZERO
        );
        
        command.setCondition(
                builder.createConditionHasItem(STACK_FROM, DISCO1),
                builder.createActionContainer(
                        builder.createActionAddObject(STACK_TO, DISCO1),
                        builder.createActionRemoveObject(STACK_FROM, DISCO1)
                ),
                RESPONSE_MOVED
        );

        command.setCondition(
                builder.createConditionHasItem(STACK_FROM, DISCO2).and(
                        builder.createConditionHasItem(STACK_TO, DISCO1).not()
                ),
                builder.createActionContainer(
                        builder.createActionAddObject(STACK_TO, DISCO2),
                        builder.createActionRemoveObject(STACK_FROM, DISCO2)
                ),
                RESPONSE_MOVED
        );

        command.setCondition(
                builder.createConditionHasItem(STACK_FROM, DISCO3).and(
                        builder.createConditionSizeEqual(STACK_TO, 0)
                ),
                builder.createActionContainer(
                        builder.createActionAddObject(STACK_TO, DISCO3),
                        builder.createActionRemoveObject(STACK_FROM, DISCO3)
                ),
                RESPONSE_MOVED
        );

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionNull(),
                CANT_BE_MOVED
        );

    }

    private void createCommandHelp(Builder builder) {
        Command command = builder.createCommandConcreteRegex(COMMAND_HELP);
        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionNull(),
                MESSAGE_HELP
        );
    }

    private void setWinCondition(GameConcrete game, Builder builder) {
        Condition condition = builder.createConditionSizeEqual(STACK3, 3);
        game.setWinCondition(PLAYER, condition);
    }

    public Game build() {
        GameConcrete gameConcrete = new GameConcrete();
        Builder builder = new Builder(gameConcrete);

        createObjects(builder);
        createPlayers(builder);

        createCommandWhatCanIDo(builder);
        createCommandCheckTopStack(builder);
        createCommandMoveTopStack(builder);
        createCommandHelp(builder);

        setWinCondition(gameConcrete, builder);

        return gameConcrete;
    }

}
