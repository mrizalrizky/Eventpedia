package model;

public class Admin extends Person {

	public Admin(String username, String password, String email) {
		super(username, password, email);
		this.role = "Admin";
	}
}
