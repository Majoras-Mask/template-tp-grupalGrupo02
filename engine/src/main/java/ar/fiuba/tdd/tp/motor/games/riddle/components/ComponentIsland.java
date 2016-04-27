package ar.fiuba.tdd.tp.motor.games.riddle.components;

import java.util.ArrayList;
import java.util.List;

public class ComponentIsland implements RiddleComponent {

    private List<RiddleComponent> components = new ArrayList<RiddleComponent>();
    private ComponentBoat boat = null;

    public ComponentIsland() {
    }

    public int size() {
        return components.size();
    }

    public void addComponent(RiddleComponent component) {
        this.components.add(component);
    }

    public boolean hasComponent(RiddleComponent comp) {
        return components.contains(comp);
    }

    public void removeBoat() {
        boat = null;
    }

    public void addBoat(ComponentBoat boat) {
        this.boat = boat;
    }

    public ComponentBoat getBoat() {
        return boat;
    }

    public void removeComponent(RiddleComponent comp) {
        components.remove(comp);
    }

}
