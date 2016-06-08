/*
import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.actions.ActionRemoveObject;
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

    //rooms
    private static final String ROOM_PASILLO = "pasillo";
    private static final String ROOM_SALON1 = "salon1";
    private static final String ROOM_SALON2 = "salon2";
    private static final String ROOM_SALON3 = "salon3";
    private static final String ROOM_BIBLIOTECA_ACCESO = "biblioteca-acceso";
    private static final String ROOM_BIBLIOTECA = "biblioteca";
    private static final String ROOM_SOTANO = "sotano";
    private static final String ROOM_SOTANO_ABAJO = "sotano-abajo";
    private static final String ROOM_AFUERA = "afuera";

    //objects
    private static final String MESA = "mesa";
    private static final String BOTELLA = "botella-licor";
    private static final String VASO1 = "vaso1";
    private static final String VASO2 = "vaso2";
    private static final String SILLA1 = "silla1";
    private static final String SILLA2 = "silla2";
    private static final String CUADRO_TREN = "cuadro-tren";
    private static final String CUADRO_BARCO = "cuadro-barco";
    private static final String CAJA_FUERTE = "caja-fuerte";
    private static final String FOTO = "foto";
    private static final String LAPICERA = "lapicera";
    private static final String MARTILLO = "martillo";
    private static final String DESTORNILLADOR1 = "destornillador1";
    private static final String DESTORNILLADOR2 = "destornillador2";
    private static final String LLAVE = "llave";
    private static final String ESTANTE = "estante";
    private static final String LIBRO1 = "libro1";
    private static final String LIBRO2 = "libro2";
    private static final String LIBRO3 = "libro3";
    private static final String LIBRO4 = "libro4";
    private static final String LIBRO5 = "libro5";
    private static final String LIBRO6 = "libro6";
    private static final String LIBRO7 = "libro7";
    private static final String LIBRO8 = "libro8";
    private static final String LIBRO9 = "libro9";
    private static final String LIBRO_VIEJO = "libro-viejo";
    private static final String ESCALERA = "escalera";
    private static final String BARANDA = "baranda";
    private static final String VENTANA = "ventana";

    //command pick
    private static final String COMMAND_PICK_OBJECT = "pick (object)";
    private static final String CURRENT_OBJECT = "(object)";
    private static final String RESPONSE_COMMAND_PICK_NOT_PICKEABLE = "Not pickeable.";
    private static final String RESPONSE_COMMAND_PICK_NO_OBJECT_IN_CURRENT_ROOM = "No such object in the room";
    private static final String RESPONSE_COMMAND_PICK_SUCCESFUL = "Picked!";
    private static final String RESPONSE_COMMAND_PICK_FULL_ITEMS = "Your invetory is full";

    //command goto
    private static final String COMMAND_GOTO = "goto ";
    private static final String RESPONSE_COMMAND_GOTO_NOT_IN_NEIGHBOR_ROOM = "You can't do it";
    private static final String RESPONSE_COMMAND_GOTO_SUCCESSFUL = "You are now in ";
    private static final String RESPONSE_COMMAND_GOTO_CANT_GO_BIBLIOTECA = "You can't go to the library.";
    private static final String COMMAND_OPEN_CAJA_FUERTE = "open CajaFuerte using Llave";
    private static final String OBJECT_LLAVE = "llave";
    private static final String RESPONSE_COMMAND_OPEN_NOT_KEY = "You don't have the key.";
    private static final String OBJECT_CAJA_FUERTE = "CajaFuerte";
    private static final String PROPERTY_FUE_ABIERTA = "";
    private static final String VALUE_FUE_ABIERTA_SI = "";
    private static final String RESPONSE_COMMAND_OPEN_WAS_OPEN = "It was opened.";
    private static final String RESPONSE_COMMAND_OPEN_NO_BOX = "There isnt box in the room";
    private static final String RESPONSE_NO_SAFEBOX = "There isnt a safebox here.";
    private static final String OBJECT_CREDENCIAL = "credencial";
    private static final String RESPONSE_COMMAND_OPEN_OK = "A credencial was dropped.";

    //command use
    private static final String COMMAND_USE = "use (objeto)";
    private static final String OBJECT_USED = "(objeto)";
    private static final String RESPONSE_CANT_USE_THAT = "You can't use that.";
    private static final String RESPONSE_CANT_USE_THAT_IN_THERE = "You can't use that in there.";
    private static final String RESPONSE_DOWN_BASEMENT = "You are in the down basement";

    //command move
    private static final String COMMAND_MOVE = "move (objeto)";
    private static final String OBJECT_MOVED = "(objeto)";
    private static final String RESPONSE_NOTHING_HAPPENED = "Nothing happened.";

    // move cuadro barco respuestas, property y values
    private static final String RESPONSE_CAN_SEE_SAFEBOX = "You can see a safebox behind.";
    private static final String PROPERTY_SAFEBOX_CAN_BE_SEEN = "caja fuerte visible";
    private static final String VALUE_SAFEBOX_CAN_BE_SEEN = "si";
    private static final String VALUE_PROPERTY_CAN_NOT_BE_SEEN = "no";

    //move libro viejo respuestas, property y values
    private static final String RESPONSE_CAN_SEE_LADDER = "A passage opens where there is a very old and broken ladder with a railing";
    private static final String PROPERTY_LADDER_CAN_BE_SEEN = "escalera visible";
    private static final String VALUE_LADDER_CAN_BE_SEEN = "si";
    private static final String VALUE_LADDER_CAN_NOT_BE_SEEN = "no";



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
        player.setProperty(PROPERTY_SAFEBOX_CAN_BE_SEEN, VALUE_PROPERTY_CAN_NOT_BE_SEEN);
        player.setProperty(PROPERTY_LADDER_CAN_BE_SEEN, VALUE_LADDER_CAN_NOT_BE_SEEN);
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
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_SAFEBOX_CAN_BE_SEEN, VALUE_PROPERTY_CAN_NOT_BE_SEEN),
                builder.createActionNull(),
                RESPONSE_NO_SAFEBOX
        );
        
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

    private void createCommandUse() {
        Command commandUse = builder.createCommandConcrete(COMMAND_USE);

        Condition conditionPlayerInBasement = builder.createConditionPropertyEquals(CURRENT_PLAYER,PROPERTY_ROOM,ROOM_SOTANO);

        Condition conditionUseStairs = builder.createConditionSameObject(OBJECT_USED, ESCALERA);
        Condition conditionUseRailing = builder.createConditionSameObject(OBJECT_USED, BARANDA);
        Condition conditionNotStairsAndNotRailing = conditionUseRailing.not().and(conditionUseStairs.not());

        commandUse.setCondition(
                conditionNotStairsAndNotRailing,
                builder.createActionNull(),
                RESPONSE_CANT_USE_THAT
        );

        commandUse.setCondition(
                conditionPlayerInBasement.not(),
                builder.createActionNull(),
                RESPONSE_CANT_USE_THAT_IN_THERE
        );

        commandUse.setCondition(
                conditionPlayerInBasement.and(conditionUseStairs.or(conditionUseRailing)),
                builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SOTANO_ABAJO),
                RESPONSE_DOWN_BASEMENT
        );
    }

    private void createCommandMove() {
        Command command = builder.createCommandConcrete(COMMAND_MOVE);

        Condition conditionInRoom1 = builder.createConditionPropertyEquals(CURRENT_PLAYER,PROPERTY_ROOM,ROOM_SALON1);
        Condition conditionLibrary = builder.createConditionPropertyEquals(CURRENT_PLAYER,PROPERTY_ROOM,ROOM_BIBLIOTECA);

        Condition conditionUseBoatPicture = builder.createConditionSameObject(OBJECT_MOVED, CUADRO_BARCO);
        Condition conditionUseOldBook = builder.createConditionSameObject(OBJECT_MOVED, LIBRO_VIEJO);

        command.setCondition(
                conditionInRoom1.not().and(conditionLibrary.not()),
                builder.createActionNull(),
                RESPONSE_NOTHING_HAPPENED
        );

        command.setCondition(
                conditionInRoom1.and(conditionUseBoatPicture.not()),
                builder.createActionNull(),
                RESPONSE_NOTHING_HAPPENED
        );

        command.setCondition(
                conditionLibrary.and(conditionUseOldBook.not()),
                builder.createActionNull(),
                RESPONSE_NOTHING_HAPPENED
        );

        command.setCondition(
                conditionInRoom1.and(conditionUseBoatPicture),
                builder.createActionSetProperty(CURRENT_PLAYER,PROPERTY_SAFEBOX_CAN_BE_SEEN, VALUE_SAFEBOX_CAN_BE_SEEN),
                RESPONSE_CAN_SEE_SAFEBOX
        );

        command.setCondition(
                conditionLibrary.and(conditionUseOldBook),
                builder.createActionSetProperty(CURRENT_PLAYER,PROPERTY_LADDER_CAN_BE_SEEN, VALUE_LADDER_CAN_BE_SEEN),
                RESPONSE_CAN_SEE_LADDER
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

        //salon 1
        ObjectInterface table = builder.createObject(MESA);
        ObjectInterface bottle = builder.createObject(BOTELLA);
        ObjectInterface glass1 = builder.createObject(VASO1);
        ObjectInterface glass2 = builder.createObject(VASO2);
        ObjectInterface chair1 = builder.createObject(SILLA1);
        ObjectInterface chair2 = builder.createObject(SILLA2);
        ObjectInterface trainPicture = builder.createObject(CUADRO_TREN);
        ObjectInterface boatPicture = builder.createObject(CUADRO_BARCO);
        ObjectInterface safeBox = builder.createObject(CAJA_FUERTE);
        ObjectInterface room1 = builder.createObject(ROOM_SALON1);
        room1.add(table); room1.add(bottle); room1.add(glass1);
        room1.add(glass2); room1.add(chair1); room1.add(chair2);
        room1.add(trainPicture); room1.add(boatPicture); room1.add(safeBox);

        //salon 2
        ObjectInterface hammer = builder.createObject(MARTILLO);
        ObjectInterface screwdriver1 = builder.createObject(DESTORNILLADOR1);
        ObjectInterface screwdriver2 = builder.createObject(DESTORNILLADOR2);
        ObjectInterface room2 = builder.createObject(ROOM_SALON2);
        room2.add(hammer); room2.add(screwdriver1); room2.add(screwdriver2);

        //salon 3
        ObjectInterface room3 = builder.createObject(ROOM_SALON3);
        ObjectInterface key = builder.createObject(LLAVE);
        room3.add(key);

        //pasillo
        ObjectInterface hall = builder.createObject(ROOM_PASILLO);
        ObjectInterface pen = builder.createObject(LAPICERA);
        ObjectInterface picture = builder.createObject(FOTO);
        hall.add(pen); hall.add(picture);

        //acceso biblioteca
        ObjectInterface libraryAccess = builder.createObject(ROOM_BIBLIOTECA_ACCESO);

        //biblioteca
        ObjectInterface library = builder.createObject(ROOM_BIBLIOTECA);
        ObjectInterface oldBook = builder.createObject(LIBRO_VIEJO);
        ObjectInterface book1 = builder.createObject(LIBRO1);
        ObjectInterface book2 = builder.createObject(LIBRO2);
        ObjectInterface book3 = builder.createObject(LIBRO3);
        ObjectInterface book4 = builder.createObject(LIBRO4);
        ObjectInterface book5 = builder.createObject(LIBRO5);
        ObjectInterface book6 = builder.createObject(LIBRO6);
        ObjectInterface book7 = builder.createObject(LIBRO7);
        ObjectInterface book8 = builder.createObject(LIBRO8);
        ObjectInterface book9 = builder.createObject(LIBRO9);
        ObjectInterface bookCase = builder.createObject(ESTANTE);
        library.add(oldBook);library.add(book1);library.add(book2);library.add(book3);library.add(book4);
        library.add(book5);library.add(book6);library.add(book7);library.add(book8);library.add(book9);
        library.add(bookCase);

        //sotano
        ObjectInterface basement = builder.createObject(ROOM_SOTANO);

        //bajo sotano
        ObjectInterface basementDown = builder.createObject(ROOM_SOTANO_ABAJO);

        //afuera
        ObjectInterface outRoom =builder.createObject(ROOM_AFUERA);
    }

    public Game build() {
        createPlayers();
        initializeRooms();
        createCommands();

        return gameConcrete;
    }

}
*/