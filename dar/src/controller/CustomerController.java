package controller;

import model.CustomerStatus;

import java.util.ArrayList;

import model.Customer;
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
}
