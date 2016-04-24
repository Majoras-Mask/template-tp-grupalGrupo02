
package ar.fiuba.tdd.tp.client.input.command.client;

import ar.fiuba.tdd.tp.client.output.ClientResponse;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

public class ExitCommand extends ClientCommand {

    public ExitCommand(CommandProcessor commandProcessor) {
        super(commandProcessor);
    }

    @Override
    public ClientResponse execute() {
        return this.commandProcessor.close();
    }
}
