package controller;

import model.CustomerStatus;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

import model.Customer;
import model.Account;
import model.CustomerRank;

public class CustomerController {

	private DbController dBc = new DbController();

	public CustomerController() {

	}

	public String[] getStatuses() {
		int enumArrayLength = CustomerStatus.values().length;
		String[] arr = new String[enumArrayLength];
		int i = 0;
		for (CustomerStatus s : CustomerStatus.values()) {
			if (i < enumArrayLength) {
				arr[i] = String.valueOf(s);
				i++;
			}
		}
		return arr;
	}

	public String[] getRanks() {
		int enumArrayLength = CustomerRank.values().length;
		String[] arr = new String[enumArrayLength];
		int i = 0;
		for (CustomerRank r : CustomerRank.values()) {
			if (i < enumArrayLength) {
				arr[i] = String.valueOf(r);
				i++;
			}
		}
		return arr;
	}

	public String[] getDojos() {
		ArrayList<Customer> dojos = dBc.searchAllDojo();
		String[] dojoNames = new String[dojos.size()];
		for (int i = 0; i < dojos.size(); i++) {
			dojoNames[i] = dojos.get(i).getName();
		}
		return dojoNames;
	}

	public int getDojoIdByStatusText(String status) {
		int dojoId = 0;
		if (!status.equals("NONE")) {
			dojoId = dBc.searchDojoIdByName(status);
		}
		return dojoId;
	}
	
	public LocalDate validateBDate(String year, String month, String day) {
		LocalDate bDate = null;
		try {
			bDate = LocalDate.of(Integer.valueOf(year), Integer.valueOf(month),
					Integer.valueOf(day));
			return bDate;
		} catch (DateTimeException exc) {
			System.out.println("Rossz születési dátum lett megadva!");
			return null;
		}
	}

	public void createCustomer(String name, CustomerStatus status, CustomerRank rank, int dojoId, LocalDate bDate,
			String email, boolean pass) {
		// Létre kell hozni egy felhasználót
		Customer cust = new Customer(name, status, rank, dojoId, bDate, 0, email, pass);
		dBc.createCustomer(cust);
		int custId = dBc.searchCustIdByName(name, email);
		// Kell csinálni egy accountot a felhasználóhoz
		Account acc = new Account(custId);
		dBc.createAccount(acc);
		int accId = dBc.searchAccIdByCustId(custId);
		// Updatelni kell a felhasználót az új számlával
		cust = new Customer(custId, name, status, rank, dojoId, bDate, accId, email, pass);
		dBc.modifyCustomer(cust);
	}
	
	// Az ügyfél keresése keresőképernyőn megadott értékek alapján
	public ArrayList<Customer> searchCustomer(Customer sample) {
		ArrayList<Customer> custList = dBc.searchAllCustomerLike(sample);
		
		
		
		
		return custList;
	}
	
}
