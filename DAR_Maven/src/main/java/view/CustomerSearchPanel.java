package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

import javax.swing.JButton;
import javax.swing.JPanel;

import model.Customer;
import model.CustomerRank;
import model.CustomerStatus;
import controller.CustomerController;

public class CustomerSearchPanel extends JPanel {

	private JButton searchBtn;
	private CusDataPanel cusDataPanel = new CusDataPanel();
	private CustomerController custCtrl = new CustomerController();

	public CustomerSearchPanel() {
		setLayout(new GridLayout(3, 1));
		add(cusDataPanel);
		searchBtn = new JButton("Keresés...");
		JPanel buttonPanel = new JPanel();
		buttonPanel.setLayout(new GridLayout(1, 1));
		buttonPanel.add(searchBtn);
		add(buttonPanel);

		searchBtn.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				int cusId = Integer.valueOf(cusDataPanel.getCusId().getFieldText());
				String cusName = cusDataPanel.getCusName().getFieldText();
				CustomerStatus cusStatus = CustomerStatus.valueOf(cusDataPanel.getStatus().getFieldText());
				CustomerRank cusRank = CustomerRank.valueOf(cusDataPanel.getRank().getFieldText());
				/*
				 * A Dojo id-t a név alapján kell kigyűjteni a customerek közül
				 * előfordulhat ,hogy több is lesz nem csak egy
				 *  - Kell egy metódus ami név alapján kigyűjti a hasonló nevű dojokat
				 * */
				String dojoNameSample = cusDataPanel.getDojoName().getFieldText();
				int dojoId = 0;
				LocalDate cusBDate = cusDataPanel.getBirthDate().getDate();
				int accId = Integer.valueOf(cusDataPanel.getAccountId().getFieldText());
				String cusEmail = cusDataPanel.getEmail().getFieldText();
				boolean passive = cusDataPanel.getPassive().isChecked();

				Customer sample = new Customer(cusId, cusName, cusStatus, cusRank, dojoId, cusBDate, accId, cusEmail,
						passive);
				
				// Meg kell nézni ,hogy az adatbáziban vannak e a mintához hasonló elemek

			}

		});
	}

}
