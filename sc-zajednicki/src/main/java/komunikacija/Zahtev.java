package komunikacija;

import java.io.Serializable;

public class Zahtev implements Serializable {
    private Operacija operacija;
    private Object params;

    public Zahtev() {
    }

    public Zahtev(Operacija operacija, Object params) {
        this.operacija = operacija;
        this.params = params;
    }

    public Operacija getOperacija() {
        return operacija;
    }

    public void setOperacija(Operacija operacija) {
        this.operacija = operacija;
    }

    public Object getParams() {
        return params;
    }

    public void setParams(Object params) {
        this.params = params;
    }
}
