import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

public class SupplierViewer implements ActionListener {


    private JFrame frame;
    private String textAction;
    private TitledBorder borderScrollPane;
    private TitledBorder borderPanel;
    private JScrollPane scrollpane;

    private JButton addNewSupplier;
    private JButton editSupplier;
    private JButton removeSupplier;
    private JButton saveSupplier;
    private String saveTextAction;


    private DefaultTableModel dataModelNewSupplier;
    private JTextField textField;
    private EditSupplier editSupplierDataBase;
    private DefaultTableModel dataModelEditSupplier;

	JMenuBar jMenuBar = new JMenuBar();

	JMenu menuFile = new JMenu("File");

	//ImageIcon createMenuItemIcon = new ImageIcon("src/icons/new.png");
	JMenuItem createMenuItem = new JMenuItem("New");



        //createMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        //createMenuItem.addActionListener(controller);
        //createMenuItem.setActionCommand("New");

    public SupplierViewer() {


	JPanel panelButtons = new JPanel();

	Border border1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	TitledBorder border2 = BorderFactory.createTitledBorder(border1, "Interaction into Suppliers Table in DataBase");
	border2.setTitleJustification(TitledBorder.CENTER);
        Border border3 = BorderFactory.createEmptyBorder(15, 15, 15, 15);
	Border border4 = BorderFactory.createCompoundBorder(border3, border2);
	panelButtons.setBorder(border4);

	BoxLayout boxLayout = new BoxLayout(panelButtons, BoxLayout.Y_AXIS);
	panelButtons.setLayout(boxLayout);

	addNewSupplier = new JButton("Add New Supplier");
	addNewSupplier.setAlignmentX(panelButtons.CENTER_ALIGNMENT);
	addNewSupplier.addActionListener(this);
	addNewSupplier.setActionCommand("Add New Supplier");


	editSupplier = new JButton("     Edit Supplier     ");
	editSupplier.setAlignmentX(panelButtons.CENTER_ALIGNMENT);
	editSupplier.addActionListener(this);
	editSupplier.setActionCommand("Edit Supplier");


	removeSupplier = new JButton(" Remove Supplier ");
	removeSupplier.setAlignmentX(panelButtons.CENTER_ALIGNMENT);
	removeSupplier.addActionListener(this);
	removeSupplier.setActionCommand("Remove Supplier");


	JButton showSuppliers = new JButton("  Show Suppliers  ");
	showSuppliers.addActionListener(this);
	showSuppliers.setActionCommand("Show Suppliers");
	showSuppliers.setAlignmentX(panelButtons.CENTER_ALIGNMENT);


	panelButtons.add(new JLabel(" "));
	panelButtons.add(addNewSupplier);
	panelButtons.add(new JLabel(" "));
	panelButtons.add(editSupplier);
	panelButtons.add(new JLabel(" "));
	panelButtons.add(removeSupplier);
	panelButtons.add(new JLabel(" "));
	panelButtons.add(showSuppliers);
	panelButtons.add(new JLabel(" "));

	textAction = "Supplier's Viewer Default Model";

	Border border11 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	borderPanel = BorderFactory.createTitledBorder(border11, textAction);
	borderPanel.setTitleJustification(TitledBorder.CENTER);
        Border border31 = BorderFactory.createEmptyBorder(15, 15, 15, 15);
	Border border41 = BorderFactory.createCompoundBorder(border31, borderPanel);


	JPanel panelTable = new JPanel();
	panelTable.setBorder(border41);
	panelTable.setLayout(new BorderLayout());


	JLabel logo = new JLabel(LogoImage.createImageIcon("images/SupplierLogo.jpg"));
	logo.setOpaque(true);
	logo.setBackground(Color.white);
	scrollpane = new JScrollPane(logo);


	textAction = "";


	Border border111 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	borderScrollPane = BorderFactory.createTitledBorder(border111, textAction);
	borderScrollPane.setTitleJustification(TitledBorder.CENTER);
        Border border311 = BorderFactory.createEmptyBorder(15, 15, 15, 15);
	Border border411 = BorderFactory.createCompoundBorder(border311, borderScrollPane);

	scrollpane.setBorder(border411);

	panelTable.add("Center", scrollpane);


	JPanel panelSave = new JPanel();

	saveSupplier = new JButton("Save Record");
	saveSupplier.addActionListener(this);
	saveSupplier.setActionCommand("Save Record");
	saveSupplier.setEnabled(false);

	panelSave.add(saveSupplier);


	panelTable.add("South", panelSave);


	menuFile.add(createMenuItem);

	jMenuBar.add(menuFile);

	frame = new JFrame("Suppliers's Viewer");
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.getContentPane().add(panelButtons, BorderLayout.NORTH);
	frame.getContentPane().add(panelTable, BorderLayout.CENTER);
	frame.setSize(820, 750);
	frame.setJMenuBar(jMenuBar);
//	frame.setLocationRelativeTo(null);
	frame.setLocation(80, 80);
	frame.setVisible(true);
	saveTextAction = "";
    }


    public void actionPerformed(ActionEvent event) {
	String command = event.getActionCommand();

	if(command.equals("Show Suppliers")) {
		textAction = "";
		borderPanel.setTitle(textAction);
		textAction = "Suppliers's Table into DataBase";
		borderScrollPane.setTitle(textAction);

		JTable tableSuppliers = getSuppliersTable();
		JViewport port = scrollpane.getViewport();
		port.add(tableSuppliers);

		setJButtonEnabled(true);
		saveTextAction = "Show Suppliers";

	} else if(command.equals("Add New Supplier")) {
		textAction = "";
		borderPanel.setTitle(textAction);
		textAction = "Fill in cells new Suppliers's data";
		borderScrollPane.setTitle(textAction);

		JTable newSupplier = getNewRowSupplier();
		JViewport port = scrollpane.getViewport();
		port.add(newSupplier);

		setJButtonEnabled(false);
		saveTextAction = "Add New Supplier";

		saveSupplier.setText("Add New Supplier");

	} else if(command.equals("Edit Supplier")) {

		textAction = "";
		borderPanel.setTitle(textAction);
		textAction = "Enter into text field Supplier's ID for editing data";
		borderScrollPane.setTitle(textAction);


		Border border1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	        Border border3 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border border4 = BorderFactory.createCompoundBorder(border1, border3);


		JPanel panel = new JPanel();

		textField = new JTextField(25);
		textField.setHorizontalAlignment(JTextField.CENTER);

		textField.setBorder(border4);

		panel.add(textField); 

		JViewport port = scrollpane.getViewport();
		port.add(panel);

		setJButtonEnabled(false);
		saveTextAction = "Edit Supplier";
		saveSupplier.setText("Find ID for Edit");


	} else if(command.equals("Remove Supplier")) {

		textAction = "";
		borderPanel.setTitle(textAction);
		textAction = "Enter into text field Supplier's ID for delete data";
		borderScrollPane.setTitle(textAction);


		Border border1 = BorderFactory.createEtchedBorder(EtchedBorder.RAISED);
	        Border border3 = BorderFactory.createEmptyBorder(5, 5, 5, 5);
		Border border4 = BorderFactory.createCompoundBorder(border1, border3);


		JPanel panel = new JPanel();

		textField = new JTextField(25);
		textField.setHorizontalAlignment(JTextField.CENTER);

		textField.setBorder(border4);

		panel.add(textField); 


		JViewport port = scrollpane.getViewport();
		port.add(panel);

		setJButtonEnabled(false);
		saveTextAction = "Remove Supplier";
		saveSupplier.setText("Remove Supplier");
	} else if(command.equals("Save Record")) {

		saveRecord(saveTextAction);

	}


	SwingUtilities.updateComponentTreeUI(frame);
    }

    private void setJButtonEnabled(boolean flag) {
	if(flag) {
		addNewSupplier.setEnabled(true);
		editSupplier.setEnabled(true);
		removeSupplier.setEnabled(true);
		saveSupplier.setEnabled(false);
	} else {
		addNewSupplier.setEnabled(false);
		editSupplier.setEnabled(false);
		removeSupplier.setEnabled(false);
		saveSupplier.setEnabled(true);
	}
    }


    private JTable getSuppliersTable() {

	SuppliersTable st = new SuppliersTable();
	Vector columnIdentifiers = st.getColumnVector();
	Vector dataVector = st.getDataVector();

	DefaultTableModel dataModel = new DefaultTableModel(dataVector, columnIdentifiers);
	JTable tableView = new JTable(dataModel);
	tableView.setEnabled(false);

	int colCount = tableView.getColumnCount();
	TableColumn column = null;
	for (int i = 0; i < colCount; i++) {
		column = tableView.getColumnModel().getColumn(i);
		column.setPreferredWidth(120);
	}

	tableView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
	tableView.setRowHeight(25);
	return tableView;

    }

    private JTable getNewRowSupplier() {

	NewSupplier ns = new NewSupplier();
	Vector columnIdentifiers = ns.getColumnVector();
	Vector dataVector = ns.getDataVector();

	dataModelNewSupplier = new DefaultTableModel(dataVector, columnIdentifiers);
	JTable tableView = new JTable(dataModelNewSupplier);

	int colCount = tableView.getColumnCount();
	TableColumn column = null;
	for (int i = 0; i < colCount; i++) {
		column = tableView.getColumnModel().getColumn(i);
		column.setPreferredWidth(120);
	}

	tableView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
	tableView.setRowHeight(25);
	return tableView;
    }



    private void saveRecord(String action) {


	if(action.equals("Show Suppliers")) {
		System.out.println(action);
	} else if(action.equals("Add New Supplier")) {


		if(dataModelNewSupplier != null) {

		Vector data = (Vector)dataModelNewSupplier.getDataVector().elementAt(0);


		for(int i = 0; i < data.size(); i++) {
			String value = (String)data.elementAt(i);
			if(value.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "Fill in all cells!!!");
				return;
			}
		}


		boolean saved = false;

		InsertNewSupplier ins = new InsertNewSupplier(data);
		saved = ins.isSaved();

		if(saved) {

			JLabel label = new JLabel("Supplier was saved!");
			label.setHorizontalAlignment(JLabel.CENTER);

			JViewport port = scrollpane.getViewport();
			port.add(label);

			textAction = "";
			borderPanel.setTitle(textAction);
			textAction = "";
			borderScrollPane.setTitle(textAction);
			saveSupplier.setEnabled(false);
			saveSupplier.setText("Save Record");
		}



		} else {
			showMessage("No data for saving!");
		}

	} else if(action.equals("Edit Supplier")) {

		String value = textField.getText();

		if(value != null & !value.equals("")) {

		boolean exists = false;

		editSupplierDataBase = new EditSupplier(value);

		exists = editSupplierDataBase.isExisted();

		if(exists) {

			textAction = "";
			borderPanel.setTitle(textAction);
			textAction = "";
			borderScrollPane.setTitle(textAction);

			JTable tableEditSupplier = getEditSupplierTable(value);
			JViewport port = scrollpane.getViewport();
			port.add(tableEditSupplier);

			saveSupplier.setText("Save Record");
			saveTextAction = "Edited Supplier Save";

		} //end if(exists)

		}else {
			showMessage("No ID data for iditing!");
		} //if(value != null & !value.equals(""))


	} else if(action.equals("Remove Supplier")) {

		String value = textField.getText();

		if(value != null & !value.equals("")) {

		boolean saved = false;

		DeleteRowFromSupplier drfs = new DeleteRowFromSupplier(value);
		saved = drfs.isSaved();

		if(saved) {

			JLabel label = new JLabel("Supplier was deleted!");
			label.setHorizontalAlignment(JLabel.CENTER);

			JViewport port = scrollpane.getViewport();
			port.add(label);

			textAction = "";
			borderPanel.setTitle(textAction);
			textAction = "";
			borderScrollPane.setTitle(textAction);
			saveSupplier.setEnabled(false);
			saveSupplier.setText("Save Record");
		}
		} else {
			showMessage("No ID data for deleting!");
		}

	} else if(action.equals("Edited Supplier Save")) {

		if(dataModelEditSupplier != null) {

		Vector data = (Vector)dataModelEditSupplier.getDataVector().elementAt(0);


		for(int i = 0; i < data.size(); i++) {
			String value = (String)data.elementAt(i);
			if(value.equals("")) {
				JOptionPane.showMessageDialog(new JFrame(), "Fill in all cells!!!");
				return;
			}

		}

		data = (Vector)dataModelEditSupplier.getDataVector().elementAt(0);
		EditDataSupplier eds = new EditDataSupplier(data);
		boolean updated = eds.isUpdated();

		if(updated) {

			JLabel label = new JLabel("Supplier was updated!");
			label.setHorizontalAlignment(JLabel.CENTER);

			JViewport port = scrollpane.getViewport();
			port.add(label);

			textAction = "";
			borderPanel.setTitle(textAction);
			textAction = "";
			borderScrollPane.setTitle(textAction);
			saveSupplier.setEnabled(false);
		}


		} //if(dataModelEditSupplier != null) {



	}
    }


    private void showMessage(String message) {
	JOptionPane.showMessageDialog(new JFrame(), message);
    }


    private JTable getEditSupplierTable(String value) {

	Vector columnIdentifiers = editSupplierDataBase.getColumnVector();
	Vector dataVector = editSupplierDataBase.getDataVector();

	dataModelEditSupplier = new SupplierDefaultTableModel(dataVector, columnIdentifiers);
	JTable tableView = new JTable(dataModelEditSupplier);

	int colCount = tableView.getColumnCount();
	TableColumn column = null;
	for (int i = 0; i < colCount; i++) {
		column = tableView.getColumnModel().getColumn(i);
		column.setPreferredWidth(120);
	}

	tableView.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); 
	tableView.setRowHeight(25);

	return tableView;
    }



    public static void main(String args[]) {
	SupplierViewer ob = new SupplierViewer();
    }

}