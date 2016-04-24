package ar.fiuba.tdd.tp.connector.config;

public class ConnectorSettings {

    private static final Long READ_TIMEOUT = 10000L;
    private static final Long CONNECTION_TIMEOUT = 50000L;

    private String host;
    private Integer port;
    private Long readTimeout;
    private Long connectionTimeout;

    public ConnectorSettings() {
    }

    public ConnectorSettings(String host, Integer port, Long readTimeout, Long connectionTimeout) {
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

    public Long getReadTimeout() {
        return readTimeout;
    }

    public void setReadTimeout(Long readTimeout) {
        this.readTimeout = readTimeout;
    }

    public Long getConnectionTimeout() {
        return connectionTimeout;
    }

    public void setConnectionTimeout(Long connectionTimeout) {
        this.connectionTimeout = connectionTimeout;
    }

}
