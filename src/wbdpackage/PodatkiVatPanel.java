package wbdpackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class PodatkiVatPanel extends JPanel implements ActionListener, MouseListener {
	private JButton addButton, saveButton, cancelButton, deleteButton;
	private JTextField wartoscField;
	private TablePanel tablePanel;
	private SearchPanel searchPanel;
	
	private String[] tableStrings = {"Wartosc podatku"};
	
	private KawiarniaBAZA kawiarniaBAZA;
	
	public PodatkiVatPanel(){
		
		kawiarniaBAZA = new KawiarniaBAZA();
		
		tablePanel = new TablePanel(tableStrings);
		
		addButton = new JButton("Dodaj nowe");
		saveButton = new JButton("Zapisz");
		cancelButton = new JButton("Anuluj");
		deleteButton = new JButton("Usuñ");
		
		wartoscField = new JTextField();
		
		searchPanel = new SearchPanel(tableStrings,tablePanel);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("Wartosc podatku:"), c);
		c.gridx = 1;
		c.gridy = 0;
		add(wartoscField, c);
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
		float wartosc = Float.parseFloat(wartoscField.getText());
		
		kawiarniaBAZA.insertPodatkiVat(wartosc);
	}
	
	/**
	 * Aktualizuje tabele zgodnie z tym co jest aktualnie w bazie danych
	 */
	private void updateTable(){
		List<PodatkiVat> podatkiList = kawiarniaBAZA.selectPodatkiVat();
		
		List<Object[]> podatkiObjectList = new LinkedList<Object[]>();
		
		for(PodatkiVat podatek: podatkiList){
			Object[] dataObjects = new Object[2];
			dataObjects[0] = podatek.getIdPodatku();
			dataObjects[1] = podatek.getWartosc();
			podatkiObjectList.add(dataObjects);
		}
		tablePanel.updateTable(podatkiObjectList);
	}
	
	private void updateSelectedRowInDataBase(){
		int id = tablePanel.getSelectedRowID();
		float wartosc = Float.parseFloat(wartoscField.getText());
		
		kawiarniaBAZA.updatePodatkiVat(id, wartosc);
	}
	
	/**
	 * Usuwanie rekordu z bazy danych. tablePanel zwraca atrybut ID z zaznaczonego rzêdu.
	 */
	private void deleteSelectedRowFromDataBase(){
		int id = tablePanel.getSelectedRowID();
		kawiarniaBAZA.deleteRow("Podatki_VAT", "ID_podatku", id);
	}
	
	private void loadForm(Object[] dataRow){
		wartoscField.setText(Float.toString((float) dataRow[0]));
	}

	private void clearForm(){
		wartoscField.setText(null);
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