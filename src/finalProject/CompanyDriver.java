package finalProject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class CompanyDriver extends Application {
	public static DBAbstract DB;
	@Override
	public void start(Stage s) {
		String DBPath = "45.55.136.114/computerParts";
		String fName = "pw.txt";
		String id = "csc3610";
		DB = new DBAbstract(DBPath, fName, id);
		//zach wuz here
		//marcus was here

		// TESTING CONFLICT 

		Parent root = null;
		try {
			root = FXMLLoader.load(getClass().getResource("CompanyHome.fxml"));
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		Scene scene = new Scene(root);
		s.setTitle("Computer Part Company Login");
		s.setScene(scene);
		
		s.show();
		
	}

	public static void main(String[] args) {
		launch(args);
	}
}
