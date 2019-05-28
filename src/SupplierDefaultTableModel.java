import javax.swing.table.DefaultTableModel;
import java.util.Vector;

public class SupplierDefaultTableModel extends DefaultTableModel{

    private static final long serialVersionUID = 111L;

    public SupplierDefaultTableModel(Vector data, Vector columnNames) {
	super(data, columnNames);
    }

    public boolean isCellEditable(int row, int col) {
        if (col < 1) {
            return false;
        } else {
            return true;
        }
    }
}