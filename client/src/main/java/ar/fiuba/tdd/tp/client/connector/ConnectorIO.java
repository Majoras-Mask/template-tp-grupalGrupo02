package ar.fiuba.tdd.tp.client.connector;

import ar.fiuba.tdd.tp.api.Request;
import ar.fiuba.tdd.tp.api.Response;
import ar.fiuba.tdd.tp.client.exception.ConnectorException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import static java.util.Objects.isNull;
import static java.util.Objects.requireNonNull;

public class ConnectorIO {

    private final ObjectOutputStream outputStream;
    private final ObjectInputStream inputStream;

    public ConnectorIO(Socket socket) throws IOException {
        this(new ObjectOutputStream(socket.getOutputStream()), new ObjectInputStream(socket.getInputStream()));
    }

    public ConnectorIO(ObjectOutputStream outputStream, ObjectInputStream inputStream) {
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

