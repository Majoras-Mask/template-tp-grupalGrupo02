import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.game.GameCommand;
import ar.fiuba.tdd.tp.engine.elements.*;
import ar.fiuba.tdd.tp.engine.utils.CommandsUtils;
import ar.fiuba.tdd.tp.engine.utils.ConditionUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class HanoiBuilder implements GameBuilder {
    private static final int MAXPLAYERS = 1;

    @Override
    public Game build() {
        Content room = new Content("room");
        Content stack1 = new Content("stack1");
        Content stack2 = new Content("stack2");
        Content stack3 = new Content("stack3");
        Content disk1 = new Content("disk1");
        Content disk2 = new Content("disk2");
        Content disk3 = new Content("disk3");
        stack1.put(disk1);
        stack1.put(disk2);
        stack1.put(disk3);
        room.put(stack1);
        room.put(stack2);
        room.put(stack3);

        Game game = new Game(MAXPLAYERS) {
            @Override
            public void addPlayer(int playerID) {
                Content player = new Content("player" + playerID, 1);
                room.put(player);
                addContentCommands(player, room, disk1, disk2, disk3);
                setGameCommands(playerID, player, this);
                this.addWinCondition(playerID,ConditionUtils.multipleConditions(ConditionUtils.contentHasItem(stack3, disk1.getName()), ConditionUtils.contentHasItem(stack3, disk2.getName()), ConditionUtils.contentHasItem(stack3, disk3.getName())));
                this.addLoseCondition(playerID, ConditionUtils.neverHappens());
            }
        };
        return game;
    }

    private void setGameCommands(int playerID, Content player, Game game) {
        game.setCommand(playerID, makeTake(player));
        game.setCommand(playerID, makePut(player));
    }

    private void addContentCommands(Content player, Content room, Content disk1, Content disk2, Content disk3) {
        disk1.addCommand("take",
                CommandsUtils.noCondition(),
                CommandsUtils.removeFromContainerPutOnThere(disk1, player, "Picked disk1 from " + disk1.getContainer().getName()));

        disk2.addCommand("take",
                CommandsUtils.contentContainerDoesntHaveItem(disk2, disk1.getName()),
                CommandsUtils.removeFromContainerPutOnThere(disk2, player, "Picked disk2 from " + disk2.getContainer().getName()));

        disk3.addCommand("take",
                CommandsUtils.multipleConditions(CommandsUtils.contentContainerDoesntHaveItem(disk3, disk1.getName()),
                        CommandsUtils.contentContainerDoesntHaveItem(disk3, disk2.getName())),
                CommandsUtils.removeFromContainerPutOnThere(disk3, player, "Picked disk3 from " + disk3.getContainer().getName()));

        disk1.addCommand("put",
                CommandsUtils.noCondition(),
                CommandsUtils.multipleCommands("Placed disk1 on there",
                    CommandsUtils.removeItemFromContent(player, disk1.getName(), ""),
                    CommandsUtils.putContentInContentParam(disk1, room, 1, "")));

        disk2.addCommand("put",
                CommandsUtils.contentParamDoesntHaveItem(room, 1, disk1.getName()),
                CommandsUtils.multipleCommands("Placed disk2 on there",
                        CommandsUtils.removeItemFromContent(player, disk2.getName(), ""),
                        CommandsUtils.putContentInContentParam(disk2, room, 1, "")));

        disk3.addCommand("put",
                CommandsUtils.multipleConditions(CommandsUtils.contentParamDoesntHaveItem(room, 1, disk1.getName()),
                        CommandsUtils.contentParamDoesntHaveItem(room, 1, disk2.getName())),
                CommandsUtils.multipleCommands("Placed disk3 on there",
                        CommandsUtils.removeItemFromContent(player, disk3.getName(), ""),
                        CommandsUtils.putContentInContentParam(disk3, room, 1, "")));
    }

    private GameCommand makeTake(Content player) {
        return new GameCommand((command) -> {
            Pattern takePattern = Pattern.compile("take .* from .*");
            Matcher takeMatcher = takePattern.matcher(command);
            return takeMatcher.find();
        }, (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[1]) && playerRoom.get(params[1]).has(params[0])) {
                return playerRoom.get(params[1]).get(params[0]).doCommand("take");
            } else {
                return "Can't do take on " + params[0] + " from " + params[1];
            }
        }, (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[2];
            params[0] = split[1];
            params[1] = split[3];
            return params;
        });
    }

    private GameCommand makePut(Content player) {
        return new GameCommand((command) -> {
            Pattern putPattern = Pattern.compile("put .* on .*");
            Matcher putMatcher = putPattern.matcher(command);
            return putMatcher.find();
        }, (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[1]) && player.has(params[0])) {
                return player.get(params[0]).doCommand("put", params);
            } else {
                return "Can't do put with " + params[0] + " on " + params[1];
            }
        }, (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[2];
            params[0] = split[1];
            params[1] = split[3];
            return params;
        });
    }

}