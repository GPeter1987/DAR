package view;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.Border;

import model.TextFieldType;

public class CusDataPanel extends JPanel {
	private TextField cusId;
	private TextField cusName;
	private TextField status;
	private TextField rank;
	private TextField dojoName;
	private DatePanel birthDate;
	private TextField accountId;
	private TextField email;
	private TextField passive;

	public CusDataPanel() {
		setLayout(new GridLayout(3, 3));

		cusId = new TextField("ID:", TextFieldType.TEXT);
		cusName = new TextField("Név:", TextFieldType.TEXT);
		status = new TextField("Státusz:", TextFieldType.STATUS);
		rank = new TextField("Rang:", TextFieldType.RANK);
		dojoName = new TextField("Dojo:", TextFieldType.TEXT);
		birthDate = new DatePanel("Születési dátum:");
		accountId = new TextField("Számla száma:", TextFieldType.TEXT);
		email = new TextField("E-mail:", TextFieldType.TEXT);
		passive = new TextField("Passzív:", TextFieldType.CHKBOX);

		this.add(cusId);
		this.add(cusName);
		this.add(status);
		this.add(rank);
		this.add(dojoName);
		this.add(birthDate);
		this.add(accountId);
		this.add(email);
		this.add(passive);

		Border innerBorder = BorderFactory.createLineBorder(Color.gray);
		Border outerBorder = BorderFactory.createLineBorder(Color.black);
		Border border = BorderFactory.createCompoundBorder(innerBorder, outerBorder);
		this.setBorder(border);

		setVisible(true);
	}

	public TextField getCusId() {
		return cusId;
	}

	public void setCusId(TextField cusId) {
		this.cusId = cusId;
	}

	public TextField getCusName() {
		return cusName;
	}

	public void setCusName(TextField cusName) {
		this.cusName = cusName;
	}

	public TextField getStatus() {
		return status;
	}

	public void setStatus(TextField status) {
		this.status = status;
	}

	public TextField getRank() {
		return rank;
	}

	public void setRank(TextField rank) {
		this.rank = rank;
	}

	public TextField getDojoName() {
		return dojoName;
	}

	public void setDojoName(TextField dojoName) {
		this.dojoName = dojoName;
	}

	public DatePanel getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(DatePanel birthDate) {
		this.birthDate = birthDate;
	}

	public TextField getAccountId() {
		return accountId;
	}

	public void setAccountId(TextField accountId) {
		this.accountId = accountId;
	}

	public TextField getEmail() {
		return email;
	}

	public void setEmail(TextField email) {
		this.email = email;
	}

	public TextField getPassive() {
		return passive;
	}

	public void setPassive(TextField passive) {
		this.passive = passive;
	}

}
