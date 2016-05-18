package ar.fiuba.tdd.tp.games;

import ar.fiuba.tdd.tp.engine.*;
import ar.fiuba.tdd.tp.engine.commands.*;
import ar.fiuba.tdd.tp.engine.elements.*;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class RiddleBuilder implements GameBuilder {

    @Override
    public Game build() {
        Game game = new Game();
        Content northShore = new Content("north shore");
        Content southShore = new Content("south shore");
        Content player = new Content("player", 1);
        Content wolf = new Content("wolf");
        Content sheep = new Content("sheep");
        Content cabbage = new Content("cabbage");
        Content northTransporter = new Content("north shore");
        Content southTransporter = new Content("south shore");
        wolf.addCommand("take", (params) -> player.canPut(), (params) -> {
            player.put(player.getContainer().take("wolf"));
            return "take wolf on boat";
        });
        wolf.addCommand("leave", (params) -> player.has("wolf"), (params) -> {
            player.getContainer().put(player.take("wolf"));
            return "leave wolf from boat";
        });
        sheep.addCommand("take", (params) -> player.canPut(), (params) -> {
            player.put(player.getContainer().take("sheep"));
            return "take sheep on boat";
        });
        sheep.addCommand("leave", (params) -> player.has("sheep"), (params) -> {
            player.getContainer().put(player.take("sheep"));
            return "leave sheep from boat";
        });
        cabbage.addCommand("take", (params) -> player.canPut(), (params) -> {
            player.put(player.getContainer().take("cabbage"));
            return "take cabbage on boat";
        });
        cabbage.addCommand("leave", (params) -> player.has("cabbage"), (params) -> {
            player.getContainer().put(player.take("cabbage"));
            return "leave cabbage from boat";
        });
        northTransporter.addCommand("cross", (params) -> validCross(southShore), (params) -> {
            northShore.put(southShore.take("player"));
            return "You have crossed to north";
        });
        southTransporter.addCommand("cross", (params) -> validCross(northShore), (params) -> {
            southShore.put(northShore.take("player"));
            return "You have crossed to south";
        });
        southShore.put(player);
        southShore.put(wolf);
        southShore.put(sheep);
        southShore.put(cabbage);
        southShore.put(northTransporter);
        northShore.put(southTransporter);
        WinCondition allInNorthShore = () -> northShore.has("wolf") && northShore.has("sheep") && northShore.has("cabbage");
        GameCommand take = makeTake(player);
        GameCommand leave = makeLeave(player);
        GameCommand cross = makeCross(player);
        game.setWinCondition(allInNorthShore);
        game.setCommand(take);
        game.setCommand(leave);
        game.setCommand(cross);
        return game;
    }

    private boolean validCross(Content leavingShore) {
        return !((leavingShore.has("wolf") && leavingShore.has("sheep")) || (leavingShore.has("sheep") && leavingShore.has("cabbage")));
    }

    private GameCommand makeTake(Content player) {
        StringToString takeParser = (command) -> {
            Pattern takePattern = Pattern.compile("take .*");
            Matcher takeMatcher = takePattern.matcher(command);
            return takeMatcher.find() ? command.substring(5) : command;
        };
        StringToString takeExecutor = (itemName) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(itemName)) {
                return playerRoom.get(itemName).doCommand("take");
            } else {
                return "Can't do take on " + itemName;
            }
        };

        return new GameCommand(takeParser, takeExecutor);
    }

    private GameCommand makeLeave(Content player) {
        StringToString leaveParser = (command) -> {
            Pattern leavePattern = Pattern.compile("leave .*");
            Matcher leaveMatcher = leavePattern.matcher(command);
            return leaveMatcher.find() ? command.substring(6) : command;
        };
        StringToString leaveExecutor = (itemName) -> {
            if (player.has(itemName)) {
                return player.get(itemName).doCommand("leave");
            } else {
                return "Can't do leave on " + itemName;
            }
        };
        return new GameCommand(leaveParser, leaveExecutor);
    }

    private GameCommand makeCross(Content player) {
        StringToString crossParser = (command) -> {
            Pattern crossPattern = Pattern.compile("cross .*");
            Matcher crossMatcher = crossPattern.matcher(command);
            return crossMatcher.find() ? command.substring(6) : command;
        };
        StringToString crossExecutor = (itemName) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(itemName)) {
                return playerRoom.get(itemName).doCommand("cross");
            } else {
                return "Can't do cross on " + itemName;
            }
        };
        return new GameCommand(crossParser, crossExecutor);
    }

}
