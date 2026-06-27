package repository.db.impl;

import domen.ApstraktniDomenskiObjekat;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import repository.db.DBConnectionFactory;
import repository.db.DBRepository;

public class DBRepositoryGeneric implements DBRepository<ApstraktniDomenskiObjekat> {

    @Override
    public List<ApstraktniDomenskiObjekat> getAll(ApstraktniDomenskiObjekat param, String uslov) throws Exception {
        List<ApstraktniDomenskiObjekat> lista = new ArrayList<>();

        String upit = "SELECT * FROM " + param.nazivTabele() + " " + param.alijasTabele() + " " + param.join();

        if (uslov != null) {
            upit += uslov;
        }

        System.out.println(upit);

        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        ResultSet rs = st.executeQuery(upit);
        lista = param.vratiListu(rs);

        rs.close();
        st.close();
        return lista;
    }

    @Override
    public void add(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.nazivTabele() + "(" + param.koloneZaUbacivanje() + ") VALUES (" + param.vrednostiZaUbacivanje() + ")";
        System.out.println(upit);

        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public int addAndReturnId(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "INSERT INTO " + param.nazivTabele() + "(" + param.koloneZaUbacivanje() + ") VALUES (" + param.vrednostiZaUbacivanje() + ")";
        System.out.println(upit);

        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement(ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
        st.executeUpdate(upit, Statement.RETURN_GENERATED_KEYS);
        ResultSet rs = st.getGeneratedKeys();
        int id = -1;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        st.close();
        return id;
    }

    @Override
    public void edit(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "UPDATE " + param.nazivTabele() + " SET " + param.vrednostiZaIzmenu() + " WHERE " + param.primarniKljuc();
        System.out.println(upit);

        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public void delete(ApstraktniDomenskiObjekat param) throws Exception {
        String upit = "DELETE FROM " + param.nazivTabele() + " WHERE " + param.primarniKljuc();
        System.out.println(upit);

        Statement st = DBConnectionFactory.getInstance().getConnection().createStatement();
        st.executeUpdate(upit);
        st.close();
    }

    @Override
    public List<ApstraktniDomenskiObjekat> getAll() {
        return null;
    }
}
