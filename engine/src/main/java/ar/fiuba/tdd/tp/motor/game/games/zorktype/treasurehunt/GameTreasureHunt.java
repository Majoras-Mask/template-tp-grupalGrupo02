package ar.fiuba.tdd.tp.motor.game.games.zorktype.treasurehunt;

import ar.fiuba.tdd.tp.motor.game.components.*;
import ar.fiuba.tdd.tp.motor.game.components.gamestatus.GameStatusLost;
import ar.fiuba.tdd.tp.motor.game.components.gamestatus.GameStatusWon;
import ar.fiuba.tdd.tp.motor.game.games.zorktype.ZorkTypeGame;


public class GameTreasureHunt extends ZorkTypeGame {
    private BuilderZork builder;
    private ComponentRoom room1;
    private ComponentRoom room2;
    private ComponentRoom room3;
    private ComponentRoom room4;
    private ComponentRoom room5;
    private ComponentTreasure treasure;

    public GameTreasureHunt() {
        builder = new BuilderZork();
        createRooms();
        createEscenary();
        this.getGamePlayer().setPickCondition(new PickConditionLessThanTwoItems());
    }

    private void createRooms() {
        room1 = builder.createRoom();
        room2 = builder.createRoom();
        room3 = builder.createRoom();
        room4 = builder.createRoom();
        room5 = builder.createRoom();
    }

    private ComponentKey createBoxWithKeyIn(ComponentRoom room) {
        ComponentBox box = builder.createBox();
        ComponentKey key = builder.createKey();
        box.setComponent(key);
        room1.addComponent(box);
        return key;
    }

    private void createEscenary() {
        createEscenary1();
        createEscenary2();
        createEscenary3();
        createEscenary4();
        createEscenary5();
    }

    private void createEscenary1() {
        ComponentKey key = createBoxWithKeyIn(room1);
        builder.createNormalDoor(room1,room2, key);
    }

    private void createEscenary2() {
        ComponentCloset closet = builder.createCloset();
        ComponentBox box = builder.createBox();
        ComponentAntidote antidote = builder.createAntidote();
        ComponentPoison poisson = builder.createPoisson();
        closet.addComponent(poisson);
        ComponentKey key = builder.createKey();
        builder.createNormalDoor(room2,room3,key);
        box.setComponent(antidote);
        closet.addComponent(key);
        closet.addComponent(box);
        room2.addComponent(closet);
    }

    private void createEscenary3() {
        ComponentBox emptyBox = builder.createBox();
        ComponentKey key = builder.createKey();
        room3.addComponent(emptyBox);
        room3.addComponent(key);
        builder.createNormalDoor(room3,room4,key);
    }

    private void createEscenary4() {
        room4.addComponent(builder.createBox());
        builder.createNormalDoor(room4,room5, null); // Unlocked
    }

    private void createEscenary5() {
        this.treasure = new ComponentTreasure();
        room5.addComponent(treasure);
    }

    @Override
    public String welcomeMessage() {
        return "La busqueda del tesoro perdido";
    }

    @Override
    public boolean checkIfGameIsFinished() {
        if (this.getGamePlayer().isDead()) {
            this.gameStatus = new GameStatusLost();
            return true;
        }
        if (getCurrentRoom() == room1 && getPlayerItems().contains(treasure)) {
            this.gameStatus = new GameStatusWon();
            return true;
        }

        return false;
    }
}
