package finalProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class EditController {

	@FXML
	TextField fNField;
	@FXML
	TextField lNField;
	@FXML
	TextField uNField;
	@FXML
	TextField eField;
	@FXML
	TextField pField;
	@FXML
	TextField phoneField;
	@FXML
	Text text;
	@FXML
	Text editTxt;
	private String uN;
	private int id;
	private boolean deleted = false;

	public void edit(ActionEvent event) throws SQLException {
		if (!deleted) {
			String DBPath = "45.55.136.114/computerParts";
			String fName = "pw.txt";
			String idd = "csc3610";
			DBAbstract DB = new DBAbstract(DBPath, fName, idd);
			Connection connection = DB.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet r = null;
			String q = null;
			q = "UPDATE `USER_INFO` SET `U_NUM`='" + id + "',`U_LNAME`='"
					+ lNField.getText() + "',`U_FNAME`='" + fNField.getText()
					+ "',`U_PHONE`='" + phoneField.getText() + "',`U_EMAIL`='"
					+ eField.getText() + "',`U_USERNAME`='" + uNField.getText()
					+ "',`U_PASSWORD`='" + pField.getText()
					+ "' WHERE `U_NUM` =" + id;

			try {
				stmt.executeUpdate(q);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		editTxt.setText("Edited");
	}

	public void delete(ActionEvent event) {
		deleted = true;
		System.out.println("Deleting..");
		try {
			String DBPath = "45.55.136.114/computerParts";
			String fName = "pw.txt";
			String id = "csc3610";
			DBAbstract DB = new DBAbstract(DBPath, fName, id);
			Connection con = DB.getConnection();
			String query = "delete from USER_INFO where U_USERNAME = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1, uN);

			// execute the prepared statement
			preparedStmt.execute();

			con.close();
		} catch (Exception e) {
			System.err.println("Account Already Deleted ");
			System.err.println(e.getMessage());
		}

	}

	/*
	 * public void adminPage(ActionEvent event) throws IOException { Parent
	 * newview = FXMLLoader.load(getClass().getResource( "CompanyAdmin.fxml"));
	 * Scene tableViewScene = new Scene(newview);
	 * 
	 * Stage window = (Stage) (((Node) event.getSource()).getScene()
	 * .getWindow());
	 * 
	 * window.setScene(tableViewScene); window.setTitle("Control Center Alpha");
	 * window.show(); }
	 */

	public void myFunction(String name, UserObj obj) {
		id = obj.getuNum();
		fNField.setText(obj.getfName());
		lNField.setText(obj.getlName());
		uNField.setText(obj.getUser());
		eField.setText(obj.getEmail());
		pField.setText(obj.getPass());
		phoneField.setText(obj.getPhone());
		uN = obj.getUser();
		System.out.println(uN);
		text.setText("Editing User: " + name);

	}

}
