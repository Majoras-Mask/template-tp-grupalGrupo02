package ar.fiuba.tdd.tp.engine;

import java.util.List;

public interface PlayerInterface {
    void setListOfPossibleCommands(List<String> list);

    String doCommand(String message);
}
