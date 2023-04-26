package view;

import java.awt.FlowLayout;
import java.time.LocalDate;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DatePanel extends JPanel {

	private JTextField year;
	private JTextField month;
	private JTextField day;

	public DatePanel(String label) {
		year = new JTextField(4);
		month = new JTextField(2);
		day = new JTextField(2);

		setLayout(new FlowLayout());

		add(new JLabel(label + " (yyyy.mm.dd)"));
		add(year);
		add(new JLabel("."));
		add(month);
		add(new JLabel("."));
		add(day);

	}

	public JTextField getYear() {
		return year;
	}

	public void setYear(JTextField year) {
		this.year = year;
	}

	public JTextField getMonth() {
		return month;
	}

	public void setMonth(JTextField month) {
		this.month = month;
	}

	public JTextField getDay() {
		return day;
	}

	public void setDay(JTextField day) {
		this.day = day;
	}
	
	public LocalDate getDate() {
		return LocalDate.of(Integer.valueOf(this.year.getText()), Integer.valueOf(this.month.getText()), Integer.valueOf(this.day.getText()));
	}

}
