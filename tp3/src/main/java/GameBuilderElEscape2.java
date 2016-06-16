
import ar.fiuba.tdd.tp.*;
import ar.fiuba.tdd.tp.actions.*;
import ar.fiuba.tdd.tp.commands.Command;
import ar.fiuba.tdd.tp.conditions.Condition;
import ar.fiuba.tdd.tp.conditions.ConditionHasItem;
import ar.fiuba.tdd.tp.conditions.ConditionPropertyEquals;
import ar.fiuba.tdd.tp.timer.Timer;
import ar.fiuba.tdd.tp.values.*;


@SuppressWarnings("CPD-START")
public class GameBuilderElEscape2 implements GameBuilder {

    static final int MAX_ITEMS = 4;

    static final String CURRENT_PLAYER = "(player)";
    static final String PLAYER1 = "player1";
    static final String PLAYER2 = "player2";
    static final String PLAYER3 = "player3";
    static final String PLAYER4 = "player4";
    static final String NPC_BIBLIOTECARIO = "bibliotecario";

    static final String PROPERTY_DORMIDO = "dormido";
    static final String VALUE_DORMIDO_SI = "si-dormido";
    static final String VALUE_DORMIDO_NO = "no-dormido";

    static final String PROPERTY_ROOM = "room";
    static final String PROPERTY_INGRESO_A_LA_BIBLIOTECA = "ingreso-a-la-biblioteca";
    static final String VALUE_INGRESO_A_LA_BIBLIOTECA_SI = "si-ingreso";
    static final String VALUE_INGRESO_A_LA_BIBLIOTECA_NO = "no-ingreso";

    static final String PROPERTY_PICKEABLE = "pickeable";
    static final String VALUE_PICKEABLE_SI = "si-pickeable";

    static final String PROPERTY_AUTENTICADO = "autenticado";
    static final String VALUE_AUTENTICADO_SI = "ok";
    static final String VALUE_AUTENTICADO_NO = "no";

    static final String PROPERTY_USO_ESCALERA = "uso-escalera";
    static final String VALUE_USO_ESCALERA_SI = "si-uso";
    static final String VALUE_USO_ESCALERA_NO = "no-uso";

    static final String PROPERTY_MOSTRO_CREDENCIAL_INVALIDA = "credencial-invalida";
    static final String VALUE_MOSTRO_CREDENCIAL_INVALIDA_SI = "si-credencial-invalida";
    static final String VALUE_MOSTRO_CREDENCIAL_INVALIDA_NO = "no-credencial-invalida";

    //rooms
    static final String ROOM_PASILLO = "pasillo";
    static final String ROOM_SALON1 = "salon1";
    static final String ROOM_SALON2 = "salon2";
    static final String ROOM_SALON3 = "salon3";
    static final String ROOM_BIBLIOTECA_ACCESO = "biblioteca-acceso";
    static final String ROOM_BIBLIOTECA = "biblioteca";
    static final String ROOM_SOTANO = "sotano";
    static final String ROOM_SOTANO_ABAJO = "sotano-abajo";
    static final String ROOM_AFUERA = "afuera";

    //objects
    static final String MESA = "mesa";
    static final String CREDENCIAL = "credencial";
    static final String BOTELLA = "botella-licor";
    static final String VASO1 = "vaso1";
    static final String VASO2 = "vaso2";
    static final String SILLA1 = "silla1";
    static final String SILLA2 = "silla2";
    static final String CUADRO_TREN = "cuadro-tren";
    static final String CUADRO_BARCO = "cuadro-barco";
    static final String CAJA_FUERTE = "caja-fuerte";
    static final String FOTO1 = "foto1";
    static final String FOTO2 = "foto2";
    static final String FOTO3 = "foto3";
    static final String FOTO4 = "foto4";
    static final String LAPICERA1 = "lapicera1";
    static final String LAPICERA2 = "lapicera2";
    static final String LAPICERA3 = "lapicera3";
    static final String LAPICERA4 = "lapicera4";
    static final String MARTILLO = "martillo";
    static final String DESTORNILLADOR1 = "destornillador1";
    static final String DESTORNILLADOR2 = "destornillador2";
    static final String LLAVE = "llave";
    static final String ESTANTE = "estante";
    static final String LIBRO1 = "libro1";
    static final String LIBRO2 = "libro2";
    static final String LIBRO3 = "libro3";
    static final String LIBRO4 = "libro4";
    static final String LIBRO5 = "libro5";
    static final String LIBRO6 = "libro6";
    static final String LIBRO7 = "libro7";
    static final String LIBRO8 = "libro8";
    static final String LIBRO9 = "libro9";
    static final String LIBRO_VIEJO = "libro-viejo";
    static final String ESCALERA = "escalera";
    static final String BARANDA = "baranda";
    static final String VENTANA = "ventana";

    //command pick
    static final String COMMAND_PICK_OBJECT = "pick (object)";
    static final String CURRENT_OBJECT = "(object)";
    static final String RESPONSE_COMMAND_PICK_NOT_PICKEABLE = "Not pickeable.";
    static final String RESPONSE_COMMAND_PICK_NO_OBJECT_IN_CURRENT_ROOM = "No such object in the room";
    static final String RESPONSE_COMMAND_PICK_SUCCESFUL = "Picked!";
    static final String RESPONSE_COMMAND_PICK_FULL_ITEMS = "Your invetory is full";

    //command goto
    static final String COMMAND_GOTO = "goto ";
    static final String RESPONSE_COMMAND_GOTO_NOT_IN_NEIGHBOR_ROOM = "You can't do it";
    static final String RESPONSE_COMMAND_GOTO_SUCCESSFUL = "You are now in ";
    static final String RESPONSE_COMMAND_GOTO_CANT_GO_BIBLIOTECA = "You can't go to the library.";

    static final String COMMAND_OPEN_CAJA_FUERTE = "open (object) using (with)";
    static final String OBJECT_OPEN = "(object)";
    static final String OBJECT_OPEN_WITH = "(with)";
    static final String RESPONSE_COMMAND_OPEN_INVALIDE_USE = "I dont understand!!!";

    static final String RESPONSE_COMMAND_OPEN_NOT_KEY = "You don't have the key.";
    static final String PROPERTY_FUE_ABIERTA = "fue-abierta";
    static final String VALUE_FUE_ABIERTA_SI = "si-abierta";
    static final String RESPONSE_COMMAND_OPEN_WAS_OPEN = "It was opened.";
    static final String RESPONSE_COMMAND_OPEN_NO_BOX = "There isnt box in the room";
    static final String RESPONSE_NO_SAFEBOX = "There isnt a safebox here.";
    static final String RESPONSE_COMMAND_OPEN_OK = "A credencial was dropped.";

    //command use
    static final String COMMAND_USE = "use (objeto)";
    static final String OBJECT_USED = "(objeto)";
    static final String RESPONSE_CANT_USE_THAT = "You can't use that.";
    static final String RESPONSE_CANT_USE_THAT_IN_THERE = "You can't use that in there.";
    static final String RESPONSE_DOWN_BASEMENT = "You are in the down basement";

    //command move
    static final String COMMAND_MOVE = "move (objeto)";
    static final String OBJECT_MOVED = "(objeto)";
    static final String RESPONSE_NOTHING_HAPPENED = "Nothing happened.";
    static final String RESPONSE_NOT_EXIST = "That object not exist.";

    // move cuadro barco respuestas, property y values
    static final String RESPONSE_CAN_SEE_SAFEBOX = "You can see a safebox behind.";
    static final String PROPERTY_SAFEBOX_CAN_BE_SEEN = "caja fuerte visible";
    static final String VALUE_SAFEBOX_CAN_BE_SEEN = "si";
    static final String VALUE_PROPERTY_CAN_NOT_BE_SEEN = "no";

    //move libro viejo respuestas, property y values
    static final String RESPONSE_CAN_SEE_LADDER = "A passage opens where there is a very old and broken ladder with a railing";
    static final String PROPERTY_LADDER_CAN_BE_SEEN = "escalera visible";
    static final String VALUE_LADDER_CAN_BE_SEEN = "si";
    static final String VALUE_LADDER_CAN_NOT_BE_SEEN = "no";
    static final String RESPONSE_COMMAND_GOTO_CANT_SEE_THE_LADDER = "You can't see the ladder";

    //put foto in credencial
    static final String COMMAND_PUT = "put (object1) in (object2)";
    static final String OBJECT1_PUT = "(object1)";
    static final String OBJECT2_PUT = "(object2)";
    static final String PROPERTY_CRED_FOTO = "credencial foto";
    static final String VALUE_CRED_FOTO_SI = "si";
    static final String VALUE_CRED_FOTO_NO = "no";
    static final String PROPERTY_ES_FOTO = "es foto";
    static final String VALUE_ES_FOTO_SI = "si";
    static final String RESPONSE_CANT_DO_THAT = "You cant do that.";
    static final String RESPONSE_YOU_PUT_PHOTO = "You put photo in credential.";

    //show
    static final String COMMAND_SHOW = "show (objeto) in (personaje)";
    static final String SHOW_OBJECT = "(objeto)";
    static final String SHOW_PERSONAJE = "(personaje)";
    static final String RESPONSE_CANT_SHOW = "There's no-one to display it to";
    static final String RESPONSE_YOU_DONT_HAVE_IT = "You dont have it.";
    static final String RESPONSE_YOU_CAN_GO = "You can go to the library, there is no-one here.";
    static final String RESPONSE_AUTENTICATION_FAIL = "Fail the autentication.";
    static final String RESPONSE_THERE_IS_NO_ONE = "You cant show that to him.";
    static final String RESPONSE_IS_SAFE_TO_GO = "authenticated";

    // Break
    static final String COMMAND_BREAK = "break (object) using (with)";
    static final String BREAK_OBJECT = "(object)";
    static final String BREAK_OBJECT_WITH = "(with)";
    static final String RESPONSE_BREAK_INCORRECT_USE = "You cant do that";

    static final String RESPONSE_BREAK_YOU_MUST_HAVE_MARTILLO = "You must have the Martillo";
    static final String RESPONSE_BREAK_NO_VENTANA = "There's not a Ventana";
    static final String RESPONSE_BREAK_SUCCESSFUL = "You break the ventana!!!";
    static final String PROPERTY_ESTADO_VENTANA = "estado-ventana";
    static final String VALUE_ESTADO_VENTANA_SANA = "ventana-sana";
    static final String VALUE_ESTADO_VENTANA_ROTA = "ventana-rota";
    static final String RESPONSE_COMMAND_GOTO_CANT_GO_TO_AFUERA = "You cannot. Break the window first.";

    static final String COMMAND_WHERE = "where";
    static final String COMMAND_DRUG = "drug Bibliotecario";
    static final int TICKS_DORMIDO = 120;
    static final int TICKS_CAMBIO_POSICION = 60 * 4;
    static final String RESPONSE_NOTIFICATION_BIBLIOTECARIO_DESPIERTO = "Se desperto el bibliotecario!!!";
    static final String RESPONSE_DRUG_OK = "El bibliotecario está dormido.";
    static final String RESPONSE_DRUG_NO_BIBLIOTECARIO = "No está el bibliotecario para drogarlo.";
    static final String RESPONSE_DRUG_NO_LICOR = "No tienes la botella de vino.";
    static final String RESPONSE_CAMBIO_DE_POSICION = "El bibliotecario cambio de posición!!!!!";

    static final String COMMAND_DROP = "drop (objeto)";
    static final String OBJECT_DROP = "(objeto)";
    static final String RESPONSE_COMMAND_DROP_NOT_DROPPEABLE = "Not droppeable.";
    static final String RESPONSE_COMMAND_DROP_DONT_HAVE_ITEM = "You dont have the item";
    static final String RESPONSE_COMMAND_DROP_SUCCESFUL = "Dropped";
    static final String RESPONSE_MOSTRASTE_CREDENCIAL_INVALIDA = "You are banned!!!";

    static final String COMMAND_LOOK_AROUND = "look around";
    static final String COMMAND_WHAT_CAN_I_DO_WITH = "what can i do with (object)";
    static final String OBJECT_WHAT_CAN_I_DO = "(object)";
    static final String PROPERTY_WHAT_CAN_I_DO = "property-what-can-i-do";

    static final String CAN_PICK_DROP = "You can pick/drop it.";
    static final String CAN_USE = "You can use it";
    static final String CAN_MOVE = "You can move it";
    static final String CAN_SHOW_DRUG = "You can drug and show";
    static final String CAN_OPEN = "You can open it";
    static final String CAN_PICK_DROP_PUT = "You can put a photo in it, pick/drop.";

    GameConcrete gameConcrete = new GameConcrete();
    Builder builder = new Builder(gameConcrete);

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
                        builder.createConditionPropertyEquals(playerID, PROPERTY_AUTENTICADO, VALUE_AUTENTICADO_NO)
                );
        seEncontroConBibliotecario =
                seEncontroConBibliotecario.and(
                        builder.createConditionComparePropertyEqual(playerID, PROPERTY_ROOM, NPC_BIBLIOTECARIO, PROPERTY_ROOM)
                );
        seEncontroConBibliotecario =
                seEncontroConBibliotecario.and(
                        builder.createConditionPropertyEquals(NPC_BIBLIOTECARIO, PROPERTY_DORMIDO, VALUE_DORMIDO_SI).not()
                );

        // Otra condición: Si uso la escalera, pierde.
        Condition usoEscalera = builder.createConditionPropertyEquals(playerID, PROPERTY_USO_ESCALERA, VALUE_USO_ESCALERA_SI);

        Condition enSotanoAbajoSinMartillo =
                builder.createConditionPropertyEquals(playerID, PROPERTY_ROOM, ROOM_SOTANO_ABAJO).and(
                        builder.createConditionHasItem(playerID, MARTILLO).not()
                );

        Condition lostCondition = seEncontroConBibliotecario.or(usoEscalera).or(enSotanoAbajoSinMartillo);
        gameConcrete.setLostCondition(playerID, lostCondition);
    }

    private void createLostCondition() {
        createLostConditionForPlayer(PLAYER1);
        createLostConditionForPlayer(PLAYER2);
        createLostConditionForPlayer(PLAYER3);
        createLostConditionForPlayer(PLAYER4);
    }

    private ObjectInterface createPlayer(String playerID) {
        ObjectInterface player = builder.createPlayer(playerID);
        player.setProperty(PROPERTY_ROOM, ROOM_PASILLO);
        player.setProperty(PROPERTY_USO_ESCALERA, VALUE_USO_ESCALERA_NO);
        player.setProperty(PROPERTY_AUTENTICADO, VALUE_AUTENTICADO_NO);
        player.setProperty(PROPERTY_MOSTRO_CREDENCIAL_INVALIDA, VALUE_MOSTRO_CREDENCIAL_INVALIDA_NO);
        player.setProperty(PROPERTY_SAFEBOX_CAN_BE_SEEN, VALUE_PROPERTY_CAN_NOT_BE_SEEN);
        player.setProperty(PROPERTY_LADDER_CAN_BE_SEEN, VALUE_LADDER_CAN_NOT_BE_SEEN);
        player.setProperty(PROPERTY_INGRESO_A_LA_BIBLIOTECA, VALUE_INGRESO_A_LA_BIBLIOTECA_NO);
        return player;
    }

    private void createPlayers() {
        ObjectInterface player1 = createPlayer(PLAYER1);
        ObjectInterface foto1 = builder.createObject(FOTO1);
        foto1.setProperty(PROPERTY_ES_FOTO,VALUE_ES_FOTO_SI);
        player1.add(foto1);
        player1.add(builder.createObject(LAPICERA1));

        ObjectInterface player2 = createPlayer(PLAYER2);
        ObjectInterface foto2 = builder.createObject(FOTO2);
        foto2.setProperty(PROPERTY_ES_FOTO,VALUE_ES_FOTO_SI);
        player2.add(foto2);
        player2.add(builder.createObject(LAPICERA2));

        ObjectInterface player3 = createPlayer(PLAYER3);
        ObjectInterface foto3 = builder.createObject(FOTO3);
        foto3.setProperty(PROPERTY_ES_FOTO,VALUE_ES_FOTO_SI);
        player3.add(foto3);
        player3.add(builder.createObject(LAPICERA3));

        ObjectInterface player4 = createPlayer(PLAYER4);
        ObjectInterface foto4 = builder.createObject(FOTO4);
        foto4.setProperty(PROPERTY_ES_FOTO,VALUE_ES_FOTO_SI);
        player4.add(foto4);
        player4.add(builder.createObject(LAPICERA4));
    }

    private void createCommandLookAround() {
        Command command = builder.createCommandConcreteRegex(COMMAND_LOOK_AROUND);

        Value response = new ValuePrintItems(
                new ValueFromProperty(
                        new ValueConstant(CURRENT_PLAYER), new ValueConstant(PROPERTY_ROOM)
                )
        );

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionNull(),
                response
        );
    }

    private void createCommandWhatCanIDoWith() {
        Command command = builder.createCommandConcreteRegex(COMMAND_WHAT_CAN_I_DO_WITH);
        
        Value response = new ValueFromProperty(
                new ValueConstant(OBJECT_WHAT_CAN_I_DO),
                new ValueConstant(PROPERTY_WHAT_CAN_I_DO)
        );

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionNull(),
                response
        );
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

    private void createCommandDrop() {
        Command command = builder.createCommandConcreteRegex(COMMAND_DROP);

        Condition objectDroppeable =
                builder.createConditionPropertyEquals(OBJECT_DROP, PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);

        Condition playerHasObject =
                builder.createConditionHasItem(CURRENT_PLAYER, OBJECT_DROP);

        command.setCondition(
                objectDroppeable.not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_DROP_NOT_DROPPEABLE
        );

        command.setCondition(
                playerHasObject.not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_DROP_DONT_HAVE_ITEM
        );

        command.setCondition(
                playerHasObject.and(objectDroppeable),
                builder.createActionContainer(
                        builder.createActionRemoveObject(CURRENT_PLAYER, OBJECT_DROP),
                        new ActionAddObject(new ValueFromProperty(new ValueConstant(CURRENT_PLAYER), new ValueConstant(PROPERTY_ROOM)),
                                new ValueConstant(OBJECT_DROP))
                ),
                RESPONSE_COMMAND_DROP_SUCCESFUL
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
                builder.createActionContainer(
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA),
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_LADDER_CAN_BE_SEEN, VALUE_LADDER_CAN_NOT_BE_SEEN)
                ),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_BIBLIOTECA)
        );


        // De acceso a biblioteca se puede si no está el bibliotecario.
        command.setCondition(
                builder.createConditionPropertyEquals(NPC_BIBLIOTECARIO, PROPERTY_ROOM, ROOM_BIBLIOTECA_ACCESO).not(),
                builder.createActionContainer(
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA),
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_INGRESO_A_LA_BIBLIOTECA, VALUE_INGRESO_A_LA_BIBLIOTECA_SI)
                ),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_BIBLIOTECA)
        );

        // El bibliotecario dormido.
        command.setCondition(
                builder.createConditionPropertyEquals(NPC_BIBLIOTECARIO, PROPERTY_DORMIDO, VALUE_DORMIDO_SI),
                builder.createActionContainer(
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA),
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_INGRESO_A_LA_BIBLIOTECA, VALUE_INGRESO_A_LA_BIBLIOTECA_SI)
                ),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_BIBLIOTECA)
        );

        // Mostraste credencial invalida.
        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_MOSTRO_CREDENCIAL_INVALIDA, VALUE_MOSTRO_CREDENCIAL_INVALIDA_SI),
                builder.createActionNull(),
                RESPONSE_MOSTRASTE_CREDENCIAL_INVALIDA
        );

        // El player está autenticado pasa.
        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_AUTENTICADO, VALUE_AUTENTICADO_SI),
                builder.createActionContainer(
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA),
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_INGRESO_A_LA_BIBLIOTECA, VALUE_INGRESO_A_LA_BIBLIOTECA_SI)
                ),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_BIBLIOTECA)
        );

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionNull(),
                RESPONSE_COMMAND_GOTO_CANT_GO_BIBLIOTECA
        );

    }

    private void createCommandGoToSotano() {
        Command command = builder.createCommandConcreteRegex(COMMAND_GOTO.concat(ROOM_SOTANO));

        Condition playerInBiblioteca =
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA);

        Condition playerSeeTheSotano =
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_LADDER_CAN_BE_SEEN, VALUE_LADDER_CAN_BE_SEEN);


        command.setCondition(
                playerInBiblioteca.not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_GOTO_NOT_IN_NEIGHBOR_ROOM
        );

        command.setCondition(
                playerSeeTheSotano.not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_GOTO_CANT_SEE_THE_LADDER
        );

        command.setCondition(
                playerSeeTheSotano.and(playerInBiblioteca),
                builder.createActionContainer(
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SOTANO),
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_LADDER_CAN_BE_SEEN, VALUE_LADDER_CAN_NOT_BE_SEEN)
                ),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_SOTANO)
        );
    }

    private void createCommandGoToAfuera() {
        Command command = builder.createCommandConcreteRegex(COMMAND_GOTO.concat(ROOM_AFUERA));

        Condition playerInSotanoAbajo =
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SOTANO_ABAJO);
        Condition ventanaRota =
                builder.createConditionPropertyEquals(VENTANA, PROPERTY_ESTADO_VENTANA, VALUE_ESTADO_VENTANA_ROTA);

        command.setCondition(
                playerInSotanoAbajo.not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_GOTO_NOT_IN_NEIGHBOR_ROOM
        );

        command.setCondition(
                ventanaRota.not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_GOTO_CANT_GO_TO_AFUERA
        );

        command.setCondition(
                ventanaRota.and(playerInSotanoAbajo),
                builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_AFUERA),
                RESPONSE_COMMAND_GOTO_SUCCESSFUL.concat(ROOM_AFUERA)
        );
    }

    private void createCommandGoTo() {
        createCommandGoToGeneric(new String[]{ROOM_PASILLO}, ROOM_SALON1);
        createCommandGoToGeneric(new String[]{ROOM_PASILLO}, ROOM_SALON2);
        createCommandGoToGeneric(new String[]{ROOM_PASILLO}, ROOM_SALON3);
        createCommandGoToGeneric(new String[]{ROOM_PASILLO, ROOM_BIBLIOTECA}, ROOM_BIBLIOTECA_ACCESO);
        createCommandGoToGeneric(new String[]{ROOM_SALON1,ROOM_SALON2,ROOM_SALON3,ROOM_BIBLIOTECA_ACCESO}, ROOM_PASILLO);

        createCommandGoToAfuera();
        createCommandGoToSotano();
        createCommandGoToBiblioteca();
    }

    private void createCommandOpen() {
        Command command = builder.createCommandConcreteRegex(COMMAND_OPEN_CAJA_FUERTE);

        Condition isCajaFuerte =
                builder.createConditionSameObject(OBJECT_OPEN, CAJA_FUERTE);
        Condition usingLlave =
                builder.createConditionSameObject(OBJECT_OPEN_WITH, LLAVE);

        command.setCondition(
                isCajaFuerte.not().or(usingLlave.not()),
                builder.createActionNull(),
                RESPONSE_COMMAND_OPEN_INVALIDE_USE
        );

        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_SAFEBOX_CAN_BE_SEEN, VALUE_PROPERTY_CAN_NOT_BE_SEEN),
                builder.createActionNull(),
                RESPONSE_NO_SAFEBOX
        );

        command.setCondition(
                builder.createConditionHasItem(CURRENT_PLAYER, LLAVE).not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_OPEN_NOT_KEY
        );

        command.setCondition(
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SALON1).not(),
                builder.createActionNull(),
                RESPONSE_COMMAND_OPEN_NO_BOX
        );

        command.setCondition(
                builder.createConditionPropertyEquals(CAJA_FUERTE, PROPERTY_FUE_ABIERTA, VALUE_FUE_ABIERTA_SI),
                builder.createActionNull(),
                RESPONSE_COMMAND_OPEN_WAS_OPEN
        );

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionContainer(
                        builder.createActionSetProperty(CAJA_FUERTE, PROPERTY_FUE_ABIERTA, VALUE_FUE_ABIERTA_SI),
                        builder.createActionSetProperty(CREDENCIAL, PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI),
                        builder.createActionAddObject(ROOM_SALON1, CREDENCIAL),
                        builder.createActionRemoveObject(CAJA_FUERTE, CREDENCIAL)
                ),
                RESPONSE_COMMAND_OPEN_OK
        );

    }

    private void createCommandUse() {
        Command commandUse = builder.createCommandConcreteRegex(COMMAND_USE);

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
                conditionPlayerInBasement.and(conditionUseRailing),
                builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SOTANO_ABAJO),
                RESPONSE_DOWN_BASEMENT
        );

        // Uso la escalera, va a perder el juego porque se setea la property uso escalera.
        commandUse.setCondition(
                conditionPlayerInBasement.and(conditionUseStairs),
                builder.createActionContainer(
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SOTANO_ABAJO),
                        builder.createActionSetProperty(CURRENT_PLAYER, PROPERTY_USO_ESCALERA, VALUE_USO_ESCALERA_SI)
                ),
                RESPONSE_DOWN_BASEMENT
        );
    }

    private void createCommandMove() {
        Command command = builder.createCommandConcreteRegex(COMMAND_MOVE);

        Condition conditionInRoom1 = builder.createConditionPropertyEquals(CURRENT_PLAYER,PROPERTY_ROOM,ROOM_SALON1);
        Condition conditionLibrary = builder.createConditionPropertyEquals(CURRENT_PLAYER,PROPERTY_ROOM,ROOM_BIBLIOTECA);

        Condition conditionUseBoatPicture = builder.createConditionSameObject(OBJECT_MOVED, CUADRO_BARCO);
        Condition conditionUseOldBook = builder.createConditionSameObject(OBJECT_MOVED, LIBRO_VIEJO);

        Condition cond = builder.createConditionGameHasObject(OBJECT_MOVED);

        command.setCondition(
                cond.not(),
                builder.createActionNull(),
                RESPONSE_NOT_EXIST
        );

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

    private void createCommandBreak() {
        Command command = builder.createCommandConcreteRegex(COMMAND_BREAK);

        Condition isVentana =
                builder.createConditionSameObject(BREAK_OBJECT, VENTANA);
        Condition isMartillo =
                builder.createConditionSameObject(BREAK_OBJECT_WITH, MARTILLO);

        Condition conditionThereIsVentana =
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_SOTANO_ABAJO);

        Condition conditionPlayerHasMartillo =
                builder.createConditionHasItem(CURRENT_PLAYER, MARTILLO);

        command.setCondition(
                isVentana.not().or(isMartillo.not()),
                builder.createActionNull(),
                RESPONSE_BREAK_INCORRECT_USE
        );

        command.setCondition(
                conditionPlayerHasMartillo.not(),
                builder.createActionNull(),
                RESPONSE_BREAK_YOU_MUST_HAVE_MARTILLO
        );

        command.setCondition(
                conditionThereIsVentana.not(),
                builder.createActionNull(),
                RESPONSE_BREAK_NO_VENTANA
        );

        command.setCondition(
                conditionPlayerHasMartillo.and(conditionThereIsVentana),
                builder.createActionSetProperty(VENTANA, PROPERTY_ESTADO_VENTANA, VALUE_ESTADO_VENTANA_ROTA),
                RESPONSE_BREAK_SUCCESSFUL
        );

    }

    private void createCommandWhereIsBibliotecario() {
        Command command = builder.createCommandConcreteRegex(COMMAND_WHERE);

        command.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionNull(),
                new ValueFromProperty(new ValueConstant(NPC_BIBLIOTECARIO), new ValueConstant(PROPERTY_ROOM))
        );

    }

    private void createConditionCambioDePosicion(Timer timer, String roomFrom, String... posibleRoomsTo) {
        ActionRandom actionRandom = new ActionRandom(gameConcrete);
        for (String roomTo: posibleRoomsTo) {
            actionRandom.addAction(
                    builder.createActionSetProperty(NPC_BIBLIOTECARIO, PROPERTY_ROOM, roomTo)
            );
        }

        timer.setCondition(
                builder.createConditionPropertyEquals(NPC_BIBLIOTECARIO, PROPERTY_ROOM, roomFrom),
                actionRandom,
                RESPONSE_CAMBIO_DE_POSICION
        );

    }

    private Timer createTimerCambioDePosicion() {
        Timer timer = builder.createPeriodicTimer(TICKS_CAMBIO_POSICION);
        createConditionCambioDePosicion(timer, ROOM_SALON1, ROOM_PASILLO);
        createConditionCambioDePosicion(timer, ROOM_SALON2, ROOM_PASILLO);
        createConditionCambioDePosicion(timer, ROOM_SALON3, ROOM_PASILLO);
        createConditionCambioDePosicion(timer, ROOM_PASILLO, ROOM_SALON1, ROOM_SALON2, ROOM_SALON3, ROOM_BIBLIOTECA_ACCESO);
        createConditionCambioDePosicion(timer, ROOM_BIBLIOTECA_ACCESO, ROOM_PASILLO, ROOM_BIBLIOTECA);
        createConditionCambioDePosicion(timer, ROOM_BIBLIOTECA, ROOM_BIBLIOTECA_ACCESO, ROOM_SOTANO);
        createConditionCambioDePosicion(timer, ROOM_SOTANO, ROOM_BIBLIOTECA, ROOM_SOTANO_ABAJO);
        createConditionCambioDePosicion(timer, ROOM_SOTANO_ABAJO, ROOM_SOTANO, ROOM_AFUERA);
        createConditionCambioDePosicion(timer, ROOM_AFUERA, ROOM_SOTANO_ABAJO);
        return timer;
    }


    private Timer createTimerDespertarBibliotecario() {
        Timer timerCambioDePosicion = createTimerCambioDePosicion();

        Timer timer = builder.createTimerConcrete(TICKS_DORMIDO);

        timer.setCondition(
                builder.createConditionAlwaysTrue(),
                builder.createActionContainer(
                        builder.createActionSetProperty(NPC_BIBLIOTECARIO, PROPERTY_DORMIDO, VALUE_DORMIDO_NO),
                        builder.createActionAddTimer(timerCambioDePosicion)
                ),
                RESPONSE_NOTIFICATION_BIBLIOTECARIO_DESPIERTO
        );

        return timer;
    }

    private void createCommandDrug() {
        Command command = builder.createCommandConcreteRegex(COMMAND_DRUG);

        Condition playerInBibliotecaAcceso =
                builder.createConditionPropertyEquals(CURRENT_PLAYER, PROPERTY_ROOM, ROOM_BIBLIOTECA_ACCESO);

        Condition playerHasLicor = builder.createConditionHasItem(CURRENT_PLAYER, BOTELLA);

        command.setCondition(
                playerInBibliotecaAcceso.not(),
                builder.createActionNull(),
                RESPONSE_DRUG_NO_BIBLIOTECARIO
        );

        command.setCondition(
                playerHasLicor.not(),
                builder.createActionNull(),
                RESPONSE_DRUG_NO_LICOR
        );

        command.setCondition(
                playerInBibliotecaAcceso.and(playerHasLicor),
                builder.createActionContainer(
                        builder.createActionSetProperty(NPC_BIBLIOTECARIO, PROPERTY_DORMIDO, VALUE_DORMIDO_SI),
                        builder.createActionAddTimer(createTimerDespertarBibliotecario())
                ),
                RESPONSE_DRUG_OK
        );
        
    }

    private void createCommandPutIn() {
        Command command = builder.createCommandConcreteRegex(COMMAND_PUT);

        Condition conditionHasObjectPut = builder.createConditionHasItem(CURRENT_PLAYER,OBJECT1_PUT);
        Condition conditionObjectPutIsPhoto = builder.createConditionPropertyEquals(OBJECT1_PUT,PROPERTY_ES_FOTO,VALUE_ES_FOTO_SI);

        Condition conditionHasObjectToPut = builder.createConditionHasItem(CURRENT_PLAYER,OBJECT2_PUT);
        Condition conditionObjectToPutIsCredential = builder.createConditionSameObject(OBJECT2_PUT,CREDENCIAL);

        command.setCondition(
                conditionHasObjectPut.not().or(conditionHasObjectToPut.not()),
                builder.createActionNull(),
                RESPONSE_YOU_DONT_HAVE_IT
        );

        command.setCondition(
                conditionHasObjectPut.and(conditionObjectPutIsPhoto.not()),
                builder.createActionNull(),
                RESPONSE_CANT_DO_THAT
        );

        command.setCondition(
                conditionHasObjectToPut.and(conditionObjectToPutIsCredential.not()),
                builder.createActionNull(),
                RESPONSE_CANT_DO_THAT
        );


        ActionContainer action = new ActionContainer();
        action.addAction(builder.createActionRemoveObject(CURRENT_PLAYER,OBJECT1_PUT));
        action.addAction(builder.createActionAddObject(CREDENCIAL, OBJECT1_PUT));
        action.addAction(
                new ActionSetProperty(
                        new ValueConstant(CREDENCIAL), new ValueConstant(PROPERTY_CRED_FOTO),
                        new ValueGetDescription(new ValueConstant(CURRENT_PLAYER))
                )
        );


        command.setCondition(
                conditionHasObjectPut.and(conditionObjectPutIsPhoto.and(conditionHasObjectToPut.and(conditionObjectToPutIsCredential))),
                action,
                RESPONSE_YOU_PUT_PHOTO
        );

    }

    private void createCommandShow() {
        Command command = builder.createCommandConcreteRegex(COMMAND_SHOW);

        Condition conditionInAccessLibrary = builder.createConditionPropertyEquals(CURRENT_PLAYER,PROPERTY_ROOM,ROOM_BIBLIOTECA_ACCESO);

        Condition conditionShowToLibrarian = builder.createConditionSameObject(NPC_BIBLIOTECARIO,SHOW_PERSONAJE);

        Condition conditionHasShowItem = builder.createConditionHasItem(CURRENT_PLAYER, SHOW_OBJECT);

        Condition conditionShowCredential = builder.createConditionSameObject(SHOW_OBJECT,CREDENCIAL);

        command.setCondition(
                conditionShowToLibrarian.not(),
                builder.createActionNull(),
                RESPONSE_THERE_IS_NO_ONE
        );

        command.setCondition(
                conditionHasShowItem.not(),
                builder.createActionNull(),
                RESPONSE_YOU_DONT_HAVE_IT
        );

        command.setCondition(
                conditionInAccessLibrary.not(),
                builder.createActionNull(),
                RESPONSE_CANT_SHOW
        );

        Condition conditionThereIsLibrarian = builder.createConditionPropertyEquals(NPC_BIBLIOTECARIO,PROPERTY_ROOM,ROOM_BIBLIOTECA_ACCESO);

        command.setCondition(
                conditionThereIsLibrarian.not().and(conditionInAccessLibrary),//estoy en el acceso y no esta el bibliotecario
                builder.createActionNull(),
                RESPONSE_YOU_CAN_GO
        );


        command.setCondition(
                conditionThereIsLibrarian.and(conditionShowCredential.not()),//esta el bibliotecario y no muestro cred
                builder.createActionSetProperty(CURRENT_PLAYER,PROPERTY_MOSTRO_CREDENCIAL_INVALIDA,VALUE_MOSTRO_CREDENCIAL_INVALIDA_SI),                                 //distinto que la credencial falla la autentificacion
                RESPONSE_AUTENTICATION_FAIL
        );

        Condition conditionCredentialHasPhotoOk =
                new ConditionPropertyEquals(
                        new ValueConstant(CREDENCIAL), new ValueConstant(PROPERTY_CRED_FOTO),
                        new ValueGetDescription(new ValueConstant(CURRENT_PLAYER))
                );

        command.setCondition(
                conditionThereIsLibrarian.and(conditionHasShowItem.and(conditionCredentialHasPhotoOk.not())),
                builder.createActionSetProperty(CURRENT_PLAYER,PROPERTY_MOSTRO_CREDENCIAL_INVALIDA,VALUE_MOSTRO_CREDENCIAL_INVALIDA_SI),                                                          //tengo el item que muerto y
                RESPONSE_AUTENTICATION_FAIL
        );

        command.setCondition(
                conditionThereIsLibrarian.and(conditionHasShowItem.and(conditionCredentialHasPhotoOk)),
                builder.createActionSetProperty(CURRENT_PLAYER,PROPERTY_AUTENTICADO,VALUE_AUTENTICADO_SI),///
                RESPONSE_IS_SAFE_TO_GO
        );
    }
    
    private void createCommands() {
        createCommandLookAround();
        createCommandWhatCanIDoWith();
        createCommandPick();
        createCommandGoTo();
        createCommandOpen();
        createCommandPutIn();
        createCommandShow();
        createCommandWhereIsBibliotecario();
        createCommandBreak();
        createCommandMove();
        createCommandUse();
        createCommandDrug();
        createCommandDrop();
    }

    private void initializeRooms() {

        //salon 1
        ObjectInterface table = builder.createObject(MESA);

        ObjectInterface bottle = builder.createObject(BOTELLA);
        bottle.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        bottle.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);

        ObjectInterface glass1 = builder.createObject(VASO1);
        glass1.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        glass1.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);

        ObjectInterface glass2 = builder.createObject(VASO2);
        glass2.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        glass2.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);

        ObjectInterface chair1 = builder.createObject(SILLA1);
        ObjectInterface chair2 = builder.createObject(SILLA2);
        ObjectInterface trainPicture = builder.createObject(CUADRO_TREN);

        ObjectInterface boatPicture = builder.createObject(CUADRO_BARCO);
        boatPicture.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_MOVE);

        ObjectInterface safeBox = builder.createObject(CAJA_FUERTE);
        safeBox.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_OPEN);
        ObjectInterface credential = builder.createObject(CREDENCIAL);
        credential.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        credential.setProperty(PROPERTY_CRED_FOTO,VALUE_CRED_FOTO_NO);
        credential.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP_PUT);
        safeBox.add(credential);
        ObjectInterface room1 = builder.createObject(ROOM_SALON1);
        room1.add(table);
        room1.add(bottle);
        room1.add(glass1);
        room1.add(glass2);
        room1.add(chair1);
        room1.add(chair2);
        room1.add(trainPicture);
        room1.add(boatPicture);
        room1.add(safeBox);

        //salon 2
        ObjectInterface hammer = builder.createObject(MARTILLO);
        hammer.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        hammer.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);

        ObjectInterface screwdriver1 = builder.createObject(DESTORNILLADOR1);
        screwdriver1.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        screwdriver1.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface screwdriver2 = builder.createObject(DESTORNILLADOR2);
        screwdriver2.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        screwdriver2.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface room2 = builder.createObject(ROOM_SALON2);
        room2.add(hammer);
        room2.add(screwdriver1);
        room2.add(screwdriver2);

        //salon 3
        ObjectInterface room3 = builder.createObject(ROOM_SALON3);
        ObjectInterface key = builder.createObject(LLAVE);
        key.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        key.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        room3.add(key);

        //pasillo
        //ObjectInterface hall = builder.createObject(ROOM_PASILLO);
        builder.createObject(ROOM_PASILLO);

        //acceso biblioteca
        //ObjectInterface libraryAccess = builder.createObject(ROOM_BIBLIOTECA_ACCESO);
        builder.createObject(ROOM_BIBLIOTECA_ACCESO);

        //biblioteca
        ObjectInterface library = builder.createObject(ROOM_BIBLIOTECA);
        ObjectInterface oldBook = builder.createObject(LIBRO_VIEJO);
        oldBook.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_MOVE);
        ObjectInterface book1 = builder.createObject(LIBRO1);
        book1.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        book1.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface book2 = builder.createObject(LIBRO2);
        book2.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        book2.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface book3 = builder.createObject(LIBRO3);
        book3.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        book3.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface book4 = builder.createObject(LIBRO4);
        book4.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        book4.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface book5 = builder.createObject(LIBRO5);
        book5.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        book5.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        ObjectInterface book6 = builder.createObject(LIBRO6);
        book6.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        book6.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface book7 = builder.createObject(LIBRO7);
        book7.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        book7.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface book8 = builder.createObject(LIBRO8);
        book8.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        book8.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface book9 = builder.createObject(LIBRO9);
        book9.setProperty(PROPERTY_PICKEABLE, VALUE_PICKEABLE_SI);
        book9.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_PICK_DROP);
        ObjectInterface bookCase = builder.createObject(ESTANTE);
        library.add(oldBook);library.add(book1);library.add(book2);library.add(book3);library.add(book4);
        library.add(book5);library.add(book6);library.add(book7);library.add(book8);library.add(book9);
        library.add(bookCase);

        //sotano
        //ObjectInterface basement = builder.createObject(ROOM_SOTANO);
        builder.createObject(ROOM_SOTANO);

        //bajo sotano
        //ObjectInterface basementDown = builder.createObject(ROOM_SOTANO_ABAJO);
        builder.createObject(ROOM_SOTANO_ABAJO);

        //afuera
        //ObjectInterface outRoom = builder.createObject(ROOM_AFUERA);
        builder.createObject(ROOM_AFUERA);

        ObjectInterface bibliotecario = builder.createObject(NPC_BIBLIOTECARIO);
        bibliotecario.setProperty(PROPERTY_ROOM, ROOM_BIBLIOTECA_ACCESO);
        bibliotecario.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_SHOW_DRUG);

        ObjectInterface ventana = builder.createObject(VENTANA);
        ventana.setProperty(PROPERTY_ESTADO_VENTANA, VALUE_ESTADO_VENTANA_SANA);

        ObjectInterface baranda = builder.createObject(BARANDA);
        baranda.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_USE);

        ObjectInterface escalera = builder.createObject(ESCALERA);
        escalera.setProperty(PROPERTY_WHAT_CAN_I_DO, CAN_USE);
    }

    public Game build() {
        createPlayers();
        initializeRooms();
        createCommands();
        createWinConditions();
        createLostCondition();

        return gameConcrete;
    }

}
