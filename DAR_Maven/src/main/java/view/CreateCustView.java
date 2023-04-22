package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.DateTimeException;
import java.time.LocalDate;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CustomerController;
import controller.DbController;
import model.CustomerRank;
import model.CustomerStatus;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class CreateCustView extends JDialog {

	private CustomerController custCtrl = new CustomerController();
	private DbController dBc = new DbController();
	private JComboBox<String> rankCombo = new JComboBox<String>();
	private JComboBox<String> dojoCombo = new JComboBox<String>();
	LocalDate birthDate = null;

	public CreateCustView(JFrame frame) {
		super(frame, "Ügyfél létrehozása", true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		// Név
		JPanel panel = new JPanel(new GridLayout(8, 1));
		JPanel namePanel = new JPanel();
		namePanel.add(new JLabel("Név:"));
		JTextField nameField = new JTextField(20);
		namePanel.add(nameField);
		panel.add(namePanel);

		// Státusz
		JPanel statusPanel = new JPanel();
		statusPanel.add(new JLabel("Státusz:"));
		JComboBox<String> statusCombo = new JComboBox<String>(custCtrl.getStatuses());
		statusPanel.add(statusCombo);
		panel.add(statusPanel);

		// Rang
		JPanel rankPanel = new JPanel();
		rankPanel.add(new JLabel("Rang:"));
		// A Combobox kezdő érték készletét megadjuk
		if (statusCombo.getSelectedItem().equals("DOJO")) {
			rankCombo = new JComboBox<String>(new String[] { "NONE" });
		} else {
			rankCombo = new JComboBox<String>(custCtrl.getRanks());
		}

		rankPanel.add(rankCombo);
		panel.add(rankPanel);

		// Dojo
		JPanel dojoPanel = new JPanel();
		dojoPanel.add(new JLabel("Dojo:"));
		// A Combobox kezdő érték készletét megadjuk
		if (statusCombo.getSelectedItem().equals("DOJO")) {
			dojoCombo = new JComboBox<String>(new String[] { "NONE" });
		} else {
			dojoCombo = new JComboBox<String>(custCtrl.getDojos());
		}

		dojoPanel.add(dojoCombo);
		panel.add(dojoPanel);

		// Reagálni kell ,ha státuszban állítanak az érték készlet változzon
		statusCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String[] rankList = null;
				String[] dojoList = null;
				if (statusCombo.getSelectedItem().equals("DOJO")) {
					rankList = new String[] { "NONE" };
					dojoList = new String[] { "NONE" };
				} else {
					rankList = custCtrl.getRanks();
					dojoList = custCtrl.getDojos();
				}

				rankCombo.setModel(new DefaultComboBoxModel<>(rankList));
				dojoCombo.setModel(new DefaultComboBoxModel<>(dojoList));
			}
		});

		// Születési dátum
		JPanel birthDatePanel = new JPanel();
		birthDatePanel.add(new JLabel("Születési dátum:"));
		JPanel datePicPanel = new JPanel();
		birthDatePanel.add(datePicPanel);
		JTextField yearField = new JTextField(4);
		datePicPanel.add(yearField);
		datePicPanel.add(new JLabel("."));
		JTextField monthField = new JTextField(2);
		datePicPanel.add(monthField);
		datePicPanel.add(new JLabel("."));
		JTextField dayField = new JTextField(2);
		datePicPanel.add(dayField);
		datePicPanel.add(new JLabel("."));
		panel.add(birthDatePanel);

		// Email cím
		JPanel emailPanel = new JPanel();
		emailPanel.add(new JLabel("E-mail cím:"));
		JTextField emailField = new JTextField(20);
		emailPanel.add(emailField);
		panel.add(emailPanel);

		// Passzív
		JPanel passiveCheckPanel = new JPanel();
		passiveCheckPanel.add(new JLabel("Passzív:"));
		JCheckBox passive = new JCheckBox();
		passiveCheckPanel.add(passive);
		panel.add(passiveCheckPanel);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Adatok feldolgozása
				String name = nameField.getText();
				CustomerStatus status = CustomerStatus.valueOf(String.valueOf(statusCombo.getSelectedItem()));
				CustomerRank rank = CustomerRank.valueOf(String.valueOf(rankCombo.getSelectedItem()));
				String email = emailField.getText();
				int dojoId = dBc.searchDojoIdByName(String.valueOf(dojoCombo.getSelectedItem()));
				LocalDate bDate = null;
				try {
					bDate = LocalDate.of(Integer.valueOf(yearField.getText()), Integer.valueOf(monthField.getText()),
							Integer.valueOf(dayField.getText()));
				} catch (DateTimeException exc) {
					System.out.println("Rossz születési dátum lett megadva!");
				}

				boolean pass = passive.isSelected();

				if (bDate != null) {
					custCtrl.createCustomer(name, status, rank, dojoId, bDate, email, pass);
					CreateCustView.this.dispose();
				} else {
					String errMess = "Hibás születési adatok az űrlapon!";
					//TODO
					System.out.println(errMess);
				}
			}
		});
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				CreateCustView.this.dispose();
			}
		});
		JPanel buttonPanel = new JPanel();
		buttonPanel.add(okButton);
		buttonPanel.add(cancelButton);
		this.add(panel, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);
		this.pack();
		this.setVisible(true);

	}
}
