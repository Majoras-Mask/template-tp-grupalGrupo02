
package ar.fiuba.tdd.tp.client.input.command.client;

import ar.fiuba.tdd.tp.client.output.ClientResponse;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

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

    public String getGameName() {
        return gameName;
    }
}
