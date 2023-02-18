package factory;

import model.Person;
import model.User;

public class UserFactory extends PersonFactory {
	@Override
	public Person create(String username, String password, String email) {
		return new User ( username, password, email);
	}
}
