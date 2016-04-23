package ar.fiuba.tdd.tp.config;

public class ConnectorSettings {

    private String host;
    private int port;
    private long readTimeout = 80000;
    private long connectionTimeout = 50000;

    public ConnectorSettings() {
    }

    public ConnectorSettings(String host, int port, long readTimeout, long connectionTimeout) {
        this.host = host;
        this.port = port;
        this.readTimeout = readTimeout;
        this.connectionTimeout = connectionTimeout;
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


}
