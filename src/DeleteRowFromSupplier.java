import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Vector;



public class DeleteRowFromSupplier {


    private boolean saved = false;

    public DeleteRowFromSupplier(String deleteID) {

	Statement statement = null;

	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.9.6.95:3306/devcit?autoRecconect=true&useSSL=false","root","root");
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	String sqlQuery = "SELECT * FROM SUPPLIERS";
	ResultSet resultSet = statement.executeQuery(sqlQuery);

	int id = Integer.parseInt(deleteID);

	resultSet.beforeFirst();

	while(resultSet.next()) {

		int supplier_id = resultSet.getInt("ID_SUPPLIER");

		if(supplier_id == id) {
			resultSet.deleteRow();
			saved = true;
			break;
		}
	}


	if(!saved) {
		showErrorMessage("ID doesn't exists!!!");
	}

	resultSet.beforeFirst();


	} catch(SQLException sqlExc) {
		System.out.println(sqlExc);
		showErrorMessage(sqlExc.toString());
	} catch(ClassNotFoundException cnfExc) {
		System.out.println(cnfExc);
		showErrorMessage(cnfExc.toString());
	} catch(Exception e) {
		showErrorMessage("Incorrect ID for deleting!");
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
	saved = false;
	JOptionPane.showMessageDialog(new JFrame(), error);
    }

    public boolean isSaved() {
	return saved;
    }
}