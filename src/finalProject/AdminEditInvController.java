package finalProject;

import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class AdminEditInvController {

	// an edit inventory that will make use of tree sets, adding/deleting from
	// database
	private TreeSet items;
	private String part;
	private int idNum;
	private int amt;
	private String item;
	private float price;
	private String company;
	private boolean exist = false;
	ArrayList<Integer> search = new ArrayList<Integer>();
	private TreeMap<String, ProductsObj> itemTMap = new TreeMap<String, ProductsObj>();
	@FXML
	Text editingTxt;
	@FXML
	TextField amountField;
	@FXML
	TextField deviceField;
	@FXML
	TextField priceField;
	@FXML
	TextField companyField;
	@FXML
	TextField addAmtField;
	@FXML
	TextField sIdField;
	@FXML
	Text sTxt;

	public void recieve(String item) {
		part = item;
		editingTxt.setText(item);
	}

	public void addInv(ActionEvent event) throws SQLException {
		String DBPath = "45.55.136.114/computerParts";
		String fName = "pw.txt";
		String id = "csc3610";
		DBAbstract DB = new DBAbstract(DBPath, fName, id);
		// Connection con = DB.getConnection();
		String fields = "";

		Connection connection = DB.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet r = null;

		if (part.equals("CPU")) {
			fields = "(CPU_NUM,CPU_AMOUNT, CPU_NAME,CPU_PRICE,CPU_COMPANY)";
			r = stmt.executeQuery("SELECT * FROM `CPU` limit 10000");
		} else if (part.equals("SSD")) {
			fields = "(SSD_NUM, SSD_AMOUNT, SSD_NAME, SSD_PRICE, SSD_COMPANY)";
			r = stmt.executeQuery("SELECT * FROM `SSD` limit 10000");
		} else if (part.equals("FANS")) {
			fields = "(FAN_NUM, FAN_AMOUNT, FAN_NAME, FAN_PRICE, FAN_COMPANY)";
			r = stmt.executeQuery("SELECT * FROM `FANS` limit 10000");
		} else if (part.equals("POWER")) {
			fields = "(POW_NUM, POW_AMOUNT, POW_NAME, POW_PRICE, POW_COMPANY)";
			r = stmt.executeQuery("SELECT * FROM `POWER` limit 10000");

		}

		String query = " insert into " + part + fields + " values (?,?,?,?,?)";

		int size = 0;

		while (r.next()) {
			size++;
		}

		PreparedStatement preparedStmt = connection.prepareStatement(query);
		preparedStmt.setInt(1, size + 1); //
		preparedStmt.setInt(2, Integer.parseInt(amountField.getText())); // first
																			// name
		preparedStmt.setString(3, deviceField.getText());
		preparedStmt.setFloat(4, Float.parseFloat(priceField.getText()));
		preparedStmt.setString(5, companyField.getText()); // gpa

		preparedStmt.execute();
	}

	public void searchId(ActionEvent event) throws SQLException {
		exist = false; // assume its false and reset it if found true
		String DBPath = "45.55.136.114/computerParts";
		String fName = "pw.txt";
		String id = "csc3610";
		DBAbstract DB = new DBAbstract(DBPath, fName, id);
		Connection connection = DB.getConnection();
		Statement stmt = connection.createStatement();
		ResultSet r = null;

		r = stmt.executeQuery("SELECT * FROM `" + part + "` limit 10000");
		int i = 0;
		search = new ArrayList<Integer>();
		while (r.next()) {
			// search[i] = r.getInt(1);
			search.add(r.getInt(1));
			itemTMap.put(
					Integer.toString(r.getInt(1)),
					new ProductsObj(Integer.toString(r.getInt(1)), Integer
							.toString(r.getInt(2)), r.getString(3), Float
							.toString(r.getFloat(4)), r.getString(5)));
			i++;

			/*
			 * if (r.getInt(1) == Integer.parseInt(sIdField.getText())) { exist
			 * = true; idNum = r.getInt(1); amt = r.getInt(2); item =
			 * r.getString(3); price = r.getFloat(4); company = r.getString(5);
			 * 
			 * sTxt.setText(r.getInt(1) + " " + r.getInt(2) + " " +
			 * r.getString(3) + " " + r.getFloat(4) + " " + r.getString(5));
			 * break; }
			 */

		}
		search = sortBubble(search);

		int a = 0;
		int l = 0;
		int[] array = new int[search.size()];

		for (int b = 0; b < search.size(); b++) {

			array[b] = search.get(b);
		}
		System.out.println(Arrays.toString(array));
		int num = binarySearch(array, l, array.length - 1,
				Integer.parseInt(sIdField.getText()));

		if (num == -1) {

			sTxt.setText("ID Not Found");
		} else {
			num++;
			String changeIN = itemTMap.get(Integer.toString(num)).getItemNum();
			String changeS = itemTMap.get(Integer.toString(num)).getStock();
			String changeP = itemTMap.get(Integer.toString(num)).getPrice();
			idNum = Integer.parseInt(changeIN);
			amt = Integer.parseInt(changeS);
			item = itemTMap.get(Integer.toString(num)).getItem();
			price = Float.parseFloat(changeP);
			company = itemTMap.get(Integer.toString(num)).getCompany();

			sTxt.setText(itemTMap.get(Integer.toString(num)).getItemNum() + " "
					+ itemTMap.get(Integer.toString(num)).getStock() + " "
					+ itemTMap.get(Integer.toString(num)).getItem() + " "
					+ itemTMap.get(Integer.toString(num)).getPrice() + " "
					+ itemTMap.get(Integer.toString(num)).getCompany());
			System.out.println("FOUND IT");
			exist = true;
		}
	}

	private int binarySearch(int[] arr, int l, int r, int x) {

		if (r >= l) {
			int mid = l + (r - l) / 2;

			// If the element is present at the
			// middle itself
			if (arr[mid] == x)
				return mid;

			// If element is smaller than mid, then
			// it can only be present in left subarray
			if (arr[mid] > x)
				return binarySearch(arr, l, mid - 1, x);

			// Else the element can only be present
			// in right subarray
			return binarySearch(arr, mid + 1, r, x);
		}

		// We reach here when element is not present
		// in array
		return -1;
	}

	private ArrayList<Integer> sortBubble(ArrayList<Integer> search2) {
		int n = search2.size();
		for (int i = 0; i < n - 1; i++)
			for (int j = 0; j < n - i - 1; j++)
				if (search2.get(j) > search2.get(j + 1)) {

					int temp = search2.get(j);
					search2.add(j, search2.get(j + 1));
					search2.add(j + 1, temp);
				}
		return search2;
	}

	public void addAmt(ActionEvent event) throws SQLException {

		if (exist) {
			String DBPath = "45.55.136.114/computerParts";
			String fName = "pw.txt";
			String id = "csc3610";
			DBAbstract DB = new DBAbstract(DBPath, fName, id);
			Connection connection = DB.getConnection();
			Statement stmt = connection.createStatement();
			ResultSet r = null;
			String q = null;
			System.out.println(idNum);
			if (part.equals("CPU")) {
				q = "UPDATE `CPU` SET `CPU_NUM`='" + idNum + "',`CPU_AMOUNT`='"
						+ (amt + Integer.parseInt(addAmtField.getText()))
						+ "',`CPU_NAME`='" + item + "',`CPU_PRICE`='" + price
						+ "',`CPU_COMPANY`='" + company + "' WHERE `CPU_NUM` ="
						+ idNum;
			} else if (part.equals("FANS")) {
				q = "UPDATE `FANS` SET `FAN_NUM`='" + idNum
						+ "',`FAN_AMOUNT`='"
						+ (amt + Integer.parseInt(addAmtField.getText()))
						+ "',`FAN_NAME`='" + item + "',`FAN_PRICE`='" + price
						+ "',`FAN_COMPANY`='" + company + "' WHERE `FAN_NUM` ="
						+ idNum;

			} else if (part.equals("HDD")) {
				q = "UPDATE `HDD` SET `HDD_NUM`='" + idNum + "',`HDD_AMOUNT`='"
						+ (amt + Integer.parseInt(addAmtField.getText()))
						+ "',`HDD_NAME`='" + item + "',`HDD_PRICE`='" + price
						+ "',`HDD_COMPANY`='" + company + "' WHERE `HDD_NUM` ="
						+ idNum;

			} else if (part.equals("SSD")) {
				q = "UPDATE `SSD` SET `SSD_NUM`='" + idNum + "',`SSD_AMOUNT`='"
						+ (amt + Integer.parseInt(addAmtField.getText()))
						+ "',`SSD_NAME`='" + item + "',`SSD_PRICE`='" + price
						+ "',`SSD_COMPANY`='" + company + "' WHERE `SSD_NUM` ="
						+ idNum;

			} else if (part.equals("POWER")) {
				q = "UPDATE `POWER` SET `POW_NUM`='" + idNum
						+ "',`POW_AMOUNT`='"
						+ (amt + Integer.parseInt(addAmtField.getText()))
						+ "',`POW_NAME`='" + item + "',`POW_PRICE`='" + price
						+ "',`POW_COMPANY`='" + company + "' WHERE `POW_NUM` ="
						+ idNum;

			}

			try {
				stmt.executeUpdate(q);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
}