package komunikacija;

import java.io.Serializable;

public class Odgovor implements Serializable {
    private Object odgovor;

    public Odgovor() {
    }

    public Odgovor(Object odgovor) {
        this.odgovor = odgovor;
    }

    public Object getOdgovor() {
        return odgovor;
    }

    public void setOdgovor(Object odgovor) {
        this.odgovor = odgovor;
    }
}
