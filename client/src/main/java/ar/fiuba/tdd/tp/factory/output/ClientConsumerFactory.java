package ar.fiuba.tdd.tp.factory.output;

import ar.fiuba.tdd.tp.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.output.consumer.SysoutPrinter;

public class ClientConsumerFactory {

    public ClientConsumer createSysoutConsumer() {
        return new SysoutPrinter();
    }
}
