
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.ClientV2;

public class HelpCommand extends ClientCommand {

    private final String gameName;

    public HelpCommand(ClientV2 client, String gameName) {
        super(client);
        this.gameName = gameName;
    }

    @Override
    public void execute() {
        this.client.help(gameName);
    }

}
