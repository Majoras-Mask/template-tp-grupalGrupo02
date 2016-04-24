package ar.fiuba.tdd.tp.factory.input.supplier;

import ar.fiuba.tdd.tp.client.input.supplier.ClientSupplier;
import ar.fiuba.tdd.tp.client.input.supplier.StdinSupplier;

public class ClientSupplierFactory {

    public ClientSupplier createStdinSupplier() {
        return new StdinSupplier();
    }

}
