package operacije.licence;

import domen.InstruktorLicenca;
import operacije.ApstraktnaGenerickaOperacija;

public class UbaciLicencuSO extends ApstraktnaGenerickaOperacija {

    @Override
    protected void preduslovi(Object param) throws Exception {
        if (param == null || !(param instanceof InstruktorLicenca)) {
            throw new Exception("Sistem ne može da zapamti licencu.");
        }
        InstruktorLicenca il = (InstruktorLicenca) param;
        if (il.getInstruktor() == null || il.getLicenca() == null) {
            throw new Exception("Sistem ne može da zapamti licencu.");
        }
        if (il.getDatumIzdavanja() == null) {
            throw new Exception("Sistem ne može da zapamti licencu.");
        }
        if (il.getInstruktor().getIdInstruktor() <= 0 || il.getLicenca().getIdLicenca() <= 0) {
            throw new Exception("Sistem ne može da zapamti licencu (neispravan instruktor ili licenca).");
        }
        if (il.getDatumIsteka() != null && !il.getDatumIsteka().after(il.getDatumIzdavanja())) {
            throw new Exception("Datum isteka mora biti posle datuma izdavanja.");
        }
    }

    @Override
    protected void izvrsiOperaciju(Object param, String kljuc) throws Exception {
        broker.add((InstruktorLicenca) param);
    }
}
