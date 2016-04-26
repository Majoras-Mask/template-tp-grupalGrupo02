package ar.fiuba.tdd.tp.motor.game.components;


import java.util.List;

public class ComponentUtilities {

    public static final GameComponent getComponent(String whatToGet, List<GameComponent> listComponents) {
        for (GameComponent component : listComponents) {
            if (component.getDescription().equals(whatToGet)) {
                return listComponents.get(listComponents.indexOf(component));
            }
        }
        return null;
    }
}
