package ar.fiuba.tdd.tp.client.connector.config;

public class ConnectorSettings {

    private static final int READ_TIMEOUT = 2000;
    private static final int CONNECTION_TIMEOUT = 3000;

    private String host;
    private int port;
    private int readTimeout;
    private int connectionTimeout;

    public ConnectorSettings() {
    }

    public ConnectorSettings(String host, Integer port, int readTimeout, int connectionTimeout) {
        this.host = host;
        this.port = port;
        this.readTimeout = readTimeout;
        this.connectionTimeout = connectionTimeout;
    }

    public ConnectorSettings(String host, Integer port) {
        this(host, port, READ_TIMEOUT, CONNECTION_TIMEOUT);
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public int getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(int readTimeout) {
        this.readTimeout = readTimeout;
    }

    public int getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(int connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

}
