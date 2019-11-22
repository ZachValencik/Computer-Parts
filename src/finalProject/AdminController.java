package finalProject;

import java.io.IOException;
import java.net.URL;
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
	TextField sField;
	@FXML
	ComboBox infoCB;
	@FXML
	ComboBox invCB;

	private ObservableList<String> statList = FXCollections.observableArrayList("# Users", "# items in Inventroy",
			"total Revenue");
	private ObservableList<String> invList = FXCollections.observableArrayList("HDD", "SSD", "CPU", "FANS", "POWER");

	
	public void navigationPage(ActionEvent event) throws IOException {
		Parent newview = FXMLLoader.load(getClass().getResource("CompanyNavigation.fxml"));
		Scene tableViewScene = new Scene(newview);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

		window.setScene(tableViewScene);
		window.setTitle("Login Center");
		window.show();
	}

	public void logout(ActionEvent event) throws IOException {
		Parent newview = FXMLLoader.load(getClass().getResource("CompanyHome.fxml"));
		Scene tableViewScene = new Scene(newview);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

		window.setScene(tableViewScene);
		window.setTitle("Login Center");
		window.show();
	}
	
	public void editUsr(ActionEvent event) throws IOException {
		String n = sField.getText();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyEditAccount.fxml"));
		Parent root = (Parent) loader.load();
		EditController edit = loader.getController();
		
		edit.myFunction(n);

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.show();
		
		
		/*
		Parent newview = FXMLLoader.load(getClass().getResource("CompanyEditAccount.fxml"));
		Scene tableViewScene = new Scene(newview);

		Stage window = (Stage) (((Node) event.getSource()).getScene().getWindow());

		window.setScene(tableViewScene);
		window.setTitle("Edit User");
		window.show();*/
	}
	

	
	

	public void editInv(ActionEvent event) throws IOException {
		System.out.println("Editing the inventory...");
		String n = invCB.getValue().toString();
		FXMLLoader loader = new FXMLLoader(getClass().getResource("CompanyEditInventory.fxml"));
		Parent root = (Parent) loader.load();
		AdminEditInvController edit = loader.getController();
		
		edit.recieve(n);

		Stage stage = new Stage();

		stage.setScene(new Scene(root));
		stage.show();
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

		infoCB.setItems(statList);
		invCB.setItems(invList);

	}
}
