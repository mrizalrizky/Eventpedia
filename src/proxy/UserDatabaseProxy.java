package proxy;

import java.util.ArrayList;

import model.Person;

public class UserDatabaseProxy {
	private ArrayList<Person> list = new ArrayList<>();
	private static UserDatabaseProxy instance = null;
	
	public UserDatabaseProxy() {
		list = new ArrayList<>();
	}

	public ArrayList<Person> getList() {
		return list;
	}

	public static UserDatabaseProxy getInstance() {
		if(instance == null) {
			return new UserDatabaseProxy();
		}
		return instance;
	}
	
	public void addPerson(Person p) {
		list.add(p);
	}
	
	public Person checkLogin(String name, String password) {
		for (Person person : list) {
			if (person.getUsername().equals(name) && person.getPassword().equals(password)) {
				return person;
			}
		}
		return null;
	}
}
