package wbdpackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.*;

public class FakturyPanel extends JPanel implements ActionListener, MouseListener {
	private JButton addButton, saveButton, cancelButton, deleteButton;
	private JTextField idKawiarniField, idPracownikaField, idZnizkiField, kwotaField;
	private TablePanel tablePanel;
	private SearchPanel searchPanel;
	private JSpinner calendar;
	
	private KawiarniaBAZA kawiarniaBAZA;
	
	private String[] tableStrings = {"idKawiarni", "idPracownika", "idZnizki", "Data", "Kwota"};
	
	public FakturyPanel(){
		
		kawiarniaBAZA = new KawiarniaBAZA();
		
		tablePanel = new TablePanel(tableStrings);
		
		addButton = new JButton("Dodaj nowe");
		saveButton = new JButton("Zapisz");
		cancelButton = new JButton("Anuluj");
		deleteButton = new JButton("Usuñ");
		
		idKawiarniField = new JTextField();
		idPracownikaField = new JTextField();
		idZnizkiField = new JTextField();
		kwotaField = new JTextField();
		
		searchPanel = new SearchPanel(tableStrings,tablePanel);
		
		SpinnerModel model = new SpinnerDateModel();
	    calendar = new JSpinner(model);
	    JComponent editor = new JSpinner.DateEditor(calendar, "dd/MM/yyyy");
	    calendar.setEditor(editor);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("idKawiarni:"), c);
		c.gridx = 1;
		c.gridy = 0;
		add(idKawiarniField, c);
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("idPracownika:"), c);
		c.gridx = 1;
		c.gridy = 1;
		add(idPracownikaField, c);
		c.gridx = 0;
		c.gridy = 2;
		add(new JLabel("idZnizki:"), c);
		c.gridx = 1;
		c.gridy = 2;
		add(idZnizkiField, c);
		c.gridx = 0;
		c.gridy = 3;
		add(new JLabel("Data:"), c);
		c.gridx = 1;
		c.gridy = 3;
		add(calendar, c);
		c.gridx = 0;
		c.gridy = 4;
		add(new JLabel("Kwota:"), c);
		c.gridx = 1;
		c.gridy = 4;
		add(kwotaField, c);
		c.gridx = 0;
		c.gridy = 5;
		add(saveButton, c);
		c.gridx = 1;
		c.gridy = 5;
		add(cancelButton, c);
		c.gridx = 0;
		c.gridy = 6;
		add(addButton, c);
		c.gridx = 1;
		c.gridy = 6;
		add(deleteButton, c);
		c.gridx = 0;
		c.gridy = 7;
		c.gridwidth = 2;
		add(searchPanel, c);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 10;
		add(new JScrollPane(tablePanel), c);
		
		addButton.addActionListener(this);
		cancelButton.addActionListener(this);
		saveButton.addActionListener(this);
		deleteButton.addActionListener(this);
		tablePanel.table.addMouseListener(this);
		
		this.updateTable();		
	}
	
	
	/**
	 * Dodawanie rekordu do bazy danych
	 */
	private void addToDataBase(){
		Date value = (Date)calendar.getValue();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(value);
		float kwota = Float.parseFloat(kwotaField.getText());
		int idPracownika = Integer.parseInt(idPracownikaField.getText());
		int idZnizki = Integer.parseInt(idZnizkiField.getText());
		
		kawiarniaBAZA.insertFaktury(data, kwota, idPracownika, idZnizki);
	}
	
	/**
	 * Aktualizuje tabele zgodnie z tym co jest aktualnie w bazie danych
	 */
	private void updateTable(){
		List<Faktury> fakturyList = kawiarniaBAZA.selectFaktury();
		
		List<Object[]> fakturyObjectList = new LinkedList<Object[]>();
		
		for(Faktury faktura: fakturyList){
			Object[] dataObjects = new Object[6];
			dataObjects[0] = faktura.getIdFaktury();
			dataObjects[1] = faktura.getIdKawiarni();
			dataObjects[2] = faktura.getIdPracownika();
			dataObjects[3] = faktura.getIdZnizki();
			dataObjects[4] = faktura.getData();
			dataObjects[5] = faktura.getKwota();
			fakturyObjectList.add(dataObjects);
		}
		tablePanel.updateTable(fakturyObjectList);
	}
	
	private void updateSelectedRowInDataBase(){
		int id = tablePanel.getSelectedRowID();
		float kwota = Float.parseFloat(kwotaField.getText());
		Date value = (Date)calendar.getValue();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(value);
		int idPracownika = Integer.parseInt(idPracownikaField.getText());
		int idZnizki = Integer.parseInt(idZnizkiField.getText());
		
		kawiarniaBAZA.updateFaktury(id, data, kwota, idPracownika, idZnizki);
	}
	
	/**
	 * Usuwanie rekordu z bazy danych. tablePanel zwraca atrybut ID z zaznaczonego rzêdu.
	 */
	private void deleteSelectedRowFromDataBase(){
		int id = tablePanel.getSelectedRowID();
		kawiarniaBAZA.deleteRow("Faktury", "ID_faktury", id);
	}
	
	private void loadForm(Object[] dataRow){
		idKawiarniField.setText(Integer.toString((int) dataRow[0]));
		idPracownikaField.setText(Integer.toString((int) dataRow[1]));
		idZnizkiField.setText(Integer.toString((int) dataRow[2]));
		//calendar.setValue(dataRow[3]);
		kwotaField.setText(Float.toString((float) dataRow[4]));
	}

	private void clearForm(){
		idPracownikaField.setText(null);
		kwotaField.setText(null);
		calendar.setValue(new Date());
		idZnizkiField.setText(null);
		idKawiarniField.setText(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()== addButton){
			this.addToDataBase();
			this.updateTable();
			this.clearForm();
		}
		
		else if(e.getSource() == cancelButton){
			this.clearForm();
			tablePanel.clearSelection();
		}
		
		else if(e.getSource() == deleteButton){
			this.deleteSelectedRowFromDataBase();
			this.updateTable();
			this.clearForm();
		}
		
		else if(e.getSource() == saveButton){
			if(tablePanel.getSelectedRow() > -1){
				this.updateSelectedRowInDataBase();
				this.updateTable();
				this.clearForm();
			}
		}
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent e) {
		if(tablePanel.getSelectedRow() > -1){
			this.loadForm(tablePanel.getDataFromSelectedRow());
		}
		
	}
	
	
}
