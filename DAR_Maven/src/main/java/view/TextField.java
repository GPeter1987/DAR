package view;

import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.TextFieldType;
import controller.CustomerController;

public class TextField extends JPanel {

	private JTextField field;
	private JCheckBox chkBox;
	private JComboBox<String> comboBox;

	public TextField(String label, TextFieldType type) {
		CustomerController custCtrl = new CustomerController();

		setLayout(new GridLayout(1, 2));
		add(new JLabel(label));

		switch (type) {
		case TEXT:
			field = new JTextField();
			add(field);
			break;
		case CHKBOX:
			chkBox = new JCheckBox();
			add(chkBox);
			break;
		case STATUS:
			comboBox = new JComboBox<String>(custCtrl.getStatuses());
			add(comboBox);
			break;
		case RANK:
			comboBox = new JComboBox<String>(custCtrl.getRanks());
			add(comboBox);
			break;
		}

		setVisible(true);
	}

	public JTextField getField() {
		return field;
	}

	public void setField(JTextField field) {
		this.field = field;
	}

	public JCheckBox getChkBox() {
		return chkBox;
	}

	public void setChkBox(JCheckBox chkBox) {
		this.chkBox = chkBox;
	}

	public String getFieldText() {
		return this.field.getText();
	}

	public void setFieldText(String text) {
		this.field.setText(text);
	}

	public boolean isChecked() {
		return this.chkBox.isSelected();
	}

	public void setChecked(boolean check) {
		this.chkBox.setSelected(check);
	}

	public JComboBox<String> getCombo() {
		return this.comboBox;
	}

	public void setCombo(String value) {
		this.comboBox.setSelectedItem(value);
	}
}
