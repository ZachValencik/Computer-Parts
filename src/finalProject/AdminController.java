package finalProject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class AdminController implements Initializable {

	@FXML
	Text dneTxt;
	@FXML
	Text statTxt;
	@FXML
	Text invalidNameTxt;
	@FXML
	Text infoTxt;

	@FXML
	TextField sField;
	@FXML
	ComboBox infoCB;
	@FXML
	ComboBox invCB;
	private double rev = 0;

	private ObservableList<String> statList = FXCollections
			.observableArrayList("Total Users", "Total Revenue");
	private ObservableList<String> invList = FXCollections.observableArrayList(
			"HDD", "SSD", "CPU", "FANS", "POWER");

	public void navigationPage(ActionEvent event) throws IOException {
		Parent newview = FXMLLoader.load(getClass().getResource(
				"CompanyNavigation.fxml"));
		Scene tableViewScene = new Scene(newview);

		Stage window = (Stage) (((Node) event.getSource()).getScene()
				.getWindow());

		window.setScene(tableViewScene);
		window.setTitle("Login Center");
		window.show();
	}

	public void logout(ActionEvent event) throws IOException {
		Parent newview = FXMLLoader.load(getClass().getResource(
				"CompanyHome.fxml"));
		Scene tableViewScene = new Scene(newview);

		Stage window = (Stage) (((Node) event.getSource()).getScene()
				.getWindow());

		window.setScene(tableViewScene);
		window.setTitle("Login Center");
		window.show();
	}

	public void editUsr(ActionEvent event) throws IOException, SQLException {
		boolean foundName = false;
		String DBPath = "45.55.136.114/computerParts";
		String fName = "pw.txt";
		String id = "csc3610";
		DBAbstract DB = new DBAbstract(DBPath, fName, id);
		Connection con = DB.getConnection();
		String SQL = "SELECT * FROM `USER_INFO` limit 10000";
		Statement stmt = con.createStatement();
		ResultSet result = stmt.executeQuery(SQL);
		String n = sField.getText();
		UserObj obj = null;
		while (result.next()) {
			if (result.getString(6).toLowerCase().equals(n.toLowerCase())) {
				obj = new UserObj(result.getInt(1), result.getString(2),
						result.getString(3), result.getString(4),
						result.getString(5), result.getString(6),
						result.getString(7));
				foundName = true;
				break;
			}

		}

		if (foundName) {
			FXMLLoader loader = new FXMLLoader(getClass().getResource(
					"CompanyEditAccount.fxml"));
			Parent root = (Parent) loader.load();
			EditController edit = loader.getController();

			edit.myFunction(n,obj);

			Stage stage = new Stage();

			stage.setScene(new Scene(root));
			stage.show();

		} else
			invalidNameTxt.setText("User Doesnt Exist");

	}

	public void editInv(ActionEvent event) throws IOException {
		System.out.println("Editing the inventory...");
		String n = invCB.getValue().toString();
		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"CompanyEditInventory.fxml"));
		Parent root = (Parent) loader.load();

		AdminEditInvController edit = loader.getController();

		edit.recieve(n);

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.show();

	}

	public void info(ActionEvent event) throws SQLException {
		String DBPath = "45.55.136.114/computerParts";
		String fName = "pw.txt";
		String id = "csc3610";
		DBAbstract DB = new DBAbstract(DBPath, fName, id);
		Connection con = DB.getConnection();

		if (infoCB.getValue().toString().equals("Total Revenue")) {

			String SQL = "Select price from SALES";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(SQL);
			rev = 0;
			while (result.next()) {
				rev += result.getFloat(1);
			}
			infoTxt.setText("Total Revenue = $" + rev);
		} else if (infoCB.getValue().toString().equals("Total Users")) {

			String SQL = "Select U_FNAME from USER_INFO";
			Statement stmt = con.createStatement();
			ResultSet result = stmt.executeQuery(SQL);
			int i = 0;
			while (result.next()) {
				i++;
			}
			infoTxt.setText("Number of users is " + i);
		}

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		infoCB.setItems(statList);
		invCB.setItems(invList);
		infoCB.getSelectionModel().selectFirst();
		invCB.getSelectionModel().selectFirst(); // makes sure no null entry.

	}
}
