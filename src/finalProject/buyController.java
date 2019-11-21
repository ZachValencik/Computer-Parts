package finalProject;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

public class buyController implements Initializable {

	@FXML
	Label label;

	@FXML
	ComboBox items;
	
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
	}
	
	public void myFunction(ObservableList<String> list) {
		//label.setText(value);
		
		items.setItems(list);
	}
	
}
