package model;

import java.time.LocalDate;

public class Customer {
	
	private int id;
	private String name;
	private CustomerStatus status;
	private CustomerRank rank;
	private Customer dojo;
	private LocalDate birthDate;
	private Account account;
	private String email;
	private Boolean passive;
	
	public Customer(int id,
					String name, 
					CustomerStatus status, 
					CustomerRank rank, 
					Customer dojo, 
					LocalDate birthDate, 
					Account account,
					String email,
					Boolean passive) {
		this.id = id;
		this.name = name;
		this.status = status;
		this.rank = rank;
		this.dojo = dojo;
		this.birthDate = birthDate;
		this.account = account;
		this.email = email;
		this.passive = passive;
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

	public Customer getDojo() {
		return dojo;
	}

	public void setDojo(Customer dojo) {
		this.dojo = dojo;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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
	
	
}
