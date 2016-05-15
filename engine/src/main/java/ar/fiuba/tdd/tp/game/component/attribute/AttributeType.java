package ar.fiuba.tdd.tp.game.component.attribute;

public enum AttributeType {

    OPENABLE("open"), PICKABLE("pick"), LOCKABLE("lock/unlock");

    private final String triggerAction;


    AttributeType(String triggerAction) {
        this.triggerAction = triggerAction;
    }

    public String getTriggerAction() {
        return this.triggerAction;
    }

}
