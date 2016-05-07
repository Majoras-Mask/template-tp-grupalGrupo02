package ar.fiuba.tdd.tp.engine;

import java.util.List;

public interface PlayerGeneric {
    void setListOfPossibleCommands(List<String> list);

    String doCommand(String message);
}
