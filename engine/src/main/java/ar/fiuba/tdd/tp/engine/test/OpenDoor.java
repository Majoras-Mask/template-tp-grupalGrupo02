package ar.fiuba.tdd.tp.engine.test;
//
//import ar.fiuba.tdd.tp.game.Game;
//import ar.fiuba.tdd.tp.game.commons.condition.Condition;
//import ar.fiuba.tdd.tp.game.commons.condition.have.HaveType;
//import ar.fiuba.tdd.tp.game.commons.condition.have.PlayerHave;
//import ar.fiuba.tdd.tp.game.commons.condition.have.ScenarioHave;
//import ar.fiuba.tdd.tp.game.commons.condition.lock.LockCondition;
//import ar.fiuba.tdd.tp.game.commons.condition.lock.LockType;
//import ar.fiuba.tdd.tp.game.commons.constraint.Constraint;
//import ar.fiuba.tdd.tp.game.commons.constraint.ConstraintImpl;
//import ar.fiuba.tdd.tp.game.component.Component;
//import ar.fiuba.tdd.tp.game.component.ComponentImpl;
//import ar.fiuba.tdd.tp.game.component.state.ComponentState;
//import ar.fiuba.tdd.tp.game.component.state.LockState;
//import ar.fiuba.tdd.tp.game.component.state.OpenState;
//import ar.fiuba.tdd.tp.game.component.state.PickState;
//import ar.fiuba.tdd.tp.game.mission.Mission;
//import ar.fiuba.tdd.tp.game.mission.MissionImpl;
//import ar.fiuba.tdd.tp.game.player.Inventory;
//import ar.fiuba.tdd.tp.game.player.Player;
//import ar.fiuba.tdd.tp.game.player.PlayerImpl;
//import ar.fiuba.tdd.tp.game.player.action.Action;
//import ar.fiuba.tdd.tp.game.player.action.ActionType;
//import ar.fiuba.tdd.tp.game.player.action.impl.LookAround;
//import ar.fiuba.tdd.tp.game.player.action.impl.Open;
//import ar.fiuba.tdd.tp.game.player.action.impl.Pick;
//import ar.fiuba.tdd.tp.game.player.action.impl.WhatToDoWith;
//import ar.fiuba.tdd.tp.game.player.action.resolver.ActionResolver;
//import ar.fiuba.tdd.tp.game.scenario.Scenario;
//import ar.fiuba.tdd.tp.game.scenario.context.Context;
//import ar.fiuba.tdd.tp.game.scenario.context.ContextImpl;
//
//import java.util.*;
//
//public class OpenDoor implements Game {
//
//    private final Player player;
//    private final Mission mission;
//
//    public OpenDoor() {
//        List<Component> room1Components = createRoom1Components();
//        List<Component> room2Components = createRoom2Components();
//
//        this.player = getPlayer(new ContextImpl("room1", room1Components));
//        List<Player> players = new ArrayList<>();
//        players.add(player);
//
//        Scenario scenario1 = new Scenario(players, room1Components);
//        Scenario scenario2 = new Scenario(new ArrayList<>(), room2Components);
//
//        this.mission = createMission(scenario1, scenario2);
//
//    }
//
//    private static List<Component> createRoom1Components() {
//        final Component key = createKey();
//
//        return new ArrayList<Component>() {
//            {
//                add(key);
//                add(createDoor(key));
//            }
//        };
//    }
//
//    private static List<Component> createRoom2Components() {
//        return new ArrayList<>();
//    }
//
//    private Mission createMission(Scenario scenario1, Scenario scenario2) {
//        List<Condition> winConditions = new ArrayList<>();
//
//        winConditions.add(new PlayerHave(this.player, "key", HaveType.HAVE));
//        winConditions.add(new ScenarioHave(HaveType.NOT_HAVE, scenario1, this.player));
//        winConditions.add(new ScenarioHave(HaveType.HAVE, scenario2, this.player));
//
//        return new MissionImpl(winConditions, new ArrayList<>());
//    }
//
//    private static PlayerImpl getPlayer(Context context) {
//        final Inventory inventory = new Inventory(new ArrayList<>(), 10);
//        return new PlayerImpl(createActionResolver(inventory, context), inventory);
//    }
//
//    @Override
//    public Boolean isWon() {
//        return this.mission.isAccomplished();
//    }
//
//    private static ActionResolver createActionResolver(Inventory inventory, Context context) {
//        final Set<Action> actions = new HashSet<>();
//
//        actions.add(new LookAround(context));
//        actions.add(new WhatToDoWith(context));
//        actions.add(new Pick(inventory, context));
//        actions.add(new Open(context));
//
//        return new ActionResolver(actions);
//    }
//
//    private static Component createKey() {
//        return new ComponentImpl("key", new ArrayList<ComponentState>() {
//            {
//                add(new PickState(Boolean.FALSE, new HashMap<>()));
//            }
//        });
//    }
//
//    /**
//     * This door has to states that compose it.
//     * -Lock state controls the lock of the door
//     * -Open state controls if the door is open or no.
//     * It also has a constraint (It can not be opened if was not unlocked first)
//     */
//    private static Component createDoor(Component key) {
//        final LockState lockState = createLockState(key);
//        final OpenState openState = createOpenState(lockState);
//
//        return new ComponentImpl("door", new ArrayList<ComponentState>() {
//            {
//                add(openState);
//                add(lockState);
//            }
//        });
//    }
//
//    private static OpenState createOpenState(LockState lockState) {
//        return new OpenState(Boolean.FALSE, new HashMap<ActionType, Constraint>() {
//            {
//                put(ActionType.OPEN, createLockConstraint(lockState));
//            }
//        });
//    }
//
//    @Override
//    public Boolean isLost() {
//        return this.mission.isFailed();
//    }
//
//    private static LockState createLockState(Component key) {
//        return new LockState(key, Boolean.TRUE, new HashMap<>());
//    }
//
//    @Override
//    public String getName() {
//        return "Open Door";
//    }
//
//    private static Constraint createLockConstraint(LockState lockState) {
//        return new ConstraintImpl(new ArrayList<Condition>() {
//            {
//                add(new LockCondition(LockType.UNLOCK, lockState));
//            }
//        });
//    }
//
//    @Override
//    public String doCommand(String command) {
//        return this.player.doCommand(command);
//    }
//
//    @Override
//    public String help() {
//        return "eehrgm.. commands?";
//    }
//}
