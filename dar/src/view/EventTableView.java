package view;

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
import model.Event;


public class EventTableView extends JPanel{
	
	private DbController dBc = new DbController();
	private JTable table;
	private JButton modBtn;
	
	public EventTableView() {
		String[] colNames = {"Esemény neve", " Esemény Típusa", "Esemény Dátuma", "Szervező"};
		ArrayList<Event> events = dBc.searcAllEvent();
		
		Object[][] data = new Object[events.size()][4];
		for(int i = 0; i < events.size(); i ++) {
			data[i][0] = events.get(i).getName();
			data[i][1] = events.get(i).getType();
			data[i][2] = events.get(i).getDate();
			data[i][3] = dBc.searchCustomerById(events.get(i).getDojoId()).getName();
		}
		
		table = new JTable(data, colNames);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
        
        ListSelectionModel selectionModel = table.getSelectionModel();
        selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        modBtn = new JButton("Módosítás...");
        modBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if(selectedRow != -1) {
					// ToDo Itt kell meghívni a módosíó képernyőt és elvégezni a módosítást.
					
 				}
				
			}
		});
        
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane);
        add(modBtn);
	}
	
	
}
