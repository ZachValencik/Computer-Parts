package finalProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
	Text text;
	private String uN;
	public void delete(ActionEvent event) {
		System.out.println("Deleting..");
		try {
			String DBPath = "45.55.136.114/computerParts";
			String fName = "pw.txt";
			String id = "csc3610";
			DBAbstract DB = new DBAbstract(DBPath, fName, id);
			Connection con = DB.getConnection();
			String query = "delete from USER_INFO where U_USERNAME = ?";
			PreparedStatement preparedStmt = con.prepareStatement(query);
			preparedStmt.setString(1,uN);

			// execute the prepared statement
			preparedStmt.execute();

			con.close();
		} catch (Exception e) {
			System.err.println("Account Already Deleted ");
			System.err.println(e.getMessage());
		}

	}

	public void adminPage(ActionEvent event) throws IOException {
		Parent newview = FXMLLoader.load(getClass().getResource(
				"CompanyAdmin.fxml"));
		Scene tableViewScene = new Scene(newview);

		Stage window = (Stage) (((Node) event.getSource()).getScene()
				.getWindow());

		window.setScene(tableViewScene);
		window.setTitle("Control Center Alpha");
		window.show();
	}
	
	
	public void myFunction(String name) {

		uN=name;
		text.setText("Editing User: "+name);
		
	}

}
