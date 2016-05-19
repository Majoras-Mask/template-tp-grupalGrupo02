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
        CommandValidator takeParser = (command) -> {
            Pattern takePattern = Pattern.compile("take .*");
            Matcher takeMatcher = takePattern.matcher(command);
            return takeMatcher.find();
        };
        CommandExecutor takeExecutor = (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("take");
            } else {
                return "Can't do take on " + params[0];
            }
        };

        return new GameCommand(takeParser, takeExecutor, (command) -> new String[0]);
    }

    private GameCommand makeLeave(Content player) {
        CommandValidator leaveParser = (command) -> {
            Pattern leavePattern = Pattern.compile("leave .*");
            Matcher leaveMatcher = leavePattern.matcher(command);
            return leaveMatcher.find();
        };
        CommandExecutor leaveExecutor = (params) -> {
            if (player.has(params[0])) {
                return player.get(params[0]).doCommand("leave");
            } else {
                return "Can't do leave on " + params[0];
            }
        };
        return new GameCommand(leaveParser, leaveExecutor, (command) -> new String[0]);
    }

    private GameCommand makeCross(Content player) {
        CommandValidator crossParser = (command) -> {
            Pattern crossPattern = Pattern.compile("cross .*");
            Matcher crossMatcher = crossPattern.matcher(command);
            return crossMatcher.find();
        };
        CommandExecutor crossExecutor = (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("cross");
            } else {
                return "Can't do cross on " + params[0];
            }
        };
        return new GameCommand(crossParser, crossExecutor, (command) -> new String[0]);
    }

}
