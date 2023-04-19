package model;

public class Participant {
	
	private int id;
	private int eventId;
	private int customerId;
	
	public Participant(int id, 
					   int eventId, 
					   int customerId) {
		this.id = id;
		this.eventId = eventId;
		this.customerId = customerId;
	}
	
	public Participant(int eventId, 
			   		   int customerId) {
		this.id = 0; // Adatbázis autoincremetelt értékét tesszük majd ide létrehozásnál. 
		this.eventId = eventId;
		this.customerId = customerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEventId() {
		return eventId;
	}

	public void setEventId(int eventId) {
		this.eventId = eventId;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}

	
	
	
}
