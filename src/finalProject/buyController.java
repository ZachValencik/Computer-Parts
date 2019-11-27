package finalProject;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class buyController implements Initializable {
	@FXML
	TableColumn<ProductsObj,String> itemNum;
	@FXML 
	TableColumn<ProductsObj,String> inStock;
	@FXML 
	TableColumn<ProductsObj,String> item;
	@FXML 
	TableColumn<ProductsObj,String> price;
	@FXML 
	TableColumn<ProductsObj,String> company;
	@FXML
	private TableView<ProductsObj> tableView = new TableView<ProductsObj>();
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		itemNum.setCellValueFactory(new PropertyValueFactory<ProductsObj,String>("itemNum"));
		inStock.setCellValueFactory(new PropertyValueFactory<ProductsObj,String>("stock"));
		item.setCellValueFactory(new PropertyValueFactory<ProductsObj,String>("item"));
		price.setCellValueFactory(new PropertyValueFactory<ProductsObj,String>("price"));
		company.setCellValueFactory(new PropertyValueFactory<ProductsObj,String>("company"));
		
		
	}
	
	public void myFunction(ObservableList<ProductsObj> oList) {
		//tableView.setEditable(true);
		//label.setText(value);
		tableView.setItems(oList);
		
		
		
		
		//tableView.setItems(oList);
		/*
		itemNum = new TableColumn("Item #");
        itemNum.setMinWidth(100);
        itemNum.setCellValueFactory(
                new PropertyValueFactory<ProductsObj, String>("itemNum"));
 
        inStock = new TableColumn("Stock");
        inStock.setMinWidth(100);
        inStock.setCellValueFactory(
                new PropertyValueFactory<ProductsObj, String>("stock"));
        
        item = new TableColumn("Item");
        item.setMinWidth(200);
        item.setCellValueFactory(
                new PropertyValueFactory<ProductsObj, String>("item"));
 
        price = new TableColumn("Price");
        price.setMinWidth(200);
        price.setCellValueFactory(
                new PropertyValueFactory<ProductsObj, String>("price"));
        
        company = new TableColumn("Company");
        company.setMinWidth(200);
        company.setCellValueFactory(
                new PropertyValueFactory<ProductsObj, String>("company"));
        
 
        tableView.setItems(oList);
        tableView.getColumns().addAll(itemNum, inStock, price,company);
		*/
		
		//tableView.getColumns(itemNum,inStock,item,price,company);
		
		
		
	}
	
}
