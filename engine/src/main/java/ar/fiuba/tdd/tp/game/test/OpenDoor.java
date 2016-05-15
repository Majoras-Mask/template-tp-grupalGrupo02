package ar.fiuba.tdd.tp.game.test;

public class OpenDoor {

//    private final PlayerImpl player;
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
//    private Mission createMission(Scenario scenario1, Scenario scenario2) {
//        List<Condition> winConditions = new ArrayList<>();
//        List<Condition> loseConditions = new ArrayList<>();
//
//        Condition winCondition = new PlayerHas(this.player, "key", ConditionType.HAVE);
//        Condition winCondition2 = new ScenarioHas(ConditionType.NOT_HAVE, scenario1, this.player);
//        Condition winCondition3 = new ScenarioHas(ConditionType.HAVE, scenario2, this.player);
//
//        winConditions.add(winCondition);
//        winConditions.add(winCondition2);
//        winConditions.add(winCondition3);
//
//        return new MissionImpl(winConditions, loseConditions);
//    }
//
//    private static PlayerImpl getPlayer(Context context) {
//        final Inventory inventory = new Inventory(new ArrayList<>(), 10);
//        return new PlayerImpl(createActionResolver(inventory, context), inventory);
//    }
//
//    private static ActionResolver createActionResolver(Inventory inventory, Context context) {
//        final Set<Action> actions = new HashSet<>();
//
//        final Action lookAround = new LookAround(context);
//        final Action whatToDoWith = new WhatToDoWith(context);
//        final Action pick = new Pick(inventory, context);
//        final Action open = new Open(context);
//
//
//        actions.add(lookAround);
//        actions.add(whatToDoWith);
//        actions.add(pick);
//        actions.add(open);
//
//        return new ActionResolver(actions);
//    }
//
//    private static Component createKey() {
//        List<Attribute> attributes = new ArrayList<>();
//        Attribute pickable = new PickableImpl();
//        attributes.add(pickable);
//
//        return new ComponentImpl("key", attributes);
//    }
//
//    private static Component createDoor() {
//        List<Attribute> attributes = new ArrayList<>();
//        Attribute openable = new OpenableImpl();
//        attributes.add(openable);
//
//        return new ComponentImpl("door", attributes);
//    }
//
//    private static List<Component> createRoom1Components() {
//        List<Component> roomComponents = new ArrayList<>();
//        roomComponents.add(createKey());
//        roomComponents.add(createDoor());
//
//        return roomComponents;
//    }
//
//    private static List<Component> createRoom2Components() {
//        return new ArrayList<>();
//    }
//
//    @Override
//    public String doCommand(String command) {
//        return this.player.doCommand(command);
//    }
//
//    @Override
//    public String getName() {
//        return "Open Door";
//    }
//
//    @Override
//    public Boolean isWon() {
//        return this.mission.isAccomplished();
//    }
//
//    @Override
//    public Boolean isLost() {
//        return this.mission.isFailed();
//    }
//
//    @Override
//    public String help() {
//        return "eehrgm.. commands?";
//    }
}
