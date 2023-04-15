package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;

public class MainFrame extends JFrame {
	
	public MainFrame() {
		super("Dojo Adminisztrációr Rendszer");
		
		setLayout(new BorderLayout());
		
		// Menu
		setJMenuBar(createMainMenu());
		
		// Table
		
		setSize(600, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
	}
	
	public JMenuBar createMainMenu() {
		JMenuBar menuBar = new JMenuBar();
		JMenu eventMenu = new JMenu("Esemény");
		JMenuItem newEvent = new JMenuItem("Új Események...");
		JMenuItem events = new JMenuItem("Események...");
		JMenu customerMenu = new JMenu("Ügyfél");
		JMenuItem newCustomer = new JMenuItem("Új ügyfél...");
		JMenuItem modCustomer = new JMenuItem("Ügyfél módosítása...");
		JMenu accountMenu = new JMenu("Számlák");
		JMenuItem listAccounts = new JMenuItem("Számlák megtekintése");
		JMenuItem selectAccount = new JMenuItem("Számla kiválasztása...");
		
		menuBar.add(eventMenu);
		eventMenu.add(newEvent);
		eventMenu.add(events);
		
		menuBar.add(customerMenu);
		customerMenu.add(newCustomer);
		customerMenu.add(modCustomer);
		
		menuBar.add(accountMenu);
		accountMenu.add(listAccounts);
		accountMenu.add(selectAccount);
		
		return menuBar;
	}
}
