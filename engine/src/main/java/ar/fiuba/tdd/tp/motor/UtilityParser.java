package ar.fiuba.tdd.tp.motor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by kevin on 22/04/16.
 */
public class UtilityParser {
    public static Matcher getMatcher(String patternString, String stringToCheck) {
        Pattern pattern = Pattern.compile(patternString);
        return pattern.matcher(stringToCheck);
    }

    public static boolean matches(String patternString, String stringToCheck) {
        Matcher matcher = getMatcher(patternString, stringToCheck);
        return matcher.find();
    }

    public static String getGroup(String patternString, String stringToCheck, int groupIndex) {
        Matcher matcher = getMatcher(patternString, stringToCheck);
        return matcher.group(groupIndex);
    }
}
