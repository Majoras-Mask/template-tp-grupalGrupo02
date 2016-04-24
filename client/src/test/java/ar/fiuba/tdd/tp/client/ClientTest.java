package ar.fiuba.tdd.tp.client;

import ar.fiuba.tdd.tp.client.connector.Connector;
import ar.fiuba.tdd.tp.client.input.ClientRequest;
import ar.fiuba.tdd.tp.client.input.command.Command;
import ar.fiuba.tdd.tp.client.input.command.client.ExitCommand;
import ar.fiuba.tdd.tp.client.input.command.converter.RequestConverter;
import ar.fiuba.tdd.tp.client.input.supplier.ClientSupplier;
import ar.fiuba.tdd.tp.client.output.consumer.ClientConsumer;
import ar.fiuba.tdd.tp.client.processor.CommandProcessor;
import ar.fiuba.tdd.tp.client.utils.Constants;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

public class ClientTest {

    private Client client;
    private ClientSupplier supplier;
    private ClientConsumer consumer;
    private RequestConverter converter;

    @Before
    public void setUp() throws Exception {
        this.supplier = mock(ClientSupplier.class);
        this.consumer = mock(ClientConsumer.class);
        this.converter = mock(RequestConverter.class);

        this.client = new Client(supplier, consumer, converter);
    }

    @Test
    public void testRun() throws Exception {
        ClientRequest request = new ClientRequest("exit");
        when(this.supplier.getRequest()).thenReturn(request);
        when(this.converter.convert(any(ClientRequest.class))).thenReturn(spy(newExitCommand()));

        this.client.run();

        Mockito.verify(this.supplier).getRequest();
        Mockito.verify(this.consumer).consume(Constants.WELCOME);
        Mockito.verify(this.consumer).consume(Constants.CLOSE_SUCCESSFUL);

        Mockito.verifyNoMoreInteractions(this.consumer, this.supplier);

        Assert.assertFalse(this.client.isRunning());
    }

    private Command newExitCommand() {
        Connector connector = mock(Connector.class);
        CommandProcessor processor = new CommandProcessor(connector);
        processor.setClient(this.client);

        return new ExitCommand(processor);
    }
    
}