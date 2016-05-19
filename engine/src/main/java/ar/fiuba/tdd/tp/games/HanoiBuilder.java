package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.*;
import ar.fiuba.tdd.tp.engine.elements.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class HanoiBuilder implements GameBuilder {

    @Override
    public Game build() {
        Game game = new Game();
        Content room = new Content("room");
        Content stack1 = new Content("stack1");
        Content stack2 = new Content("stack2");
        Content stack3 = new Content("stack3");
        Content player = new Content("player", 1);
        Content disk1 = new Content("disk1");
        Content disk2 = new Content("disk2");
        Content disk3 = new Content("disk3");
        disk1.addCommand("take", (params) -> true, (params) -> {
            String stackName = disk1.getContainer().getName();
            player.put(disk1.getContainer().take("disk1"));
            return "Picked disk1 from " + stackName;
        });
        disk2.addCommand("take", (params) -> !disk2.getContainer().has("disk1"), (params) -> {
            String stackName = disk2.getContainer().getName();
            player.put(disk2.getContainer().take("disk2"));
            return "Picked disk2 from " + stackName;
        });
        disk3.addCommand("take", (params) -> !disk3.getContainer().has("disk1") && !disk3.getContainer().has("disk2"), (params) -> {
            String stackName = disk3.getContainer().getName();
            player.put(disk3.getContainer().take("disk3"));
            return "Picked disk3 from " + stackName;
        });
        disk1.addCommand("put", (params) -> true, (params) -> {
            room.get(params[1]).put(player.take("disk1"));
            return "Placed disk1 on " + params[1];
        });
        disk2.addCommand("put", (params) -> !room.get(params[1]).has("disk1"), (params) -> {
            room.get(params[1]).put(player.take("disk2"));
            return "Placed disk2 on " + params[1];
        });
        disk3.addCommand("put", (params) -> !room.get(params[1]).has("disk1") && !room.get(params[1]).has("disk2"), (params) -> {
            room.get(params[1]).put(player.take("disk3"));
            return "Placed disk3 on " + params[1];
        });
        stack1.put(disk1);
        stack1.put(disk2);
        stack1.put(disk3);
        room.put(player);
        room.put(stack1);
        room.put(stack2);
        room.put(stack3);
        WinCondition allDisksInThirdStack = () -> stack3.has("disk1") && stack3.has("disk2") && stack3.has("disk3");
        GameCommand take = makeTake(player);
        GameCommand put = makePut(player);
        game.setWinCondition(allDisksInThirdStack);
        game.setCommand(take);
        game.setCommand(put);
        return game;
    }

    private GameCommand makeTake(Content player) {
        CommandValidator takeParser = (command) -> {
            Pattern takePattern = Pattern.compile("take .* from .*");
            Matcher takeMatcher = takePattern.matcher(command);
            return takeMatcher.find();
        };
        CommandExecutor takeExecutor = (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[1]) && playerRoom.get(params[1]).has(params[0])) {
                return playerRoom.get(params[1]).get(params[0]).doCommand("take");
            } else {
                return "Can't do take on " + params[0] + " from " + params[1];
            }
        };
        return new GameCommand(takeParser, takeExecutor, (command) -> new String[0]);
    }

    private GameCommand makePut(Content player) {
        CommandValidator putParser = (command) -> {
            Pattern putPattern = Pattern.compile("put .* on .*");
            Matcher putMatcher = putPattern.matcher(command);
            return putMatcher.find();
        };
        CommandExecutor putExecutor = (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[1]) && player.has(params[0])) {
                return player.get(params[0]).doCommand("put", params);
            } else {
                return "Can't do put with " + params[0] + " on " + params[1];
            }
        };
        return new GameCommand(putParser, putExecutor, (command) -> new String[0]);
    }

}
