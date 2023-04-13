package model;

import java.time.LocalDate;

public class Event {
	private int id;
	private String name;
	private EventType type;
	private LocalDate date;
	private Customer dojo;
	
	public Event(int id,
				 String name,
				 EventType type,
				 LocalDate date,
				 Customer dojo) {
		this.id = id;
		this.type = type;
		this.date = date;
		this.dojo = dojo;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public Customer getDojo() {
		return dojo;
	}

	public void setDojo(Customer dojo) {
		this.dojo = dojo;
	}
	
	
}
