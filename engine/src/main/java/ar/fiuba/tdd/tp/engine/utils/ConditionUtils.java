package ar.fiuba.tdd.tp.engine.utils;

import ar.fiuba.tdd.tp.engine.conditions.GameCondition;
import ar.fiuba.tdd.tp.engine.elements.Content;

public class ConditionUtils {
    public static GameCondition contentHasItem(Content content, String itemName) {
        return () -> content.has(itemName);
    }

    public static GameCondition neverHappens() {
        return () -> false;
    }
}
