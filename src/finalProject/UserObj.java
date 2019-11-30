package finalProject;

public class UserObj {
	
	private int uNum;
	private String lName;
	private String fName;
	private String phone;
	private String email;
	private String user;
	private String pass;
	
	
	
	
	public UserObj(int uNum, String lName, String fName, String phone,
			String email, String user, String pass) {

		this.uNum = uNum;
		this.lName = lName;
		this.fName = fName;
		this.phone = phone;
		this.email = email;
		this.user = user;
		this.pass = pass;
	}
	public int getuNum() {
		return uNum;
	}
	public String getlName() {
		return lName;
	}
	public String getfName() {
		return fName;
	}
	public String getPhone() {
		return phone;
	}
	public String getEmail() {
		return email;
	}
	public String getUser() {
		return user;
	}
	public String getPass() {
		return pass;
	}
	
	

}
