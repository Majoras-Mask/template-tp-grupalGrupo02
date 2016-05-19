package ar.fiuba.tdd.tp.engine.commands;

public class GameCommand {
    private CommandValidator validator;
    private CommandExecutor executor;
    private CommandParser parser;

    public GameCommand(CommandValidator validator, CommandExecutor executor, CommandParser parser) {
        this.validator = validator;
        this.executor = executor;
        this.parser = parser;
    }

    public boolean checkCommand(String command) {
        return validator.check(command);
    }

    public String[] parseCommand(String command) {
        return parser.parse(command);
    }

    public String doCommand(String[] params) {
        return executor.execute(params);
    }
}
