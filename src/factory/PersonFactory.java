package factory;

import model.Person;

public abstract class PersonFactory {
	public abstract Person create (String username, String password, String email);
}
