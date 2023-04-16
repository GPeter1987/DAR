package controller;

import java.time.LocalDate;

import javax.swing.SwingUtilities;

import view.MainFrame;

public class AppStart {

	public static void main(String[] args) {
		
		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {
				new MainFrame();
			}
			
		});
		
		// Teszt kód ide!
		DbController dBc = new DbController();
		model.Event event = new model.Event("Edzőtábor 2023/1", 
											 model.EventType.SEMINAR, 
											 LocalDate.of(2023,04,29), 
											 1);
		model.Customer cust = new model.Customer("Chuushin Dojo", 
												  model.CustomerStatus.DOJO, 
												  model.CustomerRank.NONE, 
												  0, 
												  LocalDate.of(2015,1,1), 
												  1,
												  "aikidocegled@gmail.com",
												  false);
		model.Account acc = new model.Account(1);
		model.Transaction trans = new model.Transaction(1,
														model.TransactionType.INCOME,
														model.Devisa.HUF,
														5000.00);
		model.Price price = new model.Price("Edzés díj (Havi)", 5000.00, model.Devisa.HUF);
		//dBc.createEvent(event);
		/*
		System.out.println(dBc.searchEventById(2).toString());
		java.util.ArrayList<model.Event> events= dBc.searcAllEvent();
		
		for(model.Event e : events) {
			System.out.println(e.toString());
		}
		
		System.out.println(dBc.searchCustomerById(1).toString());
		java.util.ArrayList<model.Customer> Customers= dBc.searcAllCustomer();
		
		for(model.Customer e : Customers) {
			System.out.println(e.toString());
		}
		*/
	}

}
