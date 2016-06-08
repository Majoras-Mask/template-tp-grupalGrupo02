/*
import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.actions.ActionAddObject;
import ar.fiuba.tdd.tp.actions.ActionRemoveObject;
import ar.fiuba.tdd.tp.actions.ActionSetProperty;
import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.conditions.ConditionHasItem;
import ar.fiuba.tdd.tp.values.ValueConstant;
import ar.fiuba.tdd.tp.values.ValueFromProperty;


@SuppressWarnings("CPD-START")
public class GameBuilderElEscape2 implements GameBuilder {

    private static final int MAX_ITEMS = 4;

    private static final String CURRENT_PLAYER = "(player)";
    private static final String PLAYER1 = "player1";
    private static final String PLAYER2 = "player2";
    private static final String PLAYER3 = "player3";
    private static final String PLAYER4 = "player4";
    private static final String NPC_BIBLIOTECARIO = "bibliotecario";

    private static final String PROPERTY_DORMIDO = "dormido";
    private static final String VALUE_DORMIDO_SI = "si-dormido";
    private static final String VALUE_DORMIDO_NO = "no-dormido";

    private static final String PROPERTY_ROOM = "room";
    private static final String PROPERTY_INGRESO_A_LA_BIBLIOTECA = "ingreso-a-la-biblioteca";
    private static final String VALUE_INGRESO_A_LA_BIBLIOTECA_SI = "si-ingreso";
    private static final String VALUE_INGRESO_A_LA_BIBLIOTECA_NO = "no-ingreso";

    private static final String PROPERTY_PICKEABLE = "pickeable";
    private static final String VALUE_PICKEABLE_SI = "si-pickeable";

    private static final String PROPERTY_AUTENTICADO = "autenticado";
    private static final String VALUE_AUTENTICADO_SI = "ok";
    private static final String VALUE_AUTENTICADO_NO = "no";

    private static final String PROPERTY_USO_ESCALERA = "uso-escalera";
    private static final String VALUE_USO_ESCALERA_SI = "si-uso";
    private static final String VALUE_USO_ESCALERA_NO = "no-uso";

    private static final String PROPERTY_MOSTRO_CREDENCIAL_INVALIDA = "credencial-invalida";
    private static final String VALUE_MOSTRO_CREDENCIAL_INVALIDA_SI = "si-credencial-invalida";
    private static final String VALUE_MOSTRO_CREDENCIAL_INVALIDA_NO = "no-credencial-invalida";

    private static final String ROOM_PASILLO = "pasillo";
    private static final String ROOM_SALON1 = "salon1";
    private static final String ROOM_SALON2 = "salon2";
    private static final String ROOM_SALON3 = "salon3";
    private static final String ROOM_BIBLIOTECA_ACCESO = "biblioteca-acceso";
    private static final String ROOM_BIBLIOTECA = "biblioteca";
    private static final String ROOM_SOTANO = "sotano";
    private static final String ROOM_SOTANO_ABAJO = "sotano-abajo";
    private static final String ROOM_AFUERA = "afuera";

    private static final String COMMAND_PICK_OBJECT = "pick (object)";
    private static final String CURRENT_OBJECT = "(object)";
    private static final String RESPONSE_COMMAND_PICK_NOT_PICKEABLE = "Not pickeable.";
    private static final String RESPONSE_COMMAND_PICK_NO_OBJECT_IN_CURRENT_ROOM = "No such object in the room";
    private static final String RESPONSE_COMMAND_PICK_SUCCESFUL = "Picked!";
    private static final String RESPONSE_COMMAND_PICK_FULL_ITEMS = "Your invetory is full";

    private static final String RESPONSE_COMMAND_GOTO_NOT_IN_NEIGHBOR_ROOM = "You can't do it";
    private static final String RESPONSE_COMMAND_GOTO_SUCCESSFUL = "You are now in ";
    private static final String COMMAND_GOTO = "goto ";
    private static final String RESPONSE_COMMAND_GOTO_CANT_GO_BIBLIOTECA = "You can't go to the library.";
    private static final String COMMAND_OPEN_CAJA_FUERTE = "open CajaFuerte using Llave";
    private static final String OBJECT_LLAVE = "llave";
    private static final String RESPONSE_COMMAND_OPEN_NOT_KEY = "You don't have the key.";
    private static final String OBJECT_CAJA_FUERTE = "CajaFuerte";
    private static final String PROPERTY_FUE_ABIERTA = "";
    private static final String VALUE_FUE_ABIERTA_SI = "";
    private static final String RESPONSE_COMMAND_OPEN_WAS_OPEN = "It was opened.";
    private static final String RESPONSE_COMMAND_OPEN_NO_BOX = "There isnt box in the room";
    private static final String OBJECT_CREDENCIAL = "credencial";
    private static final String RESPONSE_COMMAND_OPEN_OK = "A credencial was dropped.";

    private GameConcrete gameConcrete = new GameConcrete();
    private Builder builder = new Builder(gameConcrete);

    private void createWinConditionForPlayer(String playerID) {
        Condition condition = builder.createConditionPropertyEquals(playerID, PROPERTY_ROOM, ROOM_AFUERA);
        gameConcrete.setWinCondition(playerID, condition);
    }

    private void createWinConditions() {
        createWinConditionForPlayer(PLAYER1);
        createWinConditionForPlayer(PLAYER2);
        createWinConditionForPlayer(PLAYER3);
        createWinConditionForPlayer(PLAYER4);
    }

    private void createLostConditionForPlayer(String playerID) {
        // Primera condición: Si ingreso a la biblioteca alguna vez, y no se autentifico, y se encuentra en el mismo lugar
        // que el bibliotecario, entonces se pierde.
        Condition seEncontroConBibliotecario;
        seEncontroConBibliotecario =
                builder.createConditionPropertyEquals(playerID, PROPERTY_INGRESO_A_LA_BIBLIOTECA, VALUE_INGRESO_A_LA_BIBLIOTECA_SI);
        seEncontroConBibliotecario =
                seEncontroConBibliotecario.and(
                        builder.createConditionPropertyEquals(playerID, PROPERTY_AUTENTICADO, VALUE_AUTENTICADO_NO));
        seEncontroConBibliotecario =
                seEncontroConBibliotecario.and(
                        builder.createConditionComparePropertyEqual(playerID, PROPERTY_ROOM, NPC_BIBLIOTECARIO, PROPERTY_ROOM));

        // Otra condición: Si uso la escalera, pierde.
        Condition usoEscalera = builder.createConditionPropertyEquals(playerID, PROPERTY_USO_ESCALERA, VALUE_USO_ESCALERA_SI);

        // TODO: Condicion bajo sin martillo.

        Condition lostCondition = seEncontroConBibliotecario.or(usoEscalera);
        gameConcrete.setLostCondition(playerID, lostCondition);
    }

    private void createLostCondition() {
        createLostConditionForPlayer(PLAYER1);
        createLostConditionForPlayer(PLAYER2);
        createLostConditionForPlayer(PLAYER3);
        createLostConditionForPlayer(PLAYER4);
    }

    private void createPlayer(String playerID) {
        ObjectInterface player = builder.createPlayer(playerID);
        player.setProperty(PROPERTY_ROOM, ROOM_PASILLO);
        player.setProperty(PROPERTY_USO_ESCALERA, VALUE_USO_ESCALERA_NO);
        player.setProperty(PROPERTY_AUTENTICADO, VALUE_AUTENTICADO_NO);
        player.setProperty(PROPERTY_MOSTRO_CREDENCIAL_INVALIDA, VALUE_MOSTRO_CREDENCIAL_INVALIDA_NO);
    }

    private void createPlayers() {
        createPlayer(PLAYER1);
        createPlayer(PLAYER2);
        createPlayer(PLAYER3);
        createPlayer(PLAYER4);
    }

    private void createCommandPick() {
        Command command = builder.createCommandConcreteRegex(COMMAND_PICK_OBJECT);
        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_OBJECT, PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI).not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_PICK_NOT_PICKEABLE
        );

        command.setCondition(
                new ConditionHasItem(new ValueFromProperty(new ValueConstant(CURRENT_PLAYER), new ValueConstant(PROPERTY_ROOM)),
                        new ValueConstant(CURRENT_OBJECT)).not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_PICK_NO_OBJECT_IN_CURRENT_ROOM
        );

        command.setCondition(
                builder.createConditionComparePropertyEqual(
                        CURRENT_PLAYER, PROPERTY_ROOM, CURRENT_OBJECT, PROPERTY_ROOM
                ).not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_PICK_NO_OBJECT_IN_CURRENT_ROOM
        );

        command.setCondition(
                builder.createConditionSizeLessThanEqual(CURRENT_PLAYER, MAX_ITEMS).not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_PICK_FULL_ITEMS
        );

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionContainer(
                        builder.createActionAddObject(CURRENT_PLAYER, CURRENT_OBJECT),
                        new ActionRemoveObject(new ValueFromProperty(new ValueConstant(CURRENT_PLAYER), new ValueConstant(PROPERTY_ROOM)),
                                new ValueConstant(CURRENT_OBJECT))
                ),
                RESPONSE_COMMAND_PICK_SUCCESFUL
        );

    }

    private void createCommandGoToGeneric(String[] fromRoomArray, String nextRoom) {
        Command command = builder.createCommandConcreteRegex(COMMAND_GOTO.concat(nextRoom));

        Condition condition = builder.createConditionAlwaysTrue().not();

        for (String fromRoom: fromRoomArray) {
            condition = condition.or(
                    builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, fromRoom)
            );
        }

        command.setCondition(
                condition.not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_GOTO_NOT_IN_NEIGHBOR_ROOM
        );

        command.setCondition(
                condition,
                builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, nextRoom),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(nextRoom)
        );
    }

    private void createCommandGoToBiblioteca() {
        Command command = builder.createCommandConcreteRegex(COMMAND_GOTO.concat(ROOM_BIBLIOTECA));

        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SOTANO).or(
                        builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA_ACCESO)
                ).not() ,
                builder.createActionNull(),
                RESPONSE_COMMAND_GOTO_NOT_IN_NEIGHBOR_ROOM
        );

        // De Sotano a biblioteca, se puede.
        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SOTANO),
                builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_BIBLIOTECA)
        );


        // De acceso a biblioteca se puede si no está el bibliotecario.
        command.setCondition(
                builder.createConditionPropertyEquals(NPC_BIBLIOTECARIO, PROPERTY_ROOM, ROOM_BIBLIOTECA_ACCESO).not(),
                builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_BIBLIOTECA)
        );

        // El player está autenticado pasa.
        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_AUTENTICADO, VALUE_AUTENTICADO_SI),
                builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_BIBLIOTECA)
        );

        // El bibliotecario dormido.
        command.setCondition(
                builder.createConditionPropertyEquals(NPC_BIBLIOTECARIO, PROPERTY_DORMIDO, VALUE_DORMIDO_SI),
                builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_BIBLIOTECA)
        );

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionNull(),
                RESPONSE_COMMAND_GOTO_CANT_GO_BIBLIOTECA
        );

    }

    private void createCommandGoTo() {
        createCommandGoToGeneric(new String[]{ROOM_PASILLO}, ROOM_SALON1);
        createCommandGoToGeneric(new String[]{ROOM_PASILLO}, ROOM_SALON2);
        createCommandGoToGeneric(new String[]{ROOM_PASILLO}, ROOM_SALON3);
        createCommandGoToGeneric(new String[]{ROOM_PASILLO, ROOM_BIBLIOTECA}, ROOM_BIBLIOTECA_ACCESO);
        createCommandGoToGeneric(new String[]{ROOM_SALON1,ROOM_SALON2,ROOM_SALON3,ROOM_BIBLIOTECA_ACCESO}, ROOM_PASILLO);
        createCommandGoToGeneric(new String[]{ROOM_BIBLIOTECA}, ROOM_SOTANO);
        createCommandGoToGeneric(new String[]{ROOM_SOTANO_ABAJO}, ROOM_AFUERA);

        createCommandGoToBiblioteca();
    }

    private void createCommandOpen() {
        Command command = builder.createCommandConcreteRegex(COMMAND_OPEN_CAJA_FUERTE);
        
        command.setCondition(
                builder.createConditionHasItem(CURRENT_PLAYER, OBJECT_LLAVE).not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_OPEN_NOT_KEY
        );
                
        command.setCondition(
                builder.createConditionPropertyEquals(OBJECT_CAJA_FUERTE, PROPERTY_FUE_ABIERTA, VALUE_FUE_ABIERTA_SI),
                builder.createActionNull(),
                RESPONSE_COMMAND_OPEN_WAS_OPEN
        );

        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SALON1).not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_OPEN_NO_BOX
        );

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionContainer(
                        builder.createActionSetProperty(OBJECT_CAJA_FUERTE, PROPERTY_FUE_ABIERTA, VALUE_FUE_ABIERTA_SI),
                        builder.createActionSetProperty(OBJECT_CREDENCIAL, PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI),
                        builder.createActionSetProperty(OBJECT_CREDENCIAL, PROPERTY_ROOM, ROOM_SALON1),
                        builder.createActionAddObject(ROOM_SALON1, OBJECT_CREDENCIAL)
                ),
                RESPONSE_COMMAND_OPEN_OK
        );
        
    }

    private void createCommands() {
        createCommandPick();
        createCommandGoTo();
        createCommandOpen();
        createCommandPutIn();
        createCommandShow();
        createCommandMove();
        createCommandUse();
    }

    private void initializeRooms() {

    }

    public Game build() {
        createPlayers();
        initializeRooms();
        createCommands();

        return gameConcrete;
    }

}
*/