package ar.fiuba.tdd.tp.output;

import ar.fiuba.tdd.tp.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.output.consumer.ClientEvent;

public class SysoutPrinter implements ClientConsumer {

    private static final String PREFIX = "Client> ";

    @Override
    public void consume(ClientEvent event) {
        //TODO mejorar esto...
        System.out.println(PREFIX + event.getEvent());
    }
}
