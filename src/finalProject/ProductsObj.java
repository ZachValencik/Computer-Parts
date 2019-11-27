package finalProject;

import javafx.beans.property.SimpleStringProperty;

public class ProductsObj {
	/*private String name;
	private String brand;
	private String price;
	private String proNum;
	private String stock;
	*/
	private SimpleStringProperty itemNum;
    private  SimpleStringProperty stock;
    private  SimpleStringProperty item;
    private  SimpleStringProperty price;
    private SimpleStringProperty company;
	
	public ProductsObj(String itemNum, String stock, String item, String price, String company) {
		//super();
		this.itemNum = new SimpleStringProperty(itemNum);
		this.stock = new SimpleStringProperty(stock);
		this.item = new SimpleStringProperty(item);
		this.price = new SimpleStringProperty(price);
		this.company = new SimpleStringProperty(company);
	}

	public String getItemNum() {
		return itemNum.get();
	}

	public String getStock() {
		return stock.get();
	}

	public String getItem() {
		return item.get();
	}

	public String getPrice() {
		return price.get();
	}

	public String getCompany() {
		return company.get();
	}

	

	
	
	}


