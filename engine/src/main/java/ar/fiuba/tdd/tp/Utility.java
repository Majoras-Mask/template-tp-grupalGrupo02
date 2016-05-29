package ar.fiuba.tdd.tp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kevin on 28/05/16.
 */
public class Utility {

    public static List<String> getTagObjectGroups(String command) {
        List<String> lista = new ArrayList<>();
        int index = command.indexOf('(');
        while (index != -1) {
            int index2 = command.indexOf(')', index);
            lista.add(command.substring(index, index2 + 1));
            index = command.indexOf('(', index2);
        }
        return lista;
    }

    public static HashMap<String, String> getObjectGroups(String regexCommand, String command) {
        List<String> list = getTagObjectGroups(regexCommand);
        String regex = makeCommandARegex(regexCommand);
        List<String> values = getGroups(regex, command);

        HashMap<String,String> map = new HashMap<>();

        if (list.size() == values.size()) {
            for (int i = 0; i < list.size(); i++) {
                map.put(list.get(i), values.get(i));
            }
        }

        return map;
    }

    public static List<String> getGroups(String regex, String command) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(command);
        matcher.find();

        List<String> list = new ArrayList<>();

        for (int i = 1; i <= matcher.groupCount(); i++) {
            list.add(matcher.group(i));
        }
        return list;
    }

    public static String makeCommandARegex(String regexCommand) {
        List<String> list = getTagObjectGroups(regexCommand);
        for (String group: list) {
            regexCommand = regexCommand.replaceFirst(group, ".*");
        }

        return regexCommand;
    }

}
