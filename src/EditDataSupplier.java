import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Vector;



public class EditDataSupplier {

    private ResultSetMetaData metaData;
    private ResultSet resultSet;
    private boolean updated = false;


    public EditDataSupplier(Vector rowUpdated) {

	Statement statement = null;

	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.9.6.95:3306/devcit?autoRecconect=true&useSSL=false","root","root");
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);

	String sqlQuery = "SELECT * FROM SUPPLIERS";

	resultSet = statement.executeQuery(sqlQuery);

	metaData = resultSet.getMetaData();
	int numberOfColumns = metaData.getColumnCount();


	while(resultSet.next()) {

		int supplier_id = resultSet.getInt("ID_SUPPLIER");
		int id = Integer.parseInt("" + rowUpdated.elementAt(0));

		if(supplier_id == id) {

			for (int i = 2; i <= numberOfColumns; i++) {
				String value = (String)rowUpdated.elementAt(i - 1);
				resultSet.updateString(i, value);
			}

			resultSet.updateRow();
			updated = true;
		break;
		}
	}



	if(!updated) {
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
	updated = false;
	JOptionPane.showMessageDialog(new JFrame(), error);
    }

    public boolean isUpdated() {
	return updated;
    }



}