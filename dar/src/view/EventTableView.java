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
import controller.TableController;
import model.Event;

public class EventTableView extends JPanel {

	private DbController dBc = new DbController();
	private TableController cT = new TableController();
	private JTable table;
	private JButton modBtn;

	public EventTableView() {
		ArrayList<Event> events = dBc.searcAllEvent();

		table = cT.createEventTable(events);
		table.setPreferredScrollableViewportSize(new Dimension(500, 70));
		table.setFillsViewportHeight(true);

		ListSelectionModel selectionModel = table.getSelectionModel();
		selectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

		modBtn = new JButton("Módosítás...");
		modBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int selectedRow = table.getSelectedRow();
				if (selectedRow != -1) {
					// TODO Itt kell meghívni a módosíó képernyőt és elvégezni a módosítást.

				}

			}
		});

		JScrollPane scrollPane = new JScrollPane(table);
		setLayout(new BorderLayout());
		add(scrollPane, BorderLayout.CENTER);
		add(modBtn, BorderLayout.WEST);
		// TODO
		add(new EventPartTableView(1), BorderLayout.SOUTH);
	}

}
