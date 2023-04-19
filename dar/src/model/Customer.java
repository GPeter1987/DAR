package model;

import java.time.LocalDate;

public class Customer {

	private int id;
	private String name;
	private CustomerStatus status;
	private CustomerRank rank;
	private int dojoId;
	private LocalDate birthDate;
	private int accountId;
	private String email;
	private Boolean passive;

	public Customer(int id, String name, CustomerStatus status, CustomerRank rank, int dojoId, LocalDate birthDate,
			int accountId, String email, Boolean passive) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.rank = rank;
		this.dojoId = dojoId;
		this.birthDate = birthDate;
		this.accountId = accountId;
		this.email = email;
		this.passive = passive;
	}

	public Customer(String name, CustomerStatus status, CustomerRank rank, int dojoId, LocalDate birthDate,
			int accountId, String email, Boolean passive) {
		this.id = 0; // Adatbázis autoincremetelt értékét tesszük majd ide létrehozásnál.
		this.name = name;
		this.status = status;
		this.rank = rank;
		this.dojoId = dojoId;
		this.birthDate = birthDate;
		this.accountId = accountId;
		this.email = email;
		this.passive = passive;
	}

	// Dummy for testing
	public Customer(int id) {
		this.id = id;
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

	public CustomerStatus getStatus() {
		return status;
	}

	public void setStatus(CustomerStatus status) {
		this.status = status;
	}

	public CustomerRank getRank() {
		return rank;
	}

	public void setRank(CustomerRank rank) {
		this.rank = rank;
	}

	public int getDojoId() {
		return dojoId;
	}

	public void setDojo(int id) {
		this.dojoId = id;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccount(int id) {
		this.accountId = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Boolean getPassive() {
		return passive;
	}

	public void setPassive(Boolean passive) {
		this.passive = passive;
	}

	@Override
	public String toString() {
		return String.valueOf(id) + ", " + name;
	}
}
