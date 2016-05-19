package ar.fiuba.tdd.tp.engine.utils;

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

    public static void addPickCommand(Content player, Content content, String contentName, String commandName) {
        content.addCommand(commandName, (params) -> true, (params) -> {
            player.put(player.getContainer().take(contentName));
            return "You picked a " + contentName;
        });
    }
}
