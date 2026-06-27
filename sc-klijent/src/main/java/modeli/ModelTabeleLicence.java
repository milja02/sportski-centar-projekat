package modeli;

import domen.Licenca;
import java.util.List;
import javax.swing.table.AbstractTableModel;

public class ModelTabeleLicence extends AbstractTableModel {

    private final List<Licenca> lista;
    private final String[] kolone = {"ID", "Tip licence", "Nivo kvalifikacije"};

    public ModelTabeleLicence(List<Licenca> lista) {
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
        Licenca l = lista.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return l.getIdLicenca();
            case 1:
                return l.getTipLicence();
            case 2:
                return l.getNivoKvalifikacije();
            default:
                return "";
        }
    }

    public List<Licenca> getLista() {
        return lista;
    }
}
