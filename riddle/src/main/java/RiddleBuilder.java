import ar.fiuba.tdd.tp.engine.Game;
import ar.fiuba.tdd.tp.engine.GameBuilder;
import ar.fiuba.tdd.tp.engine.commands.game.GameCommand;
import ar.fiuba.tdd.tp.engine.elements.Content;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

@SuppressWarnings("CPD-START")
public class RiddleBuilder implements GameBuilder {
    private static final int MAXPLAYERS = 1;

    @Override
    public Game build() {
        Content northShore = new Content("northShore");
        Content southShore = new Content("southShore");
        Content wolf = new Content("wolf");
        Content sheep = new Content("sheep");
        Content cabbage = new Content("cabbage");
        Content northTransporter = new Content("northShore");
        Content southTransporter = new Content("southShore");
        southShore.put(wolf);
        southShore.put(sheep);
        southShore.put(cabbage);
        southShore.put(northTransporter);
        northShore.put(southTransporter);

        Game game = new Game(MAXPLAYERS) {
            @Override
            public void addPlayer(int playerID) {
                Content player = new Content("player" + playerID, 1);
                southShore.put(player);
                addContentCommands(player, northShore, northTransporter, southShore, southTransporter, wolf, sheep, cabbage);
                setGameCommands(playerID, player, this);
                this.addWinCondition(playerID, () -> northShore.has(wolf.getName()) && northShore.has(sheep.getName()) && northShore.has(cabbage.getName()));
                this.addLoseCondition(playerID, () -> false);
            }
        };
        return game;
    }

    private void setGameCommands(int playerID, Content player, Game game) {
        game.setCommand(playerID, makeTake(player));
        game.setCommand(playerID, makeLeave(player));
        game.setCommand(playerID, makeCross(player));
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
            northShore.put(southShore.take(player.getName()));
            return "You have crossed to north";
        });
        southTransporter.addCommand("cross", (params) -> validCross(northShore), (params) -> {
            southShore.put(northShore.take(player.getName()));
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