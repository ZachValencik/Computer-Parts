package finalProject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class NewAccountController implements Initializable {
	private String fN;
	private String lName;
	private String phoneNum;
	private String email;
	private String uName;
	private String pWord;
	private String job;
	private boolean takenUser = false;
	@FXML
	Text displayTxt;

	@FXML
	TextField fnField;
	@FXML
	TextField lnField;
	@FXML
	TextField phoneField;
	@FXML
	TextField emailField;
	@FXML
	TextField userField;
	@FXML
	PasswordField passField;

	@FXML
	Button submitBtn;
	@FXML
	Button backBtn;

	// private ObservableList<String> list =
	// FXCollections.observableArrayList("Job1", "job2", "job3", "job4", "job5");

	// create an account, should make sure if username doesnt exist already and all
	// fields are filled correctly
	public void createAccount(ActionEvent event) throws ClassNotFoundException {

		fN = fnField.getText();
		lName = lnField.getText();
		phoneNum = phoneField.getText();
		email = emailField.getText();
		uName = userField.getText();
		pWord = passField.getText();
		ArrayList<String> last = new ArrayList<>();
		takenUser = false;
		try {
			String DBPath = "45.55.136.114/computerParts";
			String fName = "pw.txt";
			String id = "csc3610";
			DBAbstract DB = new DBAbstract(DBPath, fName, id);
			Connection con = DB.getConnection();
			String SQL = "Select U_USERNAME from USER_INFO";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(SQL);

			while (result.next()) {
				if (result.getString(1).toLowerCase().equals(uName.toLowerCase())) {
					takenUser = true;
					System.out.println("User name taken, enter new one");
				}

				
			}
			if (takenUser) {
				displayTxt.setText("User Name Taken");
			} else {
				displayTxt.setText("User Created!");
				addIntoTable(DB, fN, lName, phoneNum, email, uName, pWord);
			}

		} catch (SQLException e) {

			e.printStackTrace();
		}

	}

	public void goHome(ActionEvent event) throws IOException {
		Parent newview = FXMLLoader.load(getClass().getResource("CompanyHome.fxml"));
		Scene tableViewScene = new Scene(newview);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

		window.setScene(tableViewScene);
		window.setTitle("Computer Parts Company");
		window.show();
	}

	private static void addIntoTable(DBAbstract DB, String f, String l, String p, String e, String u, String pW)
			throws ClassNotFoundException, SQLException {

		Connection con = DB.getConnection();

		String query = " insert into USER_INFO (U_FNAME, U_LNAME, U_PHONE, U_EMAIL, U_USERNAME, U_PASSWORD)" + " values (?, ?, ?,?,?,?)";

		PreparedStatement preparedStmt = con.prepareStatement(query);
		preparedStmt.setString(1, f); // random stu id
		preparedStmt.setString(2, l); // first name
		preparedStmt.setString(3, p);
		preparedStmt.setString(4, e);
		preparedStmt.setString(5, u); // gpa
		preparedStmt.setString(6, pW);

		preparedStmt.execute();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	}

}
