import java.sql.Connection;
import java.sql.Statement;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import java.util.Vector;



public class InsertNewSupplier {


    private boolean saved = false;

    public InsertNewSupplier(Vector data) {

	Statement statement = null;

	try {
		Class.forName("com.mysql.jdbc.Driver");
		Connection connection = DriverManager.getConnection("jdbc:mysql://10.9.6.95:3306/devcit?autoRecconect=true&useSSL=false","root","root");
	statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
	String sqlQuery = "SELECT * FROM SUPPLIERS";
	ResultSet resultSet = statement.executeQuery(sqlQuery);

	resultSet.moveToInsertRow();


	for(int i = 0; i < data.size(); i++) {
		if(i == 0) {
			int id = Integer.parseInt((String)data.elementAt(i));
			resultSet.updateInt((i + 1), id);
			continue;
		}
		resultSet.updateString((i + 1), (String)data.elementAt(i));
	}

	resultSet.insertRow();

	saved = true;

	} catch(SQLException sqlExc) {
		System.out.println(sqlExc);
		showErrorMessage(sqlExc.toString());
	} catch(ClassNotFoundException cnfExc) {
		System.out.println(cnfExc);
		showErrorMessage(cnfExc.toString());
	} catch(Exception e) {
		showErrorMessage("Incorrect data for saving!");
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