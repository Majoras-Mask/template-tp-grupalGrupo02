package ar.fiuba.tdd.tp.engine.utils;

import ar.fiuba.tdd.tp.engine.commands.content.CommandExecutor;
import ar.fiuba.tdd.tp.engine.commands.content.CommandValidatorContent;
import ar.fiuba.tdd.tp.engine.commands.game.CommandParser;
import ar.fiuba.tdd.tp.engine.commands.game.CommandValidator;
import ar.fiuba.tdd.tp.engine.commands.game.GameCommand;
import ar.fiuba.tdd.tp.engine.elements.Content;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by manuelcruz on 19/05/2016.
 */
public class CommandsUtils {

    private static CommandValidator getPatternValidator(String regex) {
        return (command) -> {
            Pattern pattern = Pattern.compile(regex);
            Matcher matcher = pattern.matcher(command);
            return matcher.find();
        };
    }

    public static CommandValidatorContent contentHasItem(Content content, String itemName) {
        return (params) -> content.has(itemName);
    }

    public static CommandValidatorContent contentDoesntHaveItem(Content content, String itemName) {
        return (params) -> !content.has(itemName);
    }

    public static CommandValidatorContent noCondition() {
        return (params) -> true;
    }

    public static CommandValidatorContent contentParamDoesntHaveItem(Content content, int paramNum, String itemName) {
        return (params) -> !content.get(params[paramNum]).has(itemName);
    }

    public static CommandValidatorContent multipleConditions(CommandValidatorContent... conditions) {
        return (params) -> {
            for (CommandValidatorContent condition : conditions) {
                if (!condition.check(params)) {
                    return false;
                }
            }
            return true;
        };
    }

    public static CommandValidatorContent contentContainerDoesntHaveItem(Content content, String itemName) {
        return (params) -> !content.getContainer().has(itemName);
    }

    public static CommandExecutor removeFromHerePutOnThere(Content from, Content to, Content whatToRemove, String messageOutput) {
        return (params) -> {
            to.put(from.take(whatToRemove.getName()));
            return messageOutput;
        };
    }

    public static CommandExecutor putContentInContentParam(Content what, Content where, int paramNum, String message) {
        return (params) -> {
            where.get(params[paramNum]).put(what);
            return message;
        };
    }

    public static CommandExecutor removeFromContainerPutOnThere(Content from, Content to, String message) {
        return (params) -> {
            to.put(from.getContainer().take(from.getName()));
            return message;
        };
    }

    public static CommandExecutor multipleCommands(String message, CommandExecutor... commands) {
        return (params) -> {
            for (CommandExecutor command : commands) {
                command.execute(params);
            }
            return message;
        };
    }

    public static CommandExecutor removeItemFromContent(Content content, String itemName, String message) {
        return (params) -> {
            content.take(itemName);
            return message;
        };
    }

    private static CommandParser getEmptyParser() {
        return (command) -> new String[0];
    }

    private static CommandParser getParamParser(Integer position) {
        return (command) -> {
            String[] split = command.split(" ");
            String[] params = new String[1];
            params[0] = split[position];
            return params;
        };
    }

    public static GameCommand getSameRoomCommand(String regex, String commandName, Content player, Integer parserPosition) {
        return new GameCommand(getPatternValidator(regex), (params) -> {
            Content playerRoom = player.getContainer();
            if (playerRoom.has(params[0])) {
                return playerRoom.get(params[0]).doCommand(commandName);
            } else {
                return "Can't do " + commandName + " on " + params[0];
            }
        }, getParamParser(parserPosition));
    }

    public static GameCommand getLookAroundCommand(String regex, Content player) {
        return new GameCommand(getPatternValidator(regex), (params) -> {
            Content playerRoom = player.getContainer();
            return playerRoom.getName() + " has " + playerRoom.getContentsList();
        }, getEmptyParser());
    }

    public static void addPickCommand(Content player, Content content, String commandName) {
        content.addCommand(commandName, (params) -> true, (params) -> {
            player.put(player.getContainer().take(content.getName()));
            return "You picked a " + content.getName();
        });
    }
}
