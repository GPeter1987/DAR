package controller;

import java.util.ArrayList;

import javax.swing.JTable;

import model.Customer;
import model.Event;

public class TableController {

	private DbController dBc = new DbController();

	public TableController() {

	}

	public JTable createEventTable(ArrayList<Event> events) {

		JTable table = null;
		String[] colNames = { "Esemény neve", " Esemény Típusa", "Esemény Dátuma", "Szervező" };
		Object[][] data = new Object[events.size()][4];
		for (int i = 0; i < events.size(); i++) {
			data[i][0] = events.get(i).getName();
			data[i][1] = events.get(i).getType();
			data[i][2] = events.get(i).getDate();
			data[i][3] = dBc.searchCustomerById(events.get(i).getDojoId()).getName();
		}

		return table = new JTable(data, colNames);
	}

	public JTable createEventPartTable(ArrayList<Customer> customers) {
		JTable table = null;
		String[] colNames = { "Név", "Fokozat", "Dojo", "Email cím" };
		Object[][] data = new Object[customers.size()][4];
		for (int i = 0; i < customers.size(); i++) {
			data[i][0] = customers.get(i).getName();
			data[i][1] = customers.get(i).getRank();

			if (customers.get(i).getDojoId() > 0) {
				data[i][2] = dBc.searchCustomerById(customers.get(i).getDojoId()).getName();
			} else {
				data[i][2] = customers.get(i).getName();
			}

			data[i][3] = customers.get(i).getEmail();
		}
		return table = new JTable(data, colNames);
	}
}
