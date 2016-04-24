package ar.fiuba.tdd.tp.factory.input.supplier;

import ar.fiuba.tdd.tp.factory.input.converter.CommandConverterFactory;
import ar.fiuba.tdd.tp.input.supplier.CommandSupplier;
import ar.fiuba.tdd.tp.input.supplier.StdinSupplier;

public class CommandSupplierFactory {

    private final CommandConverterFactory commandConverterFactory;

    public CommandSupplierFactory(CommandConverterFactory commandConverterFactory) {
        this.commandConverterFactory = commandConverterFactory;
    }

    public CommandSupplier createStdinSupplier() {
        return new StdinSupplier(this.commandConverterFactory.createConverters());
    }
}
