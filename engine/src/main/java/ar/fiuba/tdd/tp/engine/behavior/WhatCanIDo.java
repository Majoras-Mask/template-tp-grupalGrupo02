package ar.fiuba.tdd.tp.engine.behavior;

import ar.fiuba.tdd.tp.engine.gamecomponents.ComponentInterface;

public class WhatCanIDo implements Behavior {
    ComponentInterface item;
    private static final String WHAT_YOU_CAN = "You can: ";
    private String whatYouCan = WHAT_YOU_CAN;

    public WhatCanIDo(ComponentInterface item) {
        this.item = item;
    }

    public void changeDefaultStart(String message) {
        whatYouCan = message;
    }

    public String execute(String modifier) {
        StringBuffer message = new StringBuffer();
        message.append(whatYouCan);
        for (String action : item.getListOfActions()) {
            message.append(action + ". ");
        }
        return message.toString();
    }
}
