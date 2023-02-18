package builder;

import model.Event;

public class EventBuilder {
	private Event event;
	
	public EventBuilder() {
		this.event = new Event();
	}

	public EventBuilder setEventTitle(String title) {
		event.setTitle(title);
		return this;
	}
	
	public EventBuilder setOrganizerName(String name) {
		event.setOrganizerName(name);
		return this;
	}
	
	public EventBuilder setLocation(String location) {
		event.setLocation(location);
		return this;
	}
	
	public EventBuilder setDate(String date) {
		event.setDate(date);
		return this;
	}
	
	public Event build() {
		return this.event;
	}
}
