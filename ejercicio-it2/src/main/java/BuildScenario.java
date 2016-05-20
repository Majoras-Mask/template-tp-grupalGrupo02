import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.behavior.Behavior;
import ar.fiuba.tdd.tp.engine.behavior.ContainerDrop;
import ar.fiuba.tdd.tp.engine.behavior.Cross;
import ar.fiuba.tdd.tp.engine.behavior.Pick;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;
import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentSimple;
import ar.fiuba.tdd.tp.engine.rules.PlayerHasRule;
import ar.fiuba.tdd.tp.engine.rules.Rule;

public class BuildScenario {

    private static final String GOTO = "goto";
    private static final String MOVE = "move";
    private static final String PICK = "pick";
    private static final String USE = "use";

    public static ComponentContainer build(Game game) {

        ComponentContainer pasillo = new ComponentContainer("pasillo");
        pasillo.addItem(createDoor("puertaSalon1", game, pasillo, createSalon1(game, pasillo)));
        pasillo.addItem(createDoor("puertaSalon2", game, pasillo, createSalon2(game, pasillo)));
        pasillo.addItem(createDoor("puertaSalon3", game, pasillo, createSalon3(game, pasillo)));
        pasillo.addItem(createDoor("puertaAccesoBiblioteca", game, pasillo, createAccesoBiblioteca(game, pasillo)));

        return pasillo;
    }

    private static ComponentContainer createSalon1(Game game, ComponentContainer pasillo) {
        ComponentContainer salon1 = new ComponentContainer("salon1");

        salon1.addItem(new ComponentSimple("mesa"));

        salon1.addItem(new ComponentSimple("botella licor"));
        salon1.addItem(new ComponentSimple("vaso 1"));
        salon1.addItem(new ComponentSimple("vaso 2"));

        salon1.addItem(new ComponentSimple("silla 1"));
        salon1.addItem(new ComponentSimple("silla 2"));
        salon1.addItem(new ComponentSimple("cuadro de tren"));
        salon1.addItem(crateCuadroBarco(game));
        salon1.addItem(createDoor("pasillo", game, salon1, pasillo));

        return salon1;
    }

    private static ComponentInterface crateCuadroBarco(Game game) {

        //TODO pensar credencial
        ComponentInterface credencial = new ComponentSimple("credencial");

        ComponentContainer cajaFuerte = new ComponentContainer("caja fuerte");
        cajaFuerte.addItem(credencial);

        ComponentContainer cuadroBarco = new ComponentContainer("cuadro de barco");
        cuadroBarco.addBehavior(MOVE, new ContainerDrop(game, cuadroBarco));
        cuadroBarco.addItem(cajaFuerte);

        return cuadroBarco;
    }

    private static ComponentContainer createSalon2(Game game, ComponentContainer pasillo) {
        ComponentContainer salon2 = new ComponentContainer("salon2");

        ComponentInterface martillo = new ComponentSimple("martillo");
        martillo.addBehavior(PICK, new Pick(game, martillo));

        salon2.addItem(martillo);
        salon2.addItem(new ComponentSimple("destornillador 1"));
        salon2.addItem(new ComponentSimple("destornillador 2"));
        salon2.addItem(createDoor("pasillo", game, salon2, pasillo));

        return salon2;
    }

    private static ComponentContainer createSalon3(Game game, ComponentContainer pasillo) {
        ComponentContainer salon3 = new ComponentContainer("salon3");

        ComponentInterface llave = new ComponentSimple("llave");
        llave.addBehavior(PICK, new Pick(game, llave));

        salon3.addItem(llave);
        salon3.addItem(createDoor("pasillo", game, salon3, pasillo));

        return salon3;
    }

    private static ComponentContainer createAccesoBiblioteca(Game game, ComponentContainer pasillo) {
        ComponentContainer accessoBiblioteca = new ComponentContainer("accessoBiblioteca");

        ComponentInterface bibliotecario = new ComponentSimple("bibliotecario");

        accessoBiblioteca.addItem(bibliotecario);
        accessoBiblioteca.addItem(createDoor("pasillo", game, accessoBiblioteca, pasillo));
        accessoBiblioteca.addItem(createLockedDoor("biblioteca", game, accessoBiblioteca, createBiblioteca(game, accessoBiblioteca)));

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
        ComponentContainer sotano = new ComponentContainer("sotano");

        ComponentSimple escalera = new ComponentSimple("escalera");
        ComponentInterface baranda = new ComponentSimple("baranda");

        Behavior killPlayer = (modifier) -> {
            game.lostGame();
            return "you died!";
        };

        escalera.addBehavior(USE, killPlayer);
        baranda.addBehavior(USE, new Cross(game, sotano, createSotanoAbajo(game), createMuereSiNoTieneMartillo(game)));

        baranda.addBehavior("use", killPlayer);

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
        ventana.addBehavior("break", new Cross(game, sotanoAbajo, afuera,
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

    private static ComponentInterface createLockedDoor(String doorName, Game game,
                                                       ComponentContainer from, ComponentContainer to) {
        ComponentInterface door = new ComponentSimple(doorName);
        door.addBehavior(GOTO, new Cross(game, from, to, createRuleForBibliotecario()));
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
        door.addBehavior(GOTO, new Cross(game, from, to, createDummyRule()));
        return door;
    }

    private static Rule createDummyRule() {
        return new Rule() {
            @Override
            public boolean satisfiesRule() {
                return true;
            }

            @Override
            public String reasonsOfRuleFail() {
                return "";
            }
        }        ;
    }
}
