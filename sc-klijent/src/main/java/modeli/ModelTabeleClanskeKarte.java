package modeli;

import domen.ClanskaKarta;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleClanskeKarte extends AbstractTableModel {

    List<ClanskaKarta> lista;
    String[] kolone = {"ID", "Datum", "Ukupan iznos", "Instruktor", "Polaznik"};

    public ModelTabeleClanskeKarte(List<ClanskaKarta> lista) {
        this.lista = lista;
    }

    @Override
    public int getRowCount() {
        return lista.size();
    }

    @Override
    public int getColumnCount() {
        return kolone.length;
    }

    @Override
    public String getColumnName(int column) {
        return kolone[column];
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        ClanskaKarta ck = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return ck.getIdClanskaKarta();
            case 1:
                SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy");
                String formatiranDatum = sdf.format(ck.getDatumUclanjenja());
                return formatiranDatum;
            case 2:
                return ck.getUkupanIznos();
            case 3:
                return ck.getInstruktor();
            case 4:
                return ck.getPolaznik();
            default:
                return "N/A";
        }
    }

    public List<ClanskaKarta> getLista() {
        return lista;
    }
}
