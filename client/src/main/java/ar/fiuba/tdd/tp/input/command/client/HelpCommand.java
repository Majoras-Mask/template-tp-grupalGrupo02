
package ar.fiuba.tdd.tp.input.command.client;

import ar.fiuba.tdd.tp.ClientV2;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        HelpCommand that = (HelpCommand) o;
        return Objects.equals(gameName, that.gameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gameName);
    }
}
