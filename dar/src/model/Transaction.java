package model;

public class Transaction {
	
	private int id;
	private int accountId;
	private TransactionType type;
	private Devisa devisa;
	private double amount;
	
	public Transaction(int id,
					   int accountId,
					   TransactionType type,
					   Devisa devisa,
					   double amount) {
		this.id = id;
		this.accountId = accountId;
		this.type = type;
		this.devisa = devisa;
		this.amount = amount;
	}
	
	public Transaction(int accountId,
					   TransactionType type,
					   Devisa devisa,
					   double amount) {
		this.id = 0; // Adatbázis autoincremetelt értékét tesszük majd ide létrehozásnál.
		this.accountId = accountId;
		this.type = type;
		this.devisa = devisa;
		this.amount = amount;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public TransactionType getType() {
		return type;
	}

	public void setType(TransactionType type) {
		this.type = type;
	}

	public Devisa getDevisa() {
		return devisa;
	}

	public void setDevisa(Devisa devisa) {
		this.devisa = devisa;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
}
