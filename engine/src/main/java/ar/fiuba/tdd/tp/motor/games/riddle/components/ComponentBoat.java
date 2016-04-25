package ar.fiuba.tdd.tp.motor.games.riddle.components;

public class ComponentBoat implements RiddleComponent {

    private RiddleComponent animal = null;

    public ComponentBoat() {

    }

    public boolean canAddComponent() {
        return  (animal == null);
    }

    public void addComponent(RiddleComponent component) {
        this.animal = component;
    }

    public boolean canLeaveAnimal(RiddleComponent animal) {
        return (this.animal != null && this.animal.equals(animal));
    }

    public RiddleComponent leaveAnimal() {
        RiddleComponent animal = this.animal;
        this.animal = null;
        return animal;
    }
}
