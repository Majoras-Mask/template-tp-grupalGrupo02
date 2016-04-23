package ar.fiuba.tdd.tp.config;

import java.util.Objects;

public class ConnectorSettings {

    private static final long READ_TIMEOUT = 10000;
    private static final long CONNECTION_TIMEOUT = 50000;

    private String host;
    private int port;
    private long readTimeout;
    private long connectionTimeout;

    public ConnectorSettings() {
    }

    public ConnectorSettings(String host, int port, long readTimeout, long connectionTimeout) {
        this.host = host;
        this.port = port;
        this.readTimeout = readTimeout;
        this.connectionTimeout = connectionTimeout;
    }

    public ConnectorSettings(String host, int port) {
        this(host, port, READ_TIMEOUT, CONNECTION_TIMEOUT);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

    @Override
    public String toString() {
        return "ConnectorSettings{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", readTimeout=" + readTimeout +
                ", connectionTimeout=" + connectionTimeout +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ConnectorSettings that = (ConnectorSettings) o;
        return port == that.port &&
                readTimeout == that.readTimeout &&
                connectionTimeout == that.connectionTimeout &&
                Objects.equals(host, that.host);
    }

    @Override
    public int hashCode() {
        return Objects.hash(host, port, readTimeout, connectionTimeout);
    }
}
