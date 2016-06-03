package ar.fiuba.tdd.tp.commands;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Element;
import ar.fiuba.tdd.tp.ObjectInterface;
import ar.fiuba.tdd.tp.Utility;

import java.util.HashMap;

/**
 * Created by kevin on 28/05/16.
 */
public class CommandConcreteRegex extends CommandConcrete implements Context {

    private HashMap<String, String> lastMap;
    private Context lastContext;

    public CommandConcreteRegex(String command) {
        super(command);
    }

    @Override
    public boolean matches(String command) {
        return Utility.matches(Utility.makeCommandARegex(this.command), command.toLowerCase());
    }

    @Override
    public String execute(String command, Context context) {
        if (matches(command)) {
            this.lastMap = Utility.getObjectGroups(this.command, command);
            this.lastContext = context;
            return super.execute(command, this);
        }

        return "Can't do it.";
    }

    @Override
    public ObjectInterface getObject(String name) {
        if (lastMap.containsKey(name)) {
            return lastContext.getObject(lastMap.get(name));
        }

        return lastContext.getObject(name);
    }

}
