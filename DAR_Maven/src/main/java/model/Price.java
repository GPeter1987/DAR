package model;

public class Price {
	
	private int id;
	private String name;
	private double amount;
	private Devisa devisa;
	
	public Price(int id,
				 String name,
				 double amount,
				 Devisa devisa) {
		this.id = id;
		this.name = name;
		this.amount = amount;
		this.devisa = devisa;
	}
	
	public Price(String name,
				 double amount,
				 Devisa devisa) {
		this.id = 0; // Adatbázis autoincremetelt értékét tesszük majd ide létrehozásnál.
		this.name = name;
		this.amount = amount;
		this.devisa = devisa;
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

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public Devisa getDevisa() {
		return devisa;
	}

	public void setDevisa(Devisa devisa) {
		this.devisa = devisa;
	}
	
	
	
}
