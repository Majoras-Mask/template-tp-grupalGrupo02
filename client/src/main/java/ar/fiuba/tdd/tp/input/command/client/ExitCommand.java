
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.output.ClientResponse;

public class ExitCommand extends ClientCommand {

    public ExitCommand(CommandProcessor commandProcessor) {
        super(commandProcessor);
    }

    @Override
    public ClientResponse execute() {
        return this.commandProcessor.close();
    }
}
