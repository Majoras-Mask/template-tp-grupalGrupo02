package ar.fiuba.tdd.tp.game.player.action;

import ar.fiuba.tdd.tp.game.component.Component;
import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.context.GameContext;

import java.util.Optional;
import java.util.regex.Pattern;

public abstract class OneObjectActionImpl implements OneObjectAction {

    protected final GameContext context;
    private final Pattern commandPattern;
    private final String pattern;

    protected OneObjectActionImpl(GameContext context, String pattern) {
        this.context = context;
        this.pattern = pattern;
        this.commandPattern = Pattern.compile(pattern);
    }

    @Override
    public String doExecute(String command) {
        final String directObject = command.replaceFirst(pattern, "");
        final Optional<Component> component = this.getDirectObject(directObject);

        if (!component.isPresent()) {
            return "There is no " + directObject + " in the " + this.context.getName();
        }

        return this.execute(component.get());
    }

    private Optional<Component> getDirectObject(String directObject) {
        return this.context.findComponentByName(directObject);
    }

    @Override
    public Boolean canExecute(String action) {
        return this.commandPattern.matcher(action).find();
    }

    protected Optional<Attribute> getAttributeByType(Component component, AttributeType type) {
        return component.getAttribute(type);
    }
}
