package model;

public class Event {
	private String title;
	private String organizerName;
	private String location;
	private String date;

	public Event() {
		
	}

	public Event(String title, String organizerName, String location, String date) {
		super();
		this.title = title;
		this.organizerName = organizerName;
		this.location = location;
		this.date = date;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrganizerName() {
		return organizerName;
	}

	public void setOrganizerName(String organizerName) {
		this.organizerName = organizerName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
