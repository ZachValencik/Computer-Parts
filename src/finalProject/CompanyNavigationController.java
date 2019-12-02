package finalProject;

import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.TreeSet;

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
import javafx.scene.control.RadioButton;
import javafx.stage.Stage;

public class CompanyNavigationController implements Initializable {
	private String user;
	@FXML
	ComboBox browseCB;

	@FXML
	Button adminButton;

	// @FXML
	// RadioButton adminCB;

	private ObservableList<String> list = FXCollections.observableArrayList(
			"HDD", "SSD", "CPU", "Fans", "Power");

	public void logout(ActionEvent event) throws IOException {
		Parent newview = FXMLLoader.load(getClass().getResource(
				"CompanyHome.fxml"));
		Scene tableViewScene = new Scene(newview);

		Stage window = (Stage) (((Node) event.getSource()).getScene()
				.getWindow());

		window.setScene(tableViewScene);
		window.setTitle("CompanyName");
		window.show();
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

	public void goToBuy(ActionEvent event) throws IOException, SQLException {
		// how to send info to other scene
		TreeSet<String> l = new TreeSet<String>();

		String DBPath = "45.55.136.114/computerParts";
		String fName = "pw.txt";
		String id = "csc3610";
		DBAbstract DB = new DBAbstract(DBPath, fName, id);
		Connection con = DB.getConnection();
		String sql = null;
		if (browseCB.getValue().toString().equals("HDD"))
			sql = "SELECT * FROM `HDD` limit 10000";
		else if (browseCB.getValue().toString().equals("SSD"))
			sql = "SELECT SSD_NUM, SSD_AMOUNT, SSD_NAME, SSD_PRICE, SSD_COMPANY FROM `SSD` limit 10000";
		else if (browseCB.getValue().toString().equals("CPU"))
			sql = "SELECT CPU_NUM, CPU_AMOUNT, CPU_NAME, CPU_PRICE, CPU_COMPANY FROM `CPU` limit 10000";
		else if (browseCB.getValue().toString().equals("Fans"))
			sql = "SELECT FAN_NUM, FAN_AMOUNT, FAN_NAME, FAN_PRICE, FAN_COMPANY FROM `FANS` limit 10000";
		else if (browseCB.getValue().toString().equals("Power"))
			sql = "SELECT POW_NUM, POW_AMOUNT, POW_NAME, POW_PRICE, POW_COMPANY FROM `POWER` limit 10000";

		Connection connection = DB.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet result = stmt.executeQuery(sql);
		ArrayList<ProductsObj> obj = new ArrayList<ProductsObj>();
		ObservableList<ProductsObj> oList = FXCollections.observableArrayList();
		while (result.next()) {
			l.add("Item # " + result.getInt(1) + " In Stock: "
					+ result.getInt(2) + "  Name: " + result.getString(3)
					+ "   $" + result.getFloat(4) + "  Company: "
					+ result.getString(5));
			String nNum = Integer.toString(result.getInt(1));
			String nStock = Integer.toString(result.getInt(2));
			String item = result.getString(3);
			String price = Float.toString(result.getFloat(4));
			String company = result.getString(5);
			oList.add(new ProductsObj(nNum, nStock, item, price, company));
			obj.add(new ProductsObj(nNum, nStock, item, price, company));

		}

		FXMLLoader loader = new FXMLLoader(getClass().getResource(
				"CompanyBuy.fxml"));
		Parent root = (Parent) loader.load();
		buyController buy = loader.getController();

		buy.myFunction(oList,user); // sends obVList of objects over to the other

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.show();

	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		browseCB.setItems(list);
		browseCB.getSelectionModel().selectFirst();
	}

	public void getUser(String u) {
		user = u;

		if (!user.equals("Admin")) {
			adminButton.setVisible(false);
		}
	}

}
