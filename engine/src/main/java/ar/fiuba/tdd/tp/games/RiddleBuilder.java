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
        Content northShore = new Content("northShore");
        Content southShore = new Content("southShore");
        Content player = new Content("player", 1);
        Content wolf = new Content("wolf");
        Content sheep = new Content("sheep");
        Content cabbage = new Content("cabbage");
        Content northTransporter = new Content("northShore");
        Content southTransporter = new Content("southShore");
        southShore.put(player);
        southShore.put(wolf);
        southShore.put(sheep);
        southShore.put(cabbage);
        southShore.put(northTransporter);
        northShore.put(southTransporter);
        addContentCommands(player, northShore, northTransporter, southShore, southTransporter, wolf, sheep, cabbage);
        game.setWinCondition(() -> northShore.has("wolf") && northShore.has("sheep") && northShore.has("cabbage"));
        game.setCommand(makeTake(player));
        game.setCommand(makeLeave(player));
        game.setCommand(makeCross(player));
        return game;
    }

    private boolean validCross(Content leavingShore) {
        return !((leavingShore.has("wolf") && leavingShore.has("sheep")) || (leavingShore.has("sheep") && leavingShore.has("cabbage")));
    }

    private void addContentCommands(Content player, Content northShore, Content northTransporter, Content southShore, Content southTransporter, Content wolf, Content sheep, Content cabbage) {
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
    }

    private GameCommand makeTake(Content player) {
        return new GameCommand((command) -> {
            Pattern takePattern = Pattern.compile("take .*");
            Matcher takeMatcher = takePattern.matcher(command);
            return takeMatcher.find();
        }, (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("take");
            } else {
                return "Can't do take on " + params[0];
            }
        }, (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[1];
            params[0] = split[1];
            return params;
        });
    }

    private GameCommand makeLeave(Content player) {
        return new GameCommand((command) -> {
            Pattern leavePattern = Pattern.compile("leave .*");
            Matcher leaveMatcher = leavePattern.matcher(command);
            return leaveMatcher.find();
        }, (params) -> {
            if (player.has(params[0])) {
                return player.get(params[0]).doCommand("leave");
            } else {
                return "Can't do leave on " + params[0];
            }
        }, (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[1];
            params[0] = split[1];
            return params;
        });
    }

    private GameCommand makeCross(Content player) {
        return new GameCommand((command) -> {
            Pattern crossPattern = Pattern.compile("cross .*");
            Matcher crossMatcher = crossPattern.matcher(command);
            return crossMatcher.find();
        }, (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand("cross");
            } else {
                return "Can't do cross on " + params[0];
            }
        }, (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[1];
            params[0] = split[1];
            return params;
        });
    }

}
