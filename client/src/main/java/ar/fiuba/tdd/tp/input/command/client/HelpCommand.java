
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.output.ClientResponse;

public class HelpCommand extends ClientCommand {

    private final String gameName;

    public HelpCommand(CommandProcessor commandProcessor, String gameName) {
        super(commandProcessor);
        this.gameName = gameName;
    }

    @Override
    public ClientResponse execute() {
        return this.commandProcessor.help(gameName);
    }

}
