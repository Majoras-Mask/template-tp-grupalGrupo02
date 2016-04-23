package ar.fiuba.tdd.tp;

/**
 * Created by manuelcruz on 22/04/2016.
 */
public class NullIpPort implements IpPort{
    @Override
    public String getIp() {
        return null;
    }

    @Override
    public String getPort() {
        return null;
    }

    @Override
    public boolean isValid() {
        return false;
    }
}
