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
	private int idNum;
	private int amt;
	private String item;
	private float price;
	private String company;
	private boolean exist = false;
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

	public void searchId(ActionEvent event) throws SQLException {
		exist = false; // assume its false and reset it if found true
		String DBPath = "45.55.136.114/computerParts";
		String fName = "pw.txt";
		String id = "csc3610";
		DBAbstract DB = new DBAbstract(DBPath, fName, id);
		Connection connection = DB.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet r = null;

		r = stmt.executeQuery("SELECT * FROM `" + part + "` limit 10000");

		while (r.next()) {
			if (r.getInt(1) == Integer.parseInt(sIdField.getText())) {
				exist = true;
				idNum = r.getInt(1);
				amt = r.getInt(2);
				item = r.getString(3);
				price = r.getFloat(4);
				company = r.getString(5);

				sTxt.setText(r.getInt(1) + " " + r.getInt(2) + " "
						+ r.getString(3) + " " + r.getFloat(4) + " "
						+ r.getString(5));
				break;
			}
			if (!exist) {
				sTxt.setText("ID Not Found");
			}

		}

	}

	public void addAmt(ActionEvent event) throws SQLException {
		if (exist) {
			String DBPath = "45.55.136.114/computerParts";
			String fName = "pw.txt";
			String id = "csc3610";
			DBAbstract DB = new DBAbstract(DBPath, fName, id);
			Connection connection = DB.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet r = null;
			String q = null;
			System.out.println(idNum);
			if (part.equals("CPU")) {
				q = "UPDATE `CPU` SET `CPU_NUM`='" + idNum + "',`CPU_AMOUNT`='"
						+ (amt + Integer.parseInt(addAmtField.getText()))
						+ "',`CPU_NAME`='" + item + "',`CPU_PRICE`='" + price
						+ "',`CPU_COMPANY`='" + company + "' WHERE `CPU_NUM` ="
						+ idNum;
			} else if (part.equals("FANS")) {

			} else if (part.equals("HDD")) {

			} else if (part.equals("SSD")) {

			} else if (part.equals("POWER")) {

			}

			try {
				stmt.executeUpdate(q);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}