import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.behavior.*;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;
import ar.fiuba.tdd.tp.engine.rules.PlayerHasRule;
import ar.fiuba.tdd.tp.engine.rules.Rule;

@SuppressWarnings("CPD-START")
public class BuildScenario {

    private static final String GOTO = "goto";
    private static final String MOVE = "move";
    private static final String PICK = "pick";
    private static final String USE = "use";
    private static final String OPEN = "open";
    private static final String PUT = "put";
    private static final String SHOW = "show";
    private static final String FOTO = "foto";
    private static final String CREDENCIAL = "credencial";
    private static final String SALON_UNO = "salon 1";
    private static final String SALON_DOS = "salon 2";
    private static final String SALON_TRES = "salon 3";
    private static final String PASILLO = "pasillo";
    private static final String ACCESO_BIBLIOTECA = "acceso biblioteca";

    public static ComponentContainer build(Game game) {

        ComponentContainer pasillo = new ComponentContainer(PASILLO);
        pasillo.addItem(createDoor(SALON_UNO, game, pasillo, createSalon1(game, pasillo)));
        pasillo.addItem(createDoor(SALON_DOS, game, pasillo, createSalon2(game, pasillo)));
        pasillo.addItem(createDoor(SALON_TRES, game, pasillo, createSalon3(game, pasillo)));
        pasillo.addItem(createDoor(ACCESO_BIBLIOTECA, game, pasillo, createAccesoBiblioteca(game, pasillo)));

        return pasillo;
    }

    private static ComponentContainer createSalon1(Game game, ComponentContainer pasillo) {
        ComponentContainer salon1 = new ComponentContainer(SALON_UNO);

        salon1.addItem(new ComponentSimple("mesa"));

        salon1.addItem(new ComponentSimple("botella licor"));
        salon1.addItem(new ComponentSimple("vaso 1"));
        salon1.addItem(new ComponentSimple("vaso 2"));

        salon1.addItem(new ComponentSimple("silla 1"));
        salon1.addItem(new ComponentSimple("silla 2"));
        salon1.addItem(new ComponentSimple("cuadro de tren"));
        salon1.addItem(crateCuadroBarco(game));
        salon1.addItem(createDoor(PASILLO, game, salon1, pasillo));

        return salon1;
    }

    private static ComponentInterface crateCuadroBarco(Game game) {

        //TODO pensar credencial
        ComponentContainer credencial = new ComponentContainer(CREDENCIAL);
        credencial.addBehavior(PICK, new Pick(game, credencial));
        credencial.addBehavior(PUT, new Store(game, credencial));

        ComponentContainer cajaFuerte = new ComponentContainer("caja fuerte");
        cajaFuerte.addItem(credencial);
        cajaFuerte.addBehavior(OPEN, new CajaFuerteDrop(game, cajaFuerte, "llave"));

        ComponentContainer cuadroBarco = new ComponentContainer("cuadro de barco");
        cuadroBarco.addBehavior(MOVE, new ContainerDrop(game, cuadroBarco));
        cuadroBarco.addItem(cajaFuerte);

        return cuadroBarco;
    }

    private static ComponentContainer createSalon2(Game game, ComponentContainer pasillo) {
        ComponentContainer salon2 = new ComponentContainer(SALON_DOS);

        ComponentInterface martillo = new ComponentSimple("martillo");
        martillo.addBehavior(PICK, new Pick(game, martillo));

        salon2.addItem(martillo);
        salon2.addItem(new ComponentSimple("destornillador 1"));
        salon2.addItem(new ComponentSimple("destornillador 2"));
        salon2.addItem(createDoor("pasillo", game, salon2, pasillo));

        return salon2;
    }

    private static ComponentContainer createSalon3(Game game, ComponentContainer pasillo) {
        ComponentContainer salon3 = new ComponentContainer(SALON_TRES);

        ComponentInterface llave = new ComponentSimple("llave");
        llave.addBehavior(PICK, new Pick(game, llave));

        salon3.addItem(llave);
        salon3.addItem(createDoor("pasillo", game, salon3, pasillo));

        return salon3;
    }

    private static ComponentContainer createAccesoBiblioteca(Game game, ComponentContainer pasillo) {
        ComponentContainer accessoBiblioteca = new ComponentContainer(ACCESO_BIBLIOTECA);
        ComponentInterface bibliotecaDoor = createLockedDoor("biblioteca", game, null);

        ComponentInterface bibliotecario = new ComponentSimple("bibliotecario");
        bibliotecario.addBehavior(SHOW, new ShowCredencial(game, bibliotecaDoor, accessoBiblioteca));

        accessoBiblioteca.addItem(bibliotecario);
        accessoBiblioteca.addItem(createDoor("pasillo", game, accessoBiblioteca, pasillo));
        accessoBiblioteca.addItem(bibliotecaDoor);

        return accessoBiblioteca;
    }

    private static ComponentContainer createBiblioteca(Game game, ComponentContainer accesoBiblioteca) {
        ComponentContainer biblioteca = new ComponentContainer("biblioteca");

        biblioteca.addItem(createLibro("libro1", game));
        biblioteca.addItem(createLibro("libro2", game));
        biblioteca.addItem(createLibro("libro3", game));
        biblioteca.addItem(createLibro("libro4", game));
        biblioteca.addItem(createLibro("libro5", game));
        biblioteca.addItem(createLibro("libro6", game));
        biblioteca.addItem(createLibro("libro7", game));
        biblioteca.addItem(createLibro("libro8", game));
        biblioteca.addItem(createLibro("libro9", game));
        biblioteca.addItem(createLibroViejo("libro viejo", game, biblioteca));

        biblioteca.addItem(createDoor("pasillo", game, biblioteca, accesoBiblioteca));

        return biblioteca;
    }

    private static ComponentInterface createLibroViejo(String name, Game game, ComponentContainer biblioteca) {
        //TODO definir este libro especial
        ComponentContainer libro = new ComponentContainer(name);

        libro.addBehavior(MOVE, new ContainerDrop(game, libro));
        libro.addItem(createDoor("sotano", game, biblioteca, createSotano(game, biblioteca)));

        return libro;
    }

    private static ComponentContainer createSotano(Game game, ComponentContainer biblioteca) {
        ComponentSimple escalera = new ComponentSimple("escalera");
        ComponentInterface baranda = new ComponentSimple("baranda");

        Behavior killPlayer = (modifier) -> {
            game.lostGame();
            return "you died!";
        };

        escalera.addBehavior(USE, killPlayer);
        baranda.addBehavior(USE, new CrossWithRule(game, createSotanoAbajo(game), createMuereSiNoTieneMartillo(game)));

        baranda.addBehavior("use", killPlayer);

        ComponentContainer sotano = new ComponentContainer("sotano");
        sotano.addItem(createDoor("biblioteca", game, sotano, biblioteca));
        sotano.addItem(escalera);
        sotano.addItem(baranda);

        return sotano;
    }

    private static Rule createMuereSiNoTieneMartillo(Game game) {
        return new Rule() {
            @Override
            public boolean satisfiesRule() {
                return game.getPlayer().playerHasItem(new ComponentSimple("martillo"));
            }

            @Override
            public String reasonsOfRuleFail() {
                game.lostGame();
                return "No tiene el martillo";
            }
        };
    }

    private static ComponentContainer createSotanoAbajo(Game game) {
        ComponentContainer sotanoAbajo = new ComponentContainer("sotano abajo");

        ComponentContainer afuera = createAfuera();
        ComponentInterface ventana  = createDoor("ventana", game, sotanoAbajo, afuera);
        ventana.addBehavior("break", new CrossWithRule(game, afuera,
                new PlayerHasRule(game, new ComponentSimple("martillo"))));

        sotanoAbajo.addItem(ventana);

        return sotanoAbajo;
    }

    private static ComponentContainer createAfuera() {
        return new ComponentContainer("afuera");
    }

    private static ComponentInterface createLibro(String name, Game game) {
        ComponentInterface libro = new ComponentSimple(name);
        libro.addBehavior(PICK, new Pick(game, libro));
        return libro;
    }

    private static ComponentInterface createLockedDoor(String doorName, Game game, ComponentContainer to) {
        ComponentInterface door = new ComponentSimple(doorName);
        door.addBehavior(GOTO, new CrossWithRule(game, to, createRuleForBibliotecario()));
        return door;
    }

    private static Rule createRuleForBibliotecario() {
        //TODO definir como se pone la gorra el bibliotecario
        return new Rule() {
            @Override
            public boolean satisfiesRule() {
                return false;
            }

            @Override
            public String reasonsOfRuleFail() {
                return null;
            }
        };
    }

    private static ComponentInterface createDoor(String doorName, Game game, ComponentContainer from, ComponentContainer to) {
        ComponentInterface door = new ComponentSimple(doorName);
        door.addBehavior(GOTO, new Cross(game, to));
        return door;
    }

    private static class CajaFuerteDrop implements Behavior {
        Game game;
        ComponentContainer item;
        String keyRequired;

        public CajaFuerteDrop(Game game, ComponentContainer item, String keyRequired) {
            this.game = game;
            this.item = item;
            this.keyRequired = keyRequired;
        }

        public String execute(String modifier) {
            System.out.println(modifier);
            StringBuffer message = new StringBuffer();
            if (game.getPlayer().playerHasItem(keyRequired)) {
                message.append("You open the caja fuerte.");
                for (String itemName : item.listOfComponents()) {
                    ComponentInterface component = item.removeItem(itemName);
                    game.getPlayer().putItemInRoom(component);
                    message.append(itemName + " dropped. ");
                }

                return message.toString();
            }
            return "It's locked.";
        }
    }

    private static class Store implements Behavior {
        Game game;
        ComponentContainer container;

        public Store(Game game, ComponentContainer container) {
            this.game = game;
            this.container = container;
        }

        @Override
        public String execute(String modifier) {
            if (modifier.equals(FOTO) && game.getPlayer().playerHasItem(modifier)) {
                container.addItem(game.getPlayer().obtainItemInventory(modifier));
                game.getPlayer().removeItem(modifier);
                container.addBehavior("isValid", new True());
                return "Pasted your photo in there. You now have a valid credencial.";
            }

            return "You don't have that item.";
        }

        private static class True implements Behavior {
            @Override
            public String execute(String modifier) {
                return "true";
            }
        }
    }

    private static class ShowCredencial implements Behavior {
        Game game;
        ComponentInterface doorToUnlock;
        ComponentContainer accesoBiblioteca;

        public ShowCredencial(Game game, ComponentInterface doorToUnlock, ComponentContainer accesoBiblioteca) {
            this.game = game;
            this.doorToUnlock = doorToUnlock;
            this.accesoBiblioteca = accesoBiblioteca;
        }

        @Override
        public String execute(String modifier) {
            ComponentInterface component = game.getPlayer().obtainItemInventory(modifier);
            if (component.getName().equals(CREDENCIAL) && component.doAction("isValid", "").equals("true")) {
                doorToUnlock.addBehavior(GOTO, new Cross(game, createBiblioteca(game, accesoBiblioteca)));
                return "Good, you can now go to the library.";
            }
            return "Show me your crendential.";
        }
    }
}
