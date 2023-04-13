package model;

public class Transaction {
	
	private int id;
	private Account account;
	private TransactionType type;
	private Devisa devisa;
	private double amount;
	
	public Transaction(int id,
					   Account account,
					   TransactionType type,
					   Devisa devisa,
					   double amount) {
		this.id = id;
		this.account = account;
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

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
