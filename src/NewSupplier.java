import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Vector;



public class NewSupplier {

    private ResultSetMetaData metaData;
    private ResultSet resultSet;
    private Vector<String> columnIdentifiers;
    private Vector<Vector> dataVector;

    public NewSupplier() {

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

	while(resultSet.next()) {
		Vector<String> newRowTable = new Vector<String>();
		for (int i = 1; i <= numberOfColumns; i++) {
			String value = "";
			newRowTable.addElement(value);
		}
		dataVector.addElement(newRowTable);
		break;
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
	int n = JOptionPane.showConfirmDialog(new JFrame(), error, "Message!", JOptionPane.YES_NO_OPTION);

	if(n == 0 || n == 1) {
		System.exit(0);
	}
    }

    public Vector getColumnVector() {
	return columnIdentifiers;
    }

    public Vector getDataVector() {
	return dataVector;
    }
}