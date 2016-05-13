package ar.fiuba.tdd.tp.game.player.behavior.impl;

import ar.fiuba.tdd.tp.game.component.attribute.Attribute;
import ar.fiuba.tdd.tp.game.component.attribute.AttributeType;
import ar.fiuba.tdd.tp.game.context.GameContext;
import ar.fiuba.tdd.tp.game.player.behavior.OneObjectBehavior;
import ar.fiuba.tdd.tp.game.component.Component;

import java.util.Optional;
import java.util.regex.Pattern;

public abstract class OneObjectBehaviorImpl implements OneObjectBehavior {

    protected final GameContext context;
    private final Pattern commandPattern;
    private final String pattern;

    protected OneObjectBehaviorImpl(GameContext context, String pattern) {
        this.context = context;
        this.pattern = pattern;
        this.commandPattern = Pattern.compile(pattern);
    }

    @Override
    public String execute(String command) {
        final String directObject = command.replaceFirst(pattern, "");
        return this.execute(getDirectObject(directObject));
    }

    private Component getDirectObject(String directObject) {
        return this.context.findComponentByName(directObject);
    }

    @Override
    public Boolean canExecute(String command) {
        return this.commandPattern.matcher(command).find();
    }

    protected Optional<Attribute> getAttributeByType(Component component, AttributeType type) {
        return component.getAttribute(type);
    }
}
