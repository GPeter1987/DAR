package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame implements ActionListener {

	private static final long serialVersionUID = -1164195157399312365L;

	public MainFrame() {
		super("Dojo Adminisztrációr Rendszer");

		setLayout(new BorderLayout());

		// Menu
		setJMenuBar(createMainMenu());

		// Table
		add(new EventTableView(), BorderLayout.CENTER);

		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}

	public JMenuBar createMainMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu eventMenu = new JMenu("Esemény");
		JMenuItem newEvent = new JMenuItem("Új Események...");
		newEvent.addActionListener(this);

		JMenuItem events = new JMenuItem("Események...");
		events.addActionListener(this);

		JMenu customerMenu = new JMenu("Ügyfél");
		JMenuItem newCustomer = new JMenuItem("Új ügyfél...");
		newCustomer.addActionListener(this);

		JMenuItem modCustomer = new JMenuItem("Ügyfél módosítása...");
		modCustomer.addActionListener(this);

		JMenuItem listCustomer = new JMenuItem("Ügyfél lista...");
		listCustomer.addActionListener(this);

		JMenu accountMenu = new JMenu("Számlák");
		JMenuItem listAccounts = new JMenuItem("Számlák megtekintése...");
		listAccounts.addActionListener(this);

		JMenuItem selectAccount = new JMenuItem("Számla kiválasztása...");
		selectAccount.addActionListener(this);

		menuBar.add(eventMenu);
		eventMenu.add(newEvent);
		eventMenu.add(events);

		menuBar.add(customerMenu);
		customerMenu.add(newCustomer);
		customerMenu.add(modCustomer);
		customerMenu.add(listCustomer);

		menuBar.add(accountMenu);
		accountMenu.add(listAccounts);
		accountMenu.add(selectAccount);

		return menuBar;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		String command = e.getActionCommand();

		if (command.equals("Új ügyfél...")) {
			new CreateCustView(this);
		} else if (command.equals("Ügyfél módosítása...")) {
			// TODO
		} else if (command.equals("Ügyfél lista...")) {
			// TODO
		} else if (command.equals("Számlák megtekintése...")) {
			// TODO
		} else if (command.equals("Számla kiválasztása...")) {
			// TODO
		} else if (command.equals("Új Események...")) {
			// TODO
		} else if (command.equals("Események...")) {
			// TODO
		}

	}

}
