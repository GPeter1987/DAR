package controller;

import model.DataBase;
import model.Event;
import model.Participant;
import model.Customer;

import java.util.ArrayList;

import model.Account;
import model.Transaction;
import model.TransactionType;
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
	
	public void modifyEvent(Event event) {
		db.modifyEvent(event);
	}
	
	public void deleteEvent(Event event) {
		db.deleteEvent(event);
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
	
	public void modifyCustomer(Customer customer) {
		db.modifyCustomer(customer);
	}
	
	public void createAccount(Account account) {
		db.createAccount(account);
	}
	
	public void modifyAccount(Account acc) {
		db.modifyAccount(acc);
	}
	
	public void createTransaction(Transaction trans) {
		db.createTransaction(trans);
	}
	
	public Transaction searchTransactionById(int id) {
		return db.searchTransactionById(id);
	}
	
	public ArrayList<Transaction> searchTransactionsByAccId(int id) {
		return db.searchTransactionsByAccId(id);
	}
	
	public ArrayList<Transaction> searchTransactionByType(TransactionType type){
		return db.searchTransactionsByType(type);
	}
	
	public ArrayList<Transaction> searchAllTransaction() {
		return db.searcAllTransaction();
	}
	
	public void modifyTransaction(Transaction transaction) {
		db.modifyTransaction(transaction);
	}
	
	public void createPrice(Price price) {
		db.createPrice(price);
	}
	
	public void modifyPrice(Price price) {
		db.modifyPrice(price);
	}
	
	public void deletePrice(Price price) {
		db.deletePrice(price);
	}
	
	public void createParticipant(Event event, Customer customer) {
		db.createParticipant(event, customer);
	}
	
	public Participant searchParticipantById(int id) {
		return db.searchParticipantById(id);
	}
	
	public void deleteParticipant(Participant participant) {
		db.deleteParticipant(participant);
	}
}
