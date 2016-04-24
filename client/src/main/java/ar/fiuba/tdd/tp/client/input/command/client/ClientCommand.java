
package ar.fiuba.tdd.tp.client.input.command.client;

import ar.fiuba.tdd.tp.client.input.command.Command;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;

import static java.util.Objects.requireNonNull;

public abstract class ClientCommand implements Command {

    protected final CommandProcessor commandProcessor;

    public ClientCommand(CommandProcessor commandProcessor) {
        this.commandProcessor = requireNonNull(commandProcessor);
    }

}
