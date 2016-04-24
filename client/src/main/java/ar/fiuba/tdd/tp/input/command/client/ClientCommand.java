
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.CommandProcessor;
import ar.fiuba.tdd.tp.input.command.InputCommand;

public abstract class ClientCommand implements InputCommand {

    protected final CommandProcessor commandProcessor;

    public ClientCommand(CommandProcessor commandProcessor) {
        this.commandProcessor = commandProcessor;
    }

}
