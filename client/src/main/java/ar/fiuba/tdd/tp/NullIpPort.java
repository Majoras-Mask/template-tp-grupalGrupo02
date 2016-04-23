package ar.fiuba.tdd.tp;

/**
 * Created by manuelcruz on 22/04/2016.
 */
public class NullIpPort implements IpPort{
    boolean valid;

    public NullIpPort() {
        valid = false;
    }

    @Override
    public String getIp() {
        return "0.0.0.0";
    }

    @Override
    public String getPort() {
        return "0";
    }

    @Override
    public boolean isValid() {
        return valid;
    }

    public void validate() {
        valid = true;
    }
}
