package ar.fiuba.tdd.tp.server.utils;

import java.util.regex.Pattern;

public class PortPattern {
    private static final String from0To9 = "([0-9])"; //0-9
    private static final String from0To99 = from0To9 + "|([1-9][0-9])"; //0-99
    private static final String from0To999 = from0To99 + "|([1-9][0-9]{2})"; //0-999
    private static final String from0To9999 = from0To999 + "|([1-9][0-9]{3})"; //0-9999
    private static final String from0To59999 = from0To9999 + "|([1-5][0-9]{4})"; //0-59999
    private static final String from0To64999 = from0To59999 + "|(6[0-4][0-9]{3})"; //0-64999
    private static final String from0To65499 = from0To64999 + "|(65[0-4][0-9]{2})"; //0-65499
    private static final String from0To65529 = from0To65499 + "|(655[0-2][0-9])"; //0-65529
    private static final String from0To65535 = from0To65529 + "|(6553[0-5])"; //0-65535

    public static Pattern getPattern() {
        return Pattern.compile("(" + from0To65535 + ")");
    }
}
