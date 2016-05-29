package ar.fiuba.tdd.tp.actions;

import ar.fiuba.tdd.tp.Context;
import ar.fiuba.tdd.tp.Logger;

/**
 * Created by kevin on 29/05/16.
 */
public class ActionLog implements Action {

    private String stringToLog;
    private Logger logger;

    public ActionLog(String stringToLog, Logger logger) {
        this.stringToLog = stringToLog;
        this.logger = logger;
    }

    @Override
    public void execute(Context context) {
        logger.log(stringToLog);
    }
}
