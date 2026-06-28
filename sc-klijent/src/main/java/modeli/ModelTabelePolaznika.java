package modeli;

import domen.Polaznik;
import java.util.List;
import java.util.stream.Collectors;
import javax.swing.table.AbstractTableModel;

public class ModelTabelePolaznika extends AbstractTableModel {

    List<Polaznik> lista;
    String[] kolone = {"ID", "Ime", "Prezime", "Broj telefona", "Mesto"};

    public ModelTabelePolaznika(List<Polaznik> lista) {
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
        Polaznik p = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return p.getIdPolaznik();
            case 1:
                return p.getIme();
            case 2:
                return p.getPrezime();
            case 3:
                return p.getBrojTelefona();
            case 4:
                return p.getMesto();
            default:
                return "N/A";
        }
    }

    public List<Polaznik> getLista() {
        return lista;
    }

    public void pretrazi(String ime, String prezime, String brojTelefona) {
        List<Polaznik> filteredList = lista.stream()
                .filter(p -> (ime == null || ime.isEmpty() || p.getIme().toLowerCase().contains(ime.toLowerCase())))
                .filter(p -> (prezime == null || prezime.isEmpty() || p.getPrezime().toLowerCase().contains(prezime.toLowerCase())))
                .filter(p -> (brojTelefona == null || brojTelefona.isEmpty() || p.getBrojTelefona().toLowerCase().contains(brojTelefona.toLowerCase())))
                .collect(Collectors.toList());
        this.lista = filteredList;
        fireTableDataChanged();
    }
}
