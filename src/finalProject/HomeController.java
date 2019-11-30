package finalProject;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;



import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class HomeController {
	/*
	 * Home for the Company server where an user/admin login into an account or
	 * choose to create one. user and admin will navigate to different scenes.
	 */
	private String user; 
	@FXML
	TextField userField;
	@FXML
	PasswordField passField;

	@FXML
	Button createBtn;
	@FXML
	Button loginBtn;
	@FXML
	Text wrongInfoTxt;
	private boolean login = false;
	public void goToCreateAccount(ActionEvent event) throws IOException {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyNewAccount.fxml"));
		Parent root = (Parent) loader.load();

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.setTitle("Create a new Account");
		stage.show();

	}

	public void goToLogin(ActionEvent event) throws IOException, SQLException {
		login = false;
		String DBPath = "45.55.136.114/computerParts";//localhost:3306
		String fName = "pw.txt";
		String id = "csc3610";
		DBAbstract DB = new DBAbstract(DBPath, fName, id);
		Connection con = DB.getConnection();
		String sql = "SELECT U_FNAME, U_LNAME, U_PHONE, U_EMAIL, U_USERNAME, U_PASSWORD FROM `USER_INFO` limit 10000";
		Connection connection = DB.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		while (result.next()) {
			//checks if username and pass word match
			if (result.getString(5).toLowerCase().equals(userField.getText().toLowerCase())
					&& result.getString(6).equals(passField.getText())) {
				user = result.getString(5);
				login = true;
				break;

			}
		}

		if (login) {
			/*Parent newview = FXMLLoader.load(getClass().getResource("CompanyNavigation.fxml"));
			Scene tableViewScene = new Scene(newview);
			Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());
			window.setScene(tableViewScene);
			window.setTitle("Navigation Page");

			window.show();
			*/
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"CompanyNavigation.fxml"));
			Parent root = (Parent) loader.load();
			CompanyNavigationController sendUser = loader.getController();

			sendUser.getUser(user); // sends obVList of objects over to the other

			Stage stage = new Stage();

			stage.setScene(new Scene(root));
			stage.show();
			
			
			
			
			
			
			
			
			
		} else
			wrongInfoTxt.setText("Wrong User Name or Password!");
		//testing git

	}
	

}
