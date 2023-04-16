package controller;

import model.DataBase;
import model.Event;
import model.Customer;

import java.util.ArrayList;

import model.Account;
import model.Transaction;
import model.Price;

public class DbController {
	
	private DataBase db = new DataBase();
	
	public DbController() {
		
	}
	
	public void createEvent(Event event) {
		db.createEvent(event);
	}
	
	public Event searchEventById(int id) {
		return db.searchEventById(id);
	}
	
	public ArrayList<Event> searcAllEvent() {
		return db.searcAllEvent();
	}
	
	public void createCustomer(Customer customer) {
		db.createCustomer(customer);
	}
	
	public Customer searchCustomerById(int id) {
		return db.searchCustomerById(id);
	}
	
	public ArrayList<Customer> searcAllCustomer() {
		return db.searcAllCustomer();
	}
	
	public void createAccount(Account account) {
		db.createAccount(account);
	}
	
	public void createTransaction(Transaction trans) {
		db.createTransaction(trans);
	}
	
	public void createPrice(Price price) {
		db.createPrice(price);
	}
	
}
