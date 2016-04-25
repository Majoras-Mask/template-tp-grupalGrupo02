package ar.fiuba.tdd.tp.motor.games.riddle;

import ar.fiuba.tdd.tp.motor.games.Game;
import ar.fiuba.tdd.tp.motor.games.riddle.components.*;

public class GameRiddle implements Game {

    private ComponentIsland islandSouth;
    private ComponentIsland islandNorth;

    private ComponentBoat boat;

    private ComponentSheep sheep;
    private ComponentCabbage cabbage;
    private ComponentWolf wolf;

    private ComponentIsland currentIsland;

    private boolean areSheepAndCabbageTogether;
    private boolean areWolfAndSheepTogether;

    public GameRiddle() {

        islandSouth = new ComponentIsland();
        islandNorth = new ComponentIsland();

        boat = new ComponentBoat();

        sheep = new ComponentSheep();
        cabbage = new ComponentCabbage();
        wolf = new ComponentWolf();

        islandSouth.addComponent(cabbage);
        islandSouth.addComponent(sheep);
        islandSouth.addComponent(wolf);
        islandSouth.addBoat(boat);

        currentIsland = islandSouth;
    }

    private boolean hasAnimals(RiddleComponent animalF, RiddleComponent animalS) {
        return (currentIsland.hasComponent(animalF) && currentIsland.hasComponent(animalS));
    }

    public boolean wolfAndSheepTogether() {
        return areWolfAndSheepTogether;
    }

    public boolean sheepAndCabbageTogether() {
        return areSheepAndCabbageTogether;
    }

    private boolean isSafetoGo() {
        
        areSheepAndCabbageTogether = hasAnimals(sheep, cabbage);
        areWolfAndSheepTogether = hasAnimals(wolf, sheep);

        return ( !areSheepAndCabbageTogether && !areWolfAndSheepTogether);
    }

    private void safeRemove( RiddleComponent animal) {
        currentIsland.removeComponent(animal);
        boat.addComponent(animal);
    }

    private RiddleComponent isAnimal(String animal) {
        if (animal.equals("sheep")) {
            return sheep;
        } else if (animal.equals("wolf")) {
            return wolf;
        } else {
            return cabbage;
        }
    }

    public boolean canTake(String animal) {
        RiddleComponent animalComp = isAnimal(animal);
        return currentIsland.hasComponent(animalComp) && currentIsland.getBoat().canAddComponent();
    }

    public void take(String animal) {
        RiddleComponent animalComp = isAnimal(animal);
        safeRemove(animalComp);
    }

    public boolean boatFull() {
        return !currentIsland.getBoat().canAddComponent();
    }

    public boolean canLeave(String animal) {
        RiddleComponent animalComp = isAnimal(animal);
        return currentIsland.getBoat().canLeaveAnimal(animalComp);
    }

    public void leaveAnimal() {
        ComponentBoat boat = currentIsland.getBoat();
        currentIsland.addComponent(boat.leaveAnimal());
    }

    private ComponentIsland isIsland(String island) {
        if (island.equals("south")) {
            return islandSouth;
        } else {
            return islandNorth;
        }
    }

    public boolean canCross(String island) {
        ComponentIsland islandComp = isIsland(island);
        return !currentIsland.equals(islandComp) && isSafetoGo();
    }

    public void cross(String island) {
        ComponentIsland islandComp = isIsland(island);
        currentIsland.removeBoat();
        islandComp.addBoat(boat);
        currentIsland = islandComp;
    }

    @Override
    public boolean checkIfGameIsFinished() {
        return (islandNorth.size() == 3);
    }
}
