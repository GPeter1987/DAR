package view;

import java.awt.BorderLayout;

import javax.swing.JFrame;

public class CustomerView extends JFrame{
	
	public CustomerView() {
		super("Ügyfél áttekintő");
		
		setLayout(new BorderLayout());
		
		add(new CusDataPanel(), BorderLayout.NORTH);
		
		setSize(800, 400);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setVisible(true);
		setVisible(true);
	}

}
