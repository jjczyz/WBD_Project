package wbdpackage;


import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
/**
 * Ramka reprezentuj¹ca tabelê z list¹ pacjentów
 * @author Konrad
 *
 */
public class TablePanel extends JPanel{
	
	public JTable table;
	private DefaultTableModel model;
	private int selectedRow = -1;
	
	public TablePanel(String[] tableStrings) {
		
		model = new DefaultTableModel() {		    
		    @Override
		    public boolean isCellEditable(int row, int column) {
		       return false;
		    }
		};
		
		table = new JTable(model);
		model.addColumn("ID"); 
		for(int i=0; i<tableStrings.length; i++){
			model.addColumn(tableStrings[i]);
		}
		
		table.setPreferredScrollableViewportSize(new Dimension(450,600));
		table.setFillsViewportHeight(true);
		if (table.getPreferredSize().width < table.getPreferredScrollableViewportSize().getWidth()) {
            table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        } else {
            table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        }

		
	    ListSelectionModel cellSelectionModel = table.getSelectionModel();
	    cellSelectionModel.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

	    cellSelectionModel.addListSelectionListener(new ListSelectionListener() {
		@Override
			public void valueChanged(ListSelectionEvent e) {
				selectedRow = table.getSelectedRow();
			}
	    });
	    
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 5;
		add(new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED), c);
	}
	
	/**
	 * Podczas edytowania pacjenta aktualnie zaznaczony rz¹d jest usuwany, a na jego miejsce
	 * wprowadzany jest nowy (edytowany) pacjent
	 * @param patient
	 */
	public void editRow(){
		int rowToEdit = selectedRow;
		model.removeRow(rowToEdit);
		//model.insertRow(rowToEdit, getPatientDataToTable(patient));
	}
	
	/**
	 * Usuwany jest rz¹d z zaznaczonym aktualnie pacjentem
	 */
	public void deleteRow(){
		model.removeRow(selectedRow);
		//deleteButton.setEnabled(false);
	}
	
	/**
	 * Zwraca atrybut ID z zaznaczonego rzedu
	 * @return atrybut ID danej encji
	 */
	public int getSelectedRowID(){
		if(selectedRow == -1)
			return selectedRow;
		else{
			return (int) model.getValueAt(selectedRow, 0);
		}	
	}
	
	public int getSelectedRow(){
		return selectedRow;
	}
	
	public void clearSelection(){
		table.clearSelection();
	}
	
	/**
	 * Funkcja aktualizuje tabele. Usuwa star¹ zawartoœc i tworzy now¹
	 * @param dataObjectList Lista tablic obiektów do za³adowania w tebele. Jedna tablica to jeden rz¹d.
	 */
	public void updateTable(List<Object[]> dataObjectList){
		//czyszczenie tablicy
		int rowCount = model.getRowCount();
		for(int i = rowCount - 1; i >= 0; i--){
			model.removeRow(i);
			
		}
		//wypelnianie na nowo tablicy
		for(Object[] rowData: dataObjectList){
			model.addRow(rowData);
		}
	}

	public Object[] getDataFromSelectedRow(){
		int columnCount = model.getColumnCount();
		Object[] dataRow = new Object[columnCount - 1];
		for(int i = 1; i < columnCount; i++)
			dataRow[i-1] = model.getValueAt(selectedRow, i);
		return dataRow;
	}
	
	public DefaultTableModel getModel()
	{
		return model;
	}
	
	public void selectRow(int i)
	{
		table.setRowSelectionInterval(i, i);
	}
}

