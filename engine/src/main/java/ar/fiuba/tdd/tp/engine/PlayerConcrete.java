package ar.fiuba.tdd.tp.engine;

import java.util.List;

public class PlayerConcrete implements PlayerGeneric {
    @Override
    public void setListOfPossibleCommands(List<String> list) {
        
    }

    @Override
    public String doCommand(String message) {
        return null;
    }
    /*
    //Esta lista va a ser necesaria para poder saber que comandos son soportados
    protected List<String> possibleCommands;

    public void setListOfPossibleCommands(List<String> list) {
        possibleCommands = list;
    }

    @Override
    public String doCommand(String message) {

        //Lo que se va a tener que hacer aca es lo siguiente, le llega un mensaje, ver si ese
        //mensaje empieza con algun comando. Nosotros tenemos nuestros posibles comandos en la lista
        //possibleCommands. Si matchea, entonces vamos a tener que buscar ese objeto y si esta a la vista
        //decirle que realice esa accion
        return "";
    }
    */
}
