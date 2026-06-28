package modeli;

import domen.StavkaClanskeKarte;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleStavkaClanskeKarte extends AbstractTableModel {

    List<StavkaClanskeKarte> lista;
    String[] kolone = {"RB", "Broj termina", "Iznos stavke", "Sport"};

    public ModelTabeleStavkaClanskeKarte(List<StavkaClanskeKarte> lista) {
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
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        StavkaClanskeKarte sck = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return sck.getRb();
            case 1:
                return sck.getBrojTermina();
            case 2:
                return sck.getIznosStavke();
            case 3:
                return sck.getSport();
            default:
                return "N/A";
        }
    }

    public List<StavkaClanskeKarte> getLista() {
        return lista;
    }

    public void osvezi() {
        fireTableDataChanged();
    }

    public void dodeliRb() {
        for (int i = 0; i < lista.size(); i++) {
            lista.get(i).setRb(i + 1);
        }
        fireTableDataChanged();
    }
}
