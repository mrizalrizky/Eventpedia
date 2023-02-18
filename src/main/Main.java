package main;
import java.util.ArrayList;
import java.util.Scanner;

import builder.EventBuilder;
import factory.AdminFactory;
import factory.PersonFactory;
import factory.UserFactory;
import iterator.FIFOiterator;
import model.Event;
import model.Person;
import proxy.UserDatabaseProxy;
import singleton.EventDatabaseManager;

public class Main {

	EventDatabaseManager edm = EventDatabaseManager.getInstance();
	UserDatabaseProxy udm = UserDatabaseProxy.getInstance();
	Scanner sc = new Scanner(System.in);
	
	public void displayEventList() {
		ArrayList<Event> list = edm.getEventList();
		FIFOiterator<Event> EventIterator = new FIFOiterator<>(list);
		int i = 1;
		while (EventIterator.hasNext()) {
			Event e = EventIterator.getNext();
			System.out.println("Event ID : " + i++ + "\n"
					+ "Event Title: " + e.getTitle() + "\n"
					+ "Organizer Name: " + e.getOrganizerName() + "\n"
					+ "Event Venue: " + e.getLocation() + "\n"
					+ "Event Date: " + e.getDate() + "\n");
		}
	}

	public void register() {
		String username;
		String email;
		String password;
		String role;

		do {
			System.out.print("Input your username [5 - 30 (inclusive)]: ");
			username = sc.nextLine();
		}  while (username.length() < 5 || username.length() > 30);
		
		do {
			System.out.print("Input your password [At least 8 characters long]: ");
			password = sc.nextLine();
		}  while (password.length() < 8);

		System.out.print("Input your email: ");
		email = sc.nextLine();

		do {
			System.out.print("Input your role [Admin | User (Case sensitive)]: ");
			role = sc.nextLine();
		}  while (!role.equals("Admin") && !role.equals("User"));

		PersonFactory f;

		if (role.equals("Admin")){
			f = new AdminFactory();
			Person p = f.create(username, password, email);
			udm.addPerson(p);
		} else {
			f = new UserFactory();
			Person p = f.create(username, password, email);
			udm.addPerson(p);
		}
		System.out.println("Account succesfully registered");
		System.out.println("Press enter to continue...");
		sc.nextLine();
	}

	public void createEvent() {
		String inputTitle;
		String inputOrganizerName;
		String inputLocation;
		String inputDate;
		
		do {
			System.out.print("Input Event Title [1..32]: ");
			inputTitle = sc.nextLine();
		} while(inputTitle.length() < 1 || inputTitle.length() > 32);
		
		do {
			System.out.print("Input Organizer Name [1..32]: ");
			inputOrganizerName = sc.nextLine();
		} while(inputOrganizerName.length() < 1 || inputOrganizerName.length() > 32);

		do {
			System.out.print("Input Event Location [1..16]: ");
			inputLocation = sc.nextLine();
		} while(inputLocation.length() < 1 || inputLocation.length() > 16);
		
		do {
			System.out.print("Input Event Date [dd/mm/yy]: ");
			inputDate = sc.nextLine();
		} while(!(inputDate.charAt(2) == '/') || !(inputDate.charAt(5) == '/'));
		
		edm.getEventList().add(new EventBuilder().setEventTitle(inputTitle).setOrganizerName(inputOrganizerName)
				.setLocation(inputLocation).setDate(inputDate).build());
		
		System.out.println("\nEvent successfuly created!");
		System.out.println("Press enter to continue...");
		sc.nextLine();
	}
	
	public void editEvent() {
		int ch = -1;
		int inputID;
		String inputTitle, inputOrganizerName, inputLocation, inputDate;
		
		if(edm.getEventList().isEmpty()) {
			System.out.println("There are no events to modify.");
			System.out.println("Press enter to continue...");
			sc.nextLine();
		}
		else {
			displayEventList();
			do {
				System.out.print("Input Event ID to modify [1.." + edm.getEventList().size() + "]: ");
				inputID = sc.nextInt(); sc.nextLine();
			} while(inputID < 1 || inputID > edm.getEventList().size());
			
			do {
				System.out.println("Choose option to modify: ");
				System.out.println("1. Title");
				System.out.println("2. Organizer Name");
				System.out.println("3. Location");
				System.out.println("4. Date");
				ch = sc.nextInt(); sc.nextLine();
			} while(ch < 1 || ch > 4);
			
			switch(ch) {
				case 1:
					do {
						System.out.print("Input Event Title [1..32]: ");
						inputTitle = sc.nextLine();
					} while(inputTitle.length() < 1 || inputTitle.length() > 32);
					edm.getEventList().get(inputID-1).setTitle(inputTitle);
					break;
				case 2:
					do {
						System.out.print("Input Organizer Name [1..32]: ");
						inputOrganizerName = sc.nextLine();
					} while(inputOrganizerName.length() < 1 || inputOrganizerName.length() > 32);
					edm.getEventList().get(inputID-1).setOrganizerName(inputOrganizerName);
					break;
				case 3:
					do {
						System.out.print("Input Event Location [1..16]: ");
						inputLocation = sc.nextLine();
					} while(inputLocation.length() < 1 || inputLocation.length() > 16);
					edm.getEventList().get(inputID-1).setLocation(inputLocation);
					break;
				case 4:
					do {
						System.out.print("Input Event Date [dd/mm/yy]: ");
						inputDate = sc.nextLine();
					} while(!(inputDate.charAt(2) == '/') && !(inputDate.charAt(5) == '/'));
					edm.getEventList().get(inputID-1).setDate(inputDate);
					break;
			}
			
			System.out.println("Successfully edited event!");
			System.out.println("Press enter to continue...");
			sc.nextLine();
		}
	}
	
	public void deleteEvent() {
		int inputID;
		
		if(edm.getEventList().isEmpty()) {
			System.out.println("There are no events to delete.");
			System.out.println("Press enter to continue...");
			sc.nextLine();
		}
		else {
			displayEventList();
			do {
				System.out.print("Input Event ID to delete [1.." + edm.getEventList().size() + "]: ");
				inputID = sc.nextInt(); sc.nextLine();
			} while(inputID < 1 || inputID > edm.getEventList().size());
			
			edm.getEventList().remove(inputID-1);
			System.out.println("\nEvent ID " + inputID + " has been deleted!");
			System.out.println("Press enter to continue...");
			sc.nextLine();
		}
	}
	
	// User Menu
	public void joinEvent() {
		int inputID;
		
		if (edm.getEventList().isEmpty()) {
			System.out.println("There are no events at the moment.");
			System.out.println("Press enter to continue...");
			sc.nextLine();
		} else {
			viewEventList();
			do {
				System.out.print("Input Event ID to join [1.." + edm.getEventList().size() + "]: ");
				inputID = sc.nextInt(); sc.nextLine();
			} while(inputID < 1 || inputID > edm.getEventList().size());
		}
		
		System.out.println("Successfully joined event!");
	}

	public void viewEventList() {
		if(edm.getEventList().isEmpty()) {
			System.out.println("There are no events at the moment.");
			System.out.println("Press enter to continue...");
			sc.nextLine();
		}
		else {
			displayEventList();
		}
	}
	
	public Main() {
		int opsi = 0;
		
		do {
			System.out.println("Welcome to Eventpedia!");
			System.out.println("=====================");
			System.out.println("1. Register New Account");
			System.out.println("2. Login");
			System.out.println("3. Exit Application");
			System.out.print(">> ");
			opsi = sc.nextInt(); sc.nextLine();
			
			switch(opsi) {
				case 1:
					register();
					break;
				case 2:
					login();
					break;
			}
		} while (opsi != 3);
		
		System.out.println("Thank you for using Eventpedia!");
	}

	public void login() {
		String username;
		String password;
		String role = null;
		int flag = -1;
		do {
			System.out.print("Input your username: ");
			username = sc.nextLine();
			System.out.print("Input your password: ");
			password = sc.nextLine();
			Person p = udm.checkLogin(username, password);	
			if (p != null){
				flag =1;
				role = p.getRole();
			}
			else
			{System.out.println("Incorrect username or password!");}
		} while(flag!=1);
		System.out.println("Login Suceed!!!");
		System.out.println();
		if (role.equals("Admin")) adminMenu();
		else if (role.equals("User")) userMenu();
	}

	public void adminMenu() {
		int ch = -1;
		do {
			System.out.println("Eventpedia Admin Menu");
			System.out.println("=====================");
			System.out.println("1. Create New Event");
			System.out.println("2. Edit Event");
			System.out.println("3. Delete Event");
			System.out.println("4. Exit to Main Menu");
			System.out.print(">> ");
			ch = sc.nextInt(); sc.nextLine();
			
			switch(ch) {
			case 1:
				createEvent();
				break;
			case 2:
				editEvent();
				break;
			case 3:
				deleteEvent();
				break;
			}
		} while (ch != 4);
		
	}
	
	public void userMenu() {
		int ch = -1;
		do {
			System.out.println("Eventpedia User Menu");
			System.out.println("====================");
			System.out.println("1. Join Event");
			System.out.println("2. View Event List");
			System.out.println("3. Exit to Main Menu");
			System.out.print(">> ");
			ch = sc.nextInt(); sc.nextLine();
			switch(ch) {
			case 1:
				joinEvent();
				break;
			case 2:
				viewEventList();
				break;
			default:
				break;
			}
		} while (ch != 3);	
	}
	
	public void cls() {
		for(int i=0;i<15;i++) System.out.println();
	}

	public static void main(String[] args) {
		new Main();
	}
}
