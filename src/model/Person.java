package model;

public class Person {

	private String username;
	private String password;
	private String email;
	protected String role;
	
	public String getUsername() {
		return username;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Person(String username, String password, String email) {
		super();
		this.username = username;
		this.password = password;
		this.email = email;
	}
}
