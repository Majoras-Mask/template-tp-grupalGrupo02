package ar.fiuba.tdd.tp.client.connector;

import ar.fiuba.tdd.tp.client.exception.ConnectorException;
import ar.fiuba.tdd.tp.server.communication.Request;
import ar.fiuba.tdd.tp.server.communication.Response;

import java.io.*;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class ConnectorIO {

    private final ObjectOutput outputStream;
    private final ObjectInput inputStream;

    public ConnectorIO(ObjectOutput outputStream, ObjectInput inputStream) {
        this.outputStream = requireNonNull(outputStream, "OutputStream can't be null!");
        this.inputStream = requireNonNull(inputStream, "InputStream can't be null!");
    }

    public void send(Request request) throws IOException {
        if (isNull(request)) {
            throw new ConnectorException("Null request!");
        }

        this.outputStream.writeObject(request);
        this.outputStream.flush();
    }

    public Response receive() throws IOException, ClassNotFoundException {
         return (Response) this.inputStream.readObject();
    }

    public Response receiveIfAvailable() throws IOException, ClassNotFoundException {
        if(inputStream.available() > 0) {
            return (Response) inputStream.readObject();
        }
        return null;
    }

    public void close() {
        this.closeInputStream();
        this.closeOutputStream();
    }

    private void closeOutputStream() {
        try {
            this.outputStream.close();
        } catch (IOException e) {
            //Do nothing...
        }
    }

    private void closeInputStream() {
        try {
            this.inputStream.close();
        } catch (IOException e) {
            //Do nothing...
        }
    }

}

