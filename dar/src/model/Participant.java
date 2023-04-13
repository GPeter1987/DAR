package model;

public class Participant {
	
	private int id;
	private Event event;
	private Customer customer;
	
	public Participant(int id, Event event, Customer customer) {
		this.id = id;
		this.event = event;
		this.customer = customer;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Event getEvent() {
		return event;
	}

	public void setEvent(Event event) {
		this.event = event;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
}
