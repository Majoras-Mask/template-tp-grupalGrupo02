import ar.fiuba.tdd.tp.Game;
import ar.fiuba.tdd.tp.GameBuilder;
import ar.fiuba.tdd.tp.GameConcrete;
import ar.fiuba.tdd.tp.ObjectConcrete;
import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.commands.CommandConcrete;
import ar.fiuba.tdd.tp.commands.CommandConcreteRegex;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.conditions.ConditionAlwaysTrue;
import ar.fiuba.tdd.tp.conditions.ConditionHasItem;
import ar.fiuba.tdd.tp.conditions.ConditionPropertyEquals;
import ar.fiuba.tdd.tp.values.ValueConstant;

@SuppressWarnings("CPD-START")
public class GameBuilderOpenDoorMultiplayer implements GameBuilder {
    private static final String PLAYER1_NAME = "player1";
    private static final String PLAYER2_NAME = "player2";
    private static final String KEY_NAME = "key";
    private static final String ROOM1_NAME = "room";
    private static final String ROOM2_NAME = "room2";
    private static final String LOOKAROUND_COMMAND = "look around";
    private static final String MESSAGE_LOOK_AROUND_WITH_KEY = "There's a key and a door in the room.";
    private static final String MESSAGE_LOOK_AROUND_WITHOUT_KEY = "There's a door in the room";
    private static final String PICK_KEY_COMMAND = "pick key";
    private static final String CURRENT_PLAYER = "(player)";
    private static final String MESSAGE_PICK_KEY_OK = "There you go!";
    private static final String MESSAGE_PICK_KEY_FAIL = "I'm sorry, no key in the room";
    private static final String OPEN_DOOR_COMMAND = "open door";
    private static final String PROPERTY_ROOM = "currentRoom";
    private static final String MESSAGE_OPEN_DOOR_OK = "You enter room2";
    private static final String MESSAGE_OPEN_DOOR_FAIL = "Ey! Where do you go?! Room 2 is locked.";

    private static final String COMMAND_HELP = "help";
    private static final String MESSAGE_HELP = "OpenDoor Multiplayer. Solo dos jugadores. Intenta salir de la habitacion.";

    private Game crearGame() {
        GameConcrete game = new GameConcrete();
        /* Agregado de players */
        ObjectConcrete player1 = new ObjectConcrete(PLAYER1_NAME);
        ObjectConcrete player2 = new ObjectConcrete(PLAYER2_NAME);
        game.addPlayer(player1);
        game.addPlayer(player2);

        /* Creacion de objetos */
        ObjectConcrete key = new ObjectConcrete(KEY_NAME);
        ObjectConcrete room1 = new ObjectConcrete(ROOM1_NAME);
        ObjectConcrete room2 = new ObjectConcrete(ROOM2_NAME);
        game.addObject(key);
        game.addObject(room1);
        game.addObject(room2);
        room1.add(key);

        /* Creacion de comandos */
        CommandConcrete commandLookAround = new CommandConcrete(LOOKAROUND_COMMAND);
        Condition conditionRooom1HasKey = new ConditionHasItem(new ValueConstant(ROOM1_NAME), new ValueConstant(KEY_NAME));
        commandLookAround.setCondition(
                conditionRooom1HasKey,
                new ActionNull(),
                MESSAGE_LOOK_AROUND_WITH_KEY
        );
        commandLookAround.setCondition(
                new ConditionAlwaysTrue(),
                new ActionNull(),
                MESSAGE_LOOK_AROUND_WITHOUT_KEY
        );
        game.addCommand(commandLookAround);

        CommandConcrete commandPickKey = new CommandConcrete(PICK_KEY_COMMAND);
        ActionContainer actionPick = new ActionContainer();
        actionPick.addAction(new ActionAddObject(new ValueConstant(CURRENT_PLAYER), new ValueConstant(KEY_NAME)));
        actionPick.addAction(new ActionRemoveObject(new ValueConstant(ROOM1_NAME), new ValueConstant(KEY_NAME)));
        commandPickKey.setCondition(
                conditionRooom1HasKey,
                actionPick,
                MESSAGE_PICK_KEY_OK
        );

        commandPickKey.setCondition(
                conditionRooom1HasKey.not(),
                new ActionNull(),
                MESSAGE_PICK_KEY_FAIL
        );
        game.addCommand(commandPickKey);

        CommandConcrete commandOpenDoor = new CommandConcrete(OPEN_DOOR_COMMAND);
        Condition conditionPlayerHasKey = new ConditionHasItem(new ValueConstant(CURRENT_PLAYER), new ValueConstant(KEY_NAME));
        commandOpenDoor.setCondition(
                conditionPlayerHasKey,
                new ActionSetProperty(new ValueConstant(CURRENT_PLAYER), new ValueConstant(PROPERTY_ROOM), new ValueConstant(ROOM2_NAME)),
                MESSAGE_OPEN_DOOR_OK
        );
        commandOpenDoor.setCondition(
                conditionPlayerHasKey.not(),
                new ActionNull(),
                MESSAGE_OPEN_DOOR_FAIL
        );
        game.addCommand(commandOpenDoor);

        Condition winConditionPlayer1 = new ConditionPropertyEquals(new ValueConstant(PLAYER1_NAME), new ValueConstant(PROPERTY_ROOM),
                new ValueConstant(ROOM2_NAME));
        Condition winConditionPlayer2 = new ConditionPropertyEquals(new ValueConstant(PLAYER2_NAME), new ValueConstant(PROPERTY_ROOM),
                new ValueConstant(ROOM2_NAME));

        game.setWinCondition(PLAYER1_NAME, winConditionPlayer1);
        game.setWinCondition(PLAYER2_NAME, winConditionPlayer2);

        Command command = new CommandConcreteRegex(COMMAND_HELP);
        game.addCommand(command);
        command.setCondition(
                new ConditionAlwaysTrue(),
                new ActionNull(),
                MESSAGE_HELP
        );


        return game;
    }

    @Override
    public Game build() {
        return crearGame();
    }

}
