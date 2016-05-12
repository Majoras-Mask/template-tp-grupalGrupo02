package ar.fiuba.tdd.tp.engine.commands;

public class GameCommand {
    private CommandParser parser;
    private CommandExecutor executor;

    public GameCommand(CommandParser parser, CommandExecutor executor) {
        this.parser = parser;
        this.executor = executor;
    }

    public String checkCommand(String command) {
        return parser.parse(command);
    }

    public String doCommand(String parameter) {
        return executor.execute(parameter);
    }
}
