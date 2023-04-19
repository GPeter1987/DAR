package model;

import java.time.LocalDate;

public class Event {
	private int id;
	private String name;
	private EventType type;
	private LocalDate date;
	private int dojoId;

	public Event(int id, String name, EventType type, LocalDate date, int dojoId) {
		this.id = id;
		this.name = name;
		this.type = type;
		this.date = date;
		this.dojoId = dojoId;
	}

	public Event(String name, EventType type, LocalDate date, int dojoId) {
		this.id = 0; // Adatbázis autoincremetelt értékét tesszük majd ide létrehozásnál.
		this.name = name;
		this.type = type;
		this.date = date;
		this.dojoId = dojoId;
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

	public int getDojoId() {
		return dojoId;
	}

	public void setDojo(int id) {
		this.dojoId = id;
	}

	@Override
	public String toString() {
		return String.valueOf(id) + ", " + name;
	}
}
