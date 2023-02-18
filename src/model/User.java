package model;

public class User extends Person {

	public User(String username, String password, String email) {
		super(username, password, email);
		this.role = "User";
	}
}
