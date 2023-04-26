package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class CustomerSearchView  extends JFrame{
	
	public CustomerSearchView() {
		super("Ügyfél keresés");
		
		setLayout(new BorderLayout());
		
		add(new CustomerSearchPanel(), BorderLayout.NORTH);
		
		
		setSize(800, 400);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
		setVisible(true);
	}
}
