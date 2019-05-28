import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Vector;



public class EditSupplier {

    private ResultSetMetaData metaData;
    private ResultSet resultSet;
    private Vector<String> columnIdentifiers;
    private Vector<Vector> dataVector;
    private boolean exists = false;

    public EditSupplier(String editingID) {

	Statement statement = null;

	try {
	Class.forName("com.mysql.jdbc.Driver");
	Connection connection = DriverManager.getConnection("jdbc:mysql://10.9.6.95:3306/devcit?autoRecconect=true&useSSL=false","root","root");
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

	String sqlQuery = "SELECT * FROM SUPPLIERS";

	resultSet = statement.executeQuery(sqlQuery);

	metaData = resultSet.getMetaData();
	int numberOfColumns = metaData.getColumnCount();

	columnIdentifiers = new Vector<String>();

	for(int column = 0; column < numberOfColumns; column++) {
		String value = metaData.getColumnLabel(column + 1);
		columnIdentifiers.addElement(value);
	}

	dataVector = new Vector<Vector>();

	int id = Integer.parseInt(editingID);

	while(resultSet.next()) {


		int supplier_id = resultSet.getInt("ID_SUPPLIER");

		if(supplier_id == id) {
			Vector<String> rowTable = new Vector<String>();
			rowTable.addElement("" + supplier_id);

			for (int i = 2; i <= numberOfColumns; i++) {
				String value = resultSet.getString(i);
				rowTable.addElement(value);
			}
			exists = true;
			dataVector.addElement(rowTable);
		break;
		}
	}



	if(!exists) {
		showErrorMessage("ID doesn't exists!!!");
	}

	} catch(SQLException sqlExc) {
		System.out.println(sqlExc);
		showErrorMessage(sqlExc.toString());
	} catch(ClassNotFoundException cnfExc) {
		System.out.println(cnfExc);
		showErrorMessage(cnfExc.toString());
	} finally {
		try {
			if (statement != null) {
				statement.close();
			}
		} catch(SQLException sqlExc) {
			System.out.println(sqlExc);
			showErrorMessage(sqlExc.toString());
		}
	}
    }

    private void showErrorMessage(String error) {
	exists = false;
	JOptionPane.showMessageDialog(new JFrame(), error);
    }

    public Vector getColumnVector() {
	return columnIdentifiers;
    }

    public Vector getDataVector() {
	return dataVector;
    }

    public boolean isExisted() {
	return exists;
    }



}