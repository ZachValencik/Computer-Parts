package finalProject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AdminEditInvController {

	// an edit inventory that will make use of tree sets, adding/deleting from
	// database
	private TreeSet items;
	private String part;
	@FXML
	Text editingTxt;
	@FXML
	TextField amountField;
	@FXML
	TextField deviceField;
	@FXML
	TextField priceField;
	@FXML
	TextField companyField;
	@FXML
	TextField addAmtField;
	@FXML
	TextField sIdField;
	@FXML 
	Text sTxt;
	public void recieve(String item) {
		part = item;
		editingTxt.setText(item);
	}

	public void addInv(ActionEvent event) throws SQLException {
		String DBPath = "45.55.136.114/computerParts";
		String fName = "pw.txt";
		String id = "csc3610";
		DBAbstract DB = new DBAbstract(DBPath, fName, id);
		// Connection con = DB.getConnection();
		String fields = "";

		Connection connection = DB.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet r = null;

		if (part.equals("CPU")) {
			fields = "(CPU_NUM,CPU_AMOUNT, CPU_NAME,CPU_PRICE,CPU_COMPANY)";
			r = stmt.executeQuery("SELECT * FROM `CPU` limit 10000");
		} else if (part.equals("SSD")) {
			fields = "(SSD_NUM, SSD_AMOUNT, SSD_NAME, SSD_PRICE, SSD_COMPANY)";
			r = stmt.executeQuery("SELECT * FROM `SSD` limit 10000");
		} else if (part.equals("FANS")) {
			fields = "(FAN_NUM, FAN_AMOUNT, FAN_NAME, FAN_PRICE, FAN_COMPANY)";
			r = stmt.executeQuery("SELECT * FROM `FANS` limit 10000");
		} else if (part.equals("POWER")) {
			fields = "(POW_NUM, POW_AMOUNT, POW_NAME, POW_PRICE, POW_COMPANY)";
			r = stmt.executeQuery("SELECT * FROM `POWER` limit 10000");
		
		}
	
		String query = " insert into " + part + fields + " values (?,?,?,?,?)";

		int size = 0;

		while (r.next()) {
			size++;
		}

		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt(1, size + 1); //
		preparedStmt.setInt(2, Integer.parseInt(amountField.getText())); // first
																			// name
		preparedStmt.setString(3, deviceField.getText());
		preparedStmt.setFloat(4, Float.parseFloat(priceField.getText()));
		preparedStmt.setString(5, companyField.getText()); // gpa

		preparedStmt.execute();
	}
public void searchId(ActionEvent event){
	
}
public void addAmt(ActionEvent event){
	
}

}