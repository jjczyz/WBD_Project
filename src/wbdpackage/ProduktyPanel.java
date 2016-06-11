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

public class ProduktyPanel extends JPanel implements ActionListener, MouseListener {

	private JButton addButton, saveButton, cancelButton, deleteButton;
	private JTextField idKawiarniField, nazwaField, idPodatkuField, cenaField;
	private TablePanel tablePanel;
	private SearchPanel searchPanel;
	
	private String[] tableStrings = {"idKawiarni", "Nazwa", "idPodatku", "Cena"};
	
	private KawiarniaBAZA kawiarniaBAZA;
	
	public ProduktyPanel(){
		
		kawiarniaBAZA = new KawiarniaBAZA();
		
		tablePanel = new TablePanel(tableStrings);
		
		addButton = new JButton("Dodaj nowe");
		saveButton = new JButton("Zapisz");
		cancelButton = new JButton("Anuluj");
		deleteButton = new JButton("Usuñ");
		
		idKawiarniField = new JTextField();
		nazwaField = new JTextField();
		idPodatkuField = new JTextField();
		cenaField = new JTextField();
		
		searchPanel = new SearchPanel(tableStrings,tablePanel);
		
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
		add(new JLabel("Nazwa produktu:"), c);
		c.gridx = 1;
		c.gridy = 1;
		add(nazwaField, c);
		c.gridx = 0;
		c.gridy = 2;
		add(new JLabel("idPodatku:"), c);
		c.gridx = 1;
		c.gridy = 2;
		add(idPodatkuField, c);
		c.gridx = 0;
		c.gridy = 3;
		add(new JLabel("Cena:"), c);
		c.gridx = 1;
		c.gridy = 3;
		add(cenaField, c);
		c.gridx = 0;
		c.gridy = 4;
		add(saveButton, c);
		c.gridx = 1;
		c.gridy = 4;
		add(cancelButton, c);
		c.gridx = 0;
		c.gridy = 5;
		add(addButton, c);
		c.gridx = 1;
		c.gridy = 5;
		add(deleteButton, c);
		c.gridx = 0;
		c.gridy = 6;
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
		String nazwa = nazwaField.getText();
		float cena = Float.parseFloat(cenaField.getText());
		int idPodatku = Integer.parseInt(idPodatkuField.getText());
		
		kawiarniaBAZA.insertProdukty(nazwa, cena, idPodatku);
	}
	
	/**
	 * Aktualizuje tabele zgodnie z tym co jest aktualnie w bazie danych
	 */
	private void updateTable(){
		List<Produkty> produktyList = kawiarniaBAZA.selectProdukty();
		
		List<Object[]> produktyObjectList = new LinkedList<Object[]>();
		
		for(Produkty produkt: produktyList){
			Object[] dataObjects = new Object[5];
			dataObjects[0] = produkt.getIdProduktu();
			dataObjects[1] = produkt.getIdKawiarni();
			dataObjects[2] = produkt.getNazwa();
			dataObjects[3] = produkt.getIdPodatku();
			dataObjects[4] = produkt.getCena();
			produktyObjectList.add(dataObjects);
		}
		tablePanel.updateTable(produktyObjectList);
	}
	
	private void updateSelectedRowInDataBase(){
		int id = tablePanel.getSelectedRowID();		
		String nazwa = nazwaField.getText();
		float cena = Float.parseFloat(cenaField.getText());
		int idPodatku = Integer.parseInt(idPodatkuField.getText());
		
		kawiarniaBAZA.updateProdukty(id, nazwa, cena, idPodatku);
	}
	
	/**
	 * Usuwanie rekordu z bazy danych. tablePanel zwraca atrybut ID z zaznaczonego rzêdu.
	 */
	private void deleteSelectedRowFromDataBase(){
		int id = tablePanel.getSelectedRowID();
		kawiarniaBAZA.deleteRow("Produkty", "ID_produktu", id);
	}
	
	private void loadForm(Object[] dataRow){
		idKawiarniField.setText(Integer.toString((int) dataRow[0]));
		nazwaField.setText((String) dataRow[1]);
		idPodatkuField.setText(Integer.toString((int) dataRow[2]));
		cenaField.setText(Float.toString((float) dataRow[3]));
	}

	private void clearForm(){
		idKawiarniField.setText(null);
		nazwaField.setText(null);
		idPodatkuField.setText(null);
		cenaField.setText(null);
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