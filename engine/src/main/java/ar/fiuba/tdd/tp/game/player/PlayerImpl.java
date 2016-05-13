package ar.fiuba.tdd.tp.game.player;

import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentContainer;
import ar.fiuba.tdd.tp.game.component.Component;

import java.util.List;

public class PlayerImpl implements Player {

    private final List<Component> inventory;
    private final ComponentContainer room;
    private final BehaviorResolver behaviorResolver;

    public PlayerImpl(List<Component> inventory, ComponentContainer room, BehaviorResolver behaviorResolver) {
        this.inventory = inventory;
        this.room = room;
        this.behaviorResolver = behaviorResolver;
    }

    public String doCommand(String command) {
        return this.behaviorResolver.findBehavior(command).execute(command);
    }

    @Override
    public void addItem(Component component) {

    }


//    public List<String> listOfWhatISee() {
//        return room.listOfComponents();
//    }
//
////    public void setRoom(ComponentContainer room) {
////        this.room = room;
////    }
//
//    public boolean playerHasItem(ComponentInterface item) {
//        return inventory.contains(item);
//    }
//
////    public void addItemToInventory(ComponentInterface item) {
////        inventory.add(item);
////    }
//
//    public boolean seesItemInRoom(ComponentInterface item) {
//        return room.hasItem(item);
//    }
//
//    public boolean seesItemInRoom(String itemName) {
//        return room.hasItem(itemName);
//    }
//
//    public void putItemInRoom(ComponentInterface item) {
//        room.addItem(item);
//    }
//
//    public ComponentInterface obtainItemRoom(String itemName) {
//        if (seesItemInRoom(itemName)) {
//            return room.getItem(itemName);
//        }
//        return null;
//    }
//
//    public boolean removeItemFromRoom(ComponentInterface item) {
//        if (seesItemInRoom(item)) {
//            room.removeItem(item);
//            return true;
//        }
//        return false;
//    }
//
//    public String currentRoomName() {
//        return room.getName();
//    }
//
////    public void addBehavior(String actionString, Behavior behavior) {
////        actions.put(actionString, behavior);
////    }


}
