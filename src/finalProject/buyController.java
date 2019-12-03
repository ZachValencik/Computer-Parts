package finalProject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class buyController implements Initializable {
	private String user;
	@FXML
	private TableView<ProductsObj> tableView = new TableView<ProductsObj>();
	@FXML
	TableColumn<ProductsObj, String> itemNum;
	@FXML
	TableColumn<ProductsObj, String> inStock;
	@FXML
	TableColumn<ProductsObj, String> item;
	@FXML
	TableColumn<ProductsObj, String> price;
	@FXML
	TableColumn<ProductsObj, String> company;
	
	@FXML
	TableView<ProductsObj> cartTable;
	@FXML
	TableColumn<ProductsObj, String> cartItem;
	@FXML
	TableColumn<ProductsObj, String> cartPrice;
	@FXML
	TableColumn<ProductsObj, String> cartCompany;
	
	
	private LinkedList<ProductsObj> list = new LinkedList<ProductsObj>();
	private boolean notEmpty = false;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		itemNum.setCellValueFactory(new PropertyValueFactory<ProductsObj, String>(
				"itemNum"));
		inStock.setCellValueFactory(new PropertyValueFactory<ProductsObj, String>(
				"stock"));
		item.setCellValueFactory(new PropertyValueFactory<ProductsObj, String>(
				"item"));
		price.setCellValueFactory(new PropertyValueFactory<ProductsObj, String>(
				"price"));
		company.setCellValueFactory(new PropertyValueFactory<ProductsObj, String>(
				"company"));
		
		
		cartItem.setCellValueFactory(new PropertyValueFactory<ProductsObj, String>(
				"item"));
		cartPrice.setCellValueFactory(new PropertyValueFactory<ProductsObj, String>(
				"price"));
		cartCompany.setCellValueFactory(new PropertyValueFactory<ProductsObj, String>(
				"company"));
		

	}

	public void addItem(ActionEvent event) {
		notEmpty = true;
		list.add(tableView.getSelectionModel().getSelectedItem());
		ObservableList<ProductsObj> oL = FXCollections.observableArrayList(list);
		
		for (ProductsObj l : list) {
			System.out.println(l.getItem());
		}
		cartTable.setItems(oL);
	}

	public void resetList(ActionEvent event) {
		list.clear();
		notEmpty = false;
		cartTable.getItems().clear();
	}

	public void buy(ActionEvent event) throws SQLException {
		if (notEmpty) {
			for (ProductsObj l : list) {
				String DBPath = "45.55.136.114/computerParts";
				String fName = "pw.txt";
				String id = "csc3610";
				DBAbstract DB = new DBAbstract(DBPath, fName, id);
				Connection con = DB.getConnection();

				String query = " insert into SALES (part,price,company,buyer)"
						+ " values (?, ?, ?,?)";

				PreparedStatement preparedStmt = con.prepareStatement(query);
				preparedStmt.setString(1, l.getItem()); // random stu id
				preparedStmt.setFloat(2, Float.parseFloat(l.getPrice())); // first
																			// name
				preparedStmt.setString(3, l.getCompany());
				preparedStmt.setString(4, user);

				preparedStmt.execute();
			}
		}
		cartTable.getItems().clear();
	}

	public void myFunction(ObservableList<ProductsObj> oList, String user) {
		this.user = user;
		
		tableView.setItems(oList);

	}

}
