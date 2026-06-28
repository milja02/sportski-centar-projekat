package operacije;

import repository.Repository;
import repository.db.DBRepository;
import repository.db.impl.DBRepositoryGeneric;

public abstract class ApstraktnaGenerickaOperacija {
    protected final Repository broker;

    public ApstraktnaGenerickaOperacija() {
        this(new DBRepositoryGeneric());
    }

    protected ApstraktnaGenerickaOperacija(Repository broker) {
        this.broker = broker;
    }
    
    public final void izvrsi(Object objekat, String kljuc) throws Exception{
            try{
                preduslovi(objekat);
                zapocniTransakciju();
                izvrsiOperaciju(objekat, kljuc);
                potvrdiTransakciju();
            }catch(Exception e){
                ponistiTransakciju();
                throw e;
            }
    }
    
    protected abstract void preduslovi(Object param) throws Exception;
    protected abstract void izvrsiOperaciju(Object param, String kljuc) throws Exception;
    private void zapocniTransakciju() throws Exception{
        ((DBRepository) broker).connect();
    }
    private void potvrdiTransakciju() throws Exception{
        ((DBRepository) broker).commit();
    }
    private void ponistiTransakciju() throws Exception{
        ((DBRepository) broker).rollback();
    }
    private void ugasiKonekciju() throws Exception{
        ((DBRepository) broker).disconnect();
    }
    
}
