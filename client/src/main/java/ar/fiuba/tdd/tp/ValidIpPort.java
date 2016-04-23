package ar.fiuba.tdd.tp;

/**
 * Created by manuelcruz on 22/04/2016.
 */
public class ValidIpPort implements IpPort{
    String ip;
    String port;

    public ValidIpPort(String ip, String port){
        this.ip = ip;
        this.port = port;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public String getPort() {
        return port;
    }

    @Override
    public boolean isValid() {
        return true;
    }
}
