package view;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CustomerController;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class CreateCustView extends JDialog {

	private CustomerController custCtrl = new CustomerController();
	private JComboBox<String> rankCombo = new JComboBox<String>();

	public CreateCustView(JFrame frame) {
		super(frame, "Ügyfél létrehozása", true);
		setModalityType(ModalityType.APPLICATION_MODAL);
		setModalExclusionType(ModalExclusionType.APPLICATION_EXCLUDE);

		/*
		 * String name, CustomerStatus status, CustomerRank rank, int dojoId, LocalDate
		 * birthDate, int accountId, String email, Boolean passive
		 */
		// Név
		JPanel panel = new JPanel(new GridLayout(8, 2));
		panel.add(new JLabel("Név:"));
		JTextField nameField = new JTextField();
		panel.add(nameField);

		// Státusz
		panel.add(new JLabel("Státusz:"));
		JComboBox<String> statusCombo = new JComboBox<String>(custCtrl.getStatuses());
		panel.add(statusCombo);

		// Rang
		panel.add(new JLabel("Rang:"));
		// A Combobox kezdő érték készletét megadjuk
		if (statusCombo.getSelectedItem().equals("DOJO")) {
			rankCombo = new JComboBox<String>(new String[] { "NONE" });
		} else {
			rankCombo = new JComboBox<String>(custCtrl.getRanks());
		}

		// Reagálni kell ,ha státuszban állítanak az érték készlet változzon
		statusCombo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				String[] arr = null;
				if (statusCombo.getSelectedItem().equals("DOJO")) {
					arr = new String[] { "NONE" };
				} else {
					arr = custCtrl.getRanks();
				}

				rankCombo.setModel(new DefaultComboBoxModel<>(arr));
			}
		});

		panel.add(rankCombo);

		// Dojo
		panel.add(new JLabel("Dojo:"));
		JComboBox<String> dojoCombo = new JComboBox<>(custCtrl.getDojos());
		panel.add(dojoCombo);

		// Születési dátum
		panel.add(new JLabel("Születési dátum:"));
		
		
		// Email cím
		panel.add(new JLabel("E-mail cím:"));
		JTextField emailField = new JTextField();
		panel.add(emailField);
		
		// Passzív
		panel.add(new JLabel("Passzív:"));
		JCheckBox passive = new JCheckBox();
		panel.add(passive);

		JButton okButton = new JButton("OK");
		okButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// Adatok feldolgozása
				String name = nameField.getText();
				String description = emailField.getText();
				// TODO: adatok mentése
				CreateCustView.this.dispose();
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
