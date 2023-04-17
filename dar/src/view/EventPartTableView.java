package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;

import controller.DbController;
import model.Customer;
import model.Participant;

public class EventPartTableView extends JPanel{
	private DbController dBc = new DbController();
	private JTable table;
	private JButton addBtn;
	private JButton removeBtn;
	
	public EventPartTableView(int EventId) {
		ArrayList<Participant> participants = dBc.searchParticipantByEventId(EventId);
		ArrayList<Customer> customers = new ArrayList<Customer>();
		
		for(Participant p :participants) {
			Customer cust = dBc.searchCustomerById(p.getCustomerId());
			customers.add(cust);
		}
		
		String[] colNames = {"Név", "Fokozat", "Dojo", "Email cím"};
		Object[][] data = new Object[customers.size()][4];
		for(int i = 0; i < customers.size(); i ++) {
			data[i][0] = customers.get(i).getName();
			data[i][1] = customers.get(i).getRank();
			
			if(customers.get(i).getDojoId() > 0 ) {
				data[i][2] = dBc.searchCustomerById(customers.get(i).getDojoId()).getName();
			} else {
				data[i][2] = customers.get(i).getName();
			}
			
			data[i][3] = customers.get(i).getEmail();
		}
		
		table = new JTable(data, colNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        addBtn = new JButton("Résztvevő hozzáadása...");
        addBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Itt kell meghívni az ügyfél választó képernyőt.
					
 				
			}
		});
        
        removeBtn = new JButton("Résztvevő eltávolítása...");
        removeBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow != -1) {
					//TODO
					
 				}
			}
		});
        
        JScrollPane scrollPane = new JScrollPane(table);
        setLayout(new BorderLayout());
        add(scrollPane, BorderLayout.CENTER);
        add(addBtn, BorderLayout.WEST);
        add(removeBtn, BorderLayout.EAST);
	}
}
