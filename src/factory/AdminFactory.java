package factory;

import model.Admin;
import model.Person;

public class AdminFactory extends PersonFactory {
	@Override
	public Person create(String username, String password, String email) {
		return new Admin (username, password, email);
	}
}
