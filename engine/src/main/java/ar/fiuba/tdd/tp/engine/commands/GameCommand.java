package ar.fiuba.tdd.tp.engine.commands;

public class GameCommand {
    private StringToString parser;
    private StringToString executor;

    public GameCommand(StringToString parser, StringToString executor) {
        this.parser = parser;
        this.executor = executor;
    }

    public String checkCommand(String command) {
        return parser.convert(command);
    }

    public String doCommand(String parameter) {
        return executor.convert(parameter);
    }
}
