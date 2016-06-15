package wbdpackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class KontaPanel extends JPanel implements ActionListener, MouseListener {
	
	private JButton addButton, saveButton, cancelButton, deleteButton;
	private JTextField loginField, hasloField, typKontaField;
	private TablePanel tablePanel;
	private SearchPanel searchPanel;
	
	private KawiarniaBAZA kawiarniaBAZA;
	
	private String[] tableStrings = {"Login", "Has³o", "Typ konta"};
	
	public KontaPanel(){
		
		kawiarniaBAZA = new KawiarniaBAZA();
		
		tablePanel = new TablePanel(tableStrings);
		
		addButton = new JButton("Dodaj nowe");
		saveButton = new JButton("Zapisz");
		cancelButton = new JButton("Anuluj");
		deleteButton = new JButton("Usuñ");
		
		loginField = new JTextField();
		hasloField = new JTextField();
		typKontaField = new JTextField();
		
		searchPanel = new SearchPanel(tableStrings,tablePanel);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.insets = new Insets(5,5,5,5);
		
		c.gridx = 0;
		c.gridy = 0;
		add(new JLabel("Login:"), c);
		c.gridx = 1;
		c.gridy = 0;
		add(loginField, c);
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("Has³o:"), c);
		c.gridx = 1;
		c.gridy = 1;
		add(hasloField, c);
		c.gridx = 0;
		c.gridy = 2;
		add(new JLabel("Typ konta:"), c);
		c.gridx = 1;
		c.gridy = 2;
		add(typKontaField, c);
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
		String login = loginField.getText();
		String haslo = hasloField.getText();
		int typKonta = Integer.parseInt(typKontaField.getText());
		
		kawiarniaBAZA.insertKontaFirmowe(login, haslo, typKonta);
	}
	
	/**
	 * Aktualizuje tabele zgodnie z tym co jest aktualnie w bazie danych
	 */
	private void updateTable(){
		List<KontaFirmowe> kontaList = kawiarniaBAZA.selectKontaFirmowe();
		
		List<Object[]> kontaObjectList = new LinkedList<Object[]>();
		
		for(KontaFirmowe konto: kontaList){
			Object[] dataObjects = new Object[4];
			dataObjects[0] = konto.getIdKonta();
			dataObjects[1] = konto.getLogin();
			dataObjects[2] = konto.getPassword();
			dataObjects[3] = konto.getAccountType();
			kontaObjectList.add(dataObjects);
		}
		tablePanel.updateTable(kontaObjectList);
	}
	
	private void updateSelectedRowInDataBase(){
		int id = tablePanel.getSelectedRowID();
		String login = loginField.getText();
		String haslo = hasloField.getText();
		String typKonta = typKontaField.getText();
		
		kawiarniaBAZA.updateKontaFirmowe(id, login, haslo, typKonta);
	}
	
	/**
	 * Usuwanie rekordu z bazy danych. tablePanel zwraca atrybut ID z zaznaczonego rzêdu.
	 */
	private void deleteSelectedRowFromDataBase(){
		int id = tablePanel.getSelectedRowID();
		kawiarniaBAZA.deleteRow("Konta_firmowe", "ID_konta", id);
	}
	
	private void loadForm(Object[] dataRow){
		loginField.setText((String) dataRow[0]);
		hasloField.setText((String) dataRow[1]);
		typKontaField.setText((String) dataRow[2]);
	}

	private void clearForm(){
		loginField.setText(null);
		hasloField.setText(null);
		typKontaField.setText(null);
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
