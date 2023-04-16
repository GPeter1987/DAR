package model;

public class Account {
	private int id;
	private int customerId;
	
	public Account(int id, int customerId) {
		this.id = id;
		this.customerId = customerId;
	}
	
	public Account(int customerId) {
		this.id = 0; // Adatbázis autoincremetelt értékét tesszük majd ide létrehozásnál.
		this.customerId = customerId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCustomerId() {
		return customerId;
	}

	public void setCustomerId(int id) {
		this.customerId = id;
	}
	
	
}
