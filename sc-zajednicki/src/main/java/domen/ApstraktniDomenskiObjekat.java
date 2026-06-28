package domen;

import java.io.Serializable;
import java.sql.ResultSet;
import java.util.List;

/**
 * Apstraktna klasa za sve domenske klase koje se perzistiraju u bazi podataka.
 */
public interface ApstraktniDomenskiObjekat extends Serializable {

    /**
     * Vraća naziv tabele u bazi podataka.
     *
     * @return naziv tabele
     */
    String nazivTabele();

    /**
     * Vraća primarni ključ objekta.
     *
     * @return vraća primarni ključ
     */
    String primarniKljuc();

    /**
     * Vraća nazive kolona koje učestvuju u INSERT upitu.
     *
     * @return lista kolona odvojenih zarezom
     */
    String koloneZaUbacivanje();

    /**
     * Vraća vrednosti kolona za INSERT upit.
     *
     * @return vrednosti kolona u formatu pogodnom za SQL
     */
    String vrednostiZaUbacivanje();

    /**
     * Vraća deo SET klauzule za UPDATE upit.
     *
     * @return vrednosti kolona za izmenu
     */
    String vrednostiZaIzmenu();

    /**
     * Vraća alias tabele u SQL upitu.
     *
     * @return alias tabele
     */
    String alijasTabele();

    /**
     * Vraća JOIN klauzulu potrebnu za učitavanje objekta iz baze.
     *
     * @return SQL JOIN ili prazan string ako nije potreban
     */
    String join();

    /**
     * Vraća WHERE uslov za selekciju objekta.
     *
     * @return SQL WHERE uslov
     */
    String uslovWhere();

    /**
     * Formira listu domenskih objekata na osnovu rezultata upita.
     *
     * @param rs kursor sa rezultatima upita
     * @return lista domenskih objekata
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    List<ApstraktniDomenskiObjekat> vratiListu(ResultSet rs) throws Exception;

    /**
     * Formira jedan domenski objekat na osnovu trenutnog reda u rezultatu upita.
     *
     * @param rs kursor sa rezultatima upita
     * @return domenski objekat
     * @throws Exception ako dođe do greške pri čitanju rezultata
     */
    ApstraktniDomenskiObjekat vratiObjekatIzRs(ResultSet rs) throws Exception;
}
