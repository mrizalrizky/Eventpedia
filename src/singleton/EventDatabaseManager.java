package singleton;

import java.util.ArrayList;

import model.Event;

public class EventDatabaseManager {
	private static EventDatabaseManager instance = null;
	private ArrayList<Event> eventList = new ArrayList<>();
	
	private EventDatabaseManager() {
	}
	
	public static EventDatabaseManager getInstance() {
		if(instance == null) {
			instance = new EventDatabaseManager();
		}
		return instance;
	}

	public ArrayList<Event> getEventList() {
		return eventList;
	}
}
