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
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;

public class PracownicyPanel extends JPanel implements ActionListener, MouseListener {
	
	private JButton addButton, saveButton, cancelButton, deleteButton;
	private JTextField imieField, nazwiskoField, kontoBankoweField, peselField, telefonField, emailField, ulicaField, miastoField, kodPocztowyField, krajField, nrDomuField, nrMieszkaniaField, idZnizkiField, idKawiarniField, idKontaField; 
	private TablePanel tablePanel;
	private SearchPanel searchPanel;
	private JSpinner calendar;

	
	private String[] tableStrings = {"Imiê", "Nazwisko", "Data zatrudnienia", "Konto bankowe", "PESEL", "Telefon", "E-mail", "Ulica", "Miasto", "Kod pocztowy", "Kraj", "Numer domu", "Numer mieszkania", "idZnizki", "idKawiarni", "idKonta"};
	
	private KawiarniaBAZA kawiarniaBAZA;
	
	public PracownicyPanel(){
		
		kawiarniaBAZA = new KawiarniaBAZA();
		
		tablePanel = new TablePanel(tableStrings);
		
		addButton = new JButton("Dodaj nowe");
		saveButton = new JButton("Zapisz");
		cancelButton = new JButton("Anuluj");
		deleteButton = new JButton("Usuñ");
		
		searchPanel = new SearchPanel(tableStrings,tablePanel);
		
		imieField = new JTextField();
		nazwiskoField = new JTextField();
		kontoBankoweField = new JTextField();
		peselField = new JTextField();
		emailField = new JTextField();
		telefonField = new JTextField();
		ulicaField = new JTextField();
		miastoField = new JTextField();
		kodPocztowyField = new JTextField();
		krajField = new JTextField();
		nrDomuField = new JTextField();
		nrMieszkaniaField = new JTextField();
		idZnizkiField = new JTextField();
		idKawiarniField = new JTextField();
		idKontaField = new JTextField();
		
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
		add(new JLabel("Imiê:"), c);
		c.gridx = 1;
		add(imieField, c);
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("Nazwisko:"), c);
		c.gridx = 1;
		add(nazwiskoField, c);
		c.gridx = 0;
		c.gridy = 2;
		add(new JLabel("Data zatrudnienia:"), c);
		c.gridx = 1;
		c.gridy = 2;
		add(calendar, c);
		c.gridx = 0;
		c.gridy = 3;
		add(new JLabel("Konto bankowe:"), c);
		c.gridx = 1;
		c.gridy = 3;
		add(kontoBankoweField, c);
		c.gridx = 0;
		c.gridy = 4;
		add(new JLabel("PESEL:"), c);
		c.gridx = 1;
		c.gridy = 4;
		add(peselField, c);
		c.gridx = 0;
		c.gridy = 5;
		add(new JLabel("Telefon:"), c);
		c.gridx = 1;
		c.gridy = 5;
		add(telefonField, c);
		c.gridx = 0;
		c.gridy = 6;
		add(new JLabel("E-mail:"), c);
		c.gridx = 1;
		c.gridy = 6;
		add(emailField, c);
		c.gridx = 0;
		c.gridy = 7;
		add(new JLabel("Ulica:"), c);
		c.gridx = 1;
		c.gridy = 7;
		add(ulicaField, c);
		c.gridx = 0;
		c.gridy = 8;
		add(new JLabel("Miasto:"), c);
		c.gridx = 1;
		c.gridy = 8;
		add(miastoField, c);
		c.gridx = 0;
		c.gridy = 9;
		add(new JLabel("Kod pocztowy:"), c);
		c.gridx = 1;
		c.gridy = 9;
		add(kodPocztowyField, c);
		c.gridx = 0;
		c.gridy = 10;
		add(new JLabel("Kraj:"), c);
		c.gridx = 1;
		c.gridy = 10;
		add(krajField, c);
		c.gridx = 0;
		c.gridy = 11;
		add(new JLabel("Numer domu:"), c);
		c.gridx = 1;
		c.gridy = 11;
		add(nrDomuField, c);
		c.gridx = 0;
		c.gridy = 12;
		add(new JLabel("Numer mieszkania:"), c);
		c.gridx = 1;
		c.gridy = 12;
		add(nrMieszkaniaField, c);
		c.gridx = 0;
		c.gridy = 13;
		add(new JLabel("idZnizki:"), c);
		c.gridx = 1;
		c.gridy = 13;
		add(idZnizkiField, c);
		c.gridx = 0;
		c.gridy = 14;
		add(new JLabel("idKawiarni:"), c);
		c.gridx = 1;
		c.gridy = 14;
		add(idKawiarniField, c);
		c.gridx = 0;
		c.gridy = 15;
		add(new JLabel("idKonta:"), c);
		c.gridx = 1;
		c.gridy = 15;
		add(idKontaField, c);
		
		c.gridx = 0;
		c.gridy = 16;
		add(saveButton, c);
		c.gridx = 1;
		c.gridy = 16;
		add(cancelButton, c);
		c.gridx = 0;
		c.gridy = 17;
		add(addButton, c);
		c.gridx = 1;
		c.gridy = 17;
		add(deleteButton, c);
		c.gridx = 2;
		c.gridy = 0;
		c.gridheight = 25;
		add(new JScrollPane(tablePanel), c);
		c.gridwidth = 2;
		c.gridx = 0;
		c.gridy = 18;
		add(searchPanel, c);
		
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
		int idZnizki = Integer.parseInt(idZnizkiField.getText());		
		String imie = imieField.getText();
		String nazwisko = nazwiskoField.getText();
		String kontoBankowe = kontoBankoweField.getText();
		String pesel = peselField.getText();
		String telefon = telefonField.getText();
		String email = emailField.getText();
		String ulica = ulicaField.getText();
		String miasto = miastoField.getText();
		int nrDomu = Integer.parseInt(nrDomuField.getText());
		int nrMieszkania = Integer.parseInt(nrMieszkaniaField.getText());
		String kodPocztowy = kodPocztowyField.getText();
		String kraj = krajField.getText();
		int idKonta = Integer.parseInt(idKontaField.getText());
		
		kawiarniaBAZA.insertPracownicy(imie, nazwisko, data, kontoBankowe, pesel, telefon, email, ulica, miasto, nrDomu, nrMieszkania, kodPocztowy, kraj, idZnizki, idKonta);
	}
	
	/**
	 * Aktualizuje tabele zgodnie z tym co jest aktualnie w bazie danych
	 */
	private void updateTable(){
		List<Pracownicy> pracownicyList = kawiarniaBAZA.selectPracownicy();
		
		List<Object[]> pracownicyObjectList = new LinkedList<Object[]>();
		
		for(Pracownicy pracownik: pracownicyList){
			Object[] dataObjects = new Object[17];
			dataObjects[0] = pracownik.getIdPracownika();
			dataObjects[1] = pracownik.getImie();
			dataObjects[2] = pracownik.getNazwisko();
			dataObjects[3] = pracownik.getDataZatrudnienia();
			dataObjects[4] = pracownik.getKontoBankowe();
			dataObjects[5] = pracownik.getPesel();
			dataObjects[6] = pracownik.getTelefon();
			dataObjects[7] = pracownik.getEmail();
			dataObjects[8] = pracownik.getUlica();
			dataObjects[9] = pracownik.getMiasto();
			dataObjects[10] = pracownik.getKodPocztowy();
			dataObjects[11] = pracownik.getKraj();
			dataObjects[12] = pracownik.getNrDomu();
			dataObjects[13] = pracownik.getNrMieszkania();
			dataObjects[14] = pracownik.getIdZnizki();
			dataObjects[15] = pracownik.getIdKawiarni();
			dataObjects[16] = pracownik.getIdKonta();
			
			pracownicyObjectList.add(dataObjects);
		}
		tablePanel.updateTable(pracownicyObjectList);
	}
	
	private void updateSelectedRowInDataBase(){
		int id = tablePanel.getSelectedRowID();
		Date value = (Date)calendar.getValue();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(value);
		int idZnizki = Integer.parseInt(idZnizkiField.getText());
		String imie = imieField.getText();
		String nazwisko = nazwiskoField.getText();
		String kontoBankowe = kontoBankoweField.getText();
		String pesel = peselField.getText();
		String telefon = telefonField.getText();
		String email = emailField.getText();
		String ulica = ulicaField.getText();
		String miasto = miastoField.getText();
		int nrDomu = Integer.parseInt(nrDomuField.getText());
		int nrMieszkania = Integer.parseInt(nrMieszkaniaField.getText());
		String kodPocztowy = kodPocztowyField.getText();
		String kraj = krajField.getText();
		int idKonta = Integer.parseInt(idKontaField.getText());
		
		kawiarniaBAZA.updatePracownicy(id, imie, nazwisko, data, kontoBankowe, pesel, telefon, email, ulica, miasto, nrDomu, nrMieszkania, kodPocztowy, kraj, idZnizki, idKonta);
	}
	
	/**
	 * Usuwanie rekordu z bazy danych. tablePanel zwraca atrybut ID z zaznaczonego rzêdu.
	 */
	private void deleteSelectedRowFromDataBase(){
		int id = tablePanel.getSelectedRowID();
		kawiarniaBAZA.deleteRow("Pracownicy", "ID_pracownika", id);
	}
	
	private void loadForm(Object[] dataRow){
		imieField.setText((String) dataRow[0]);
		nazwiskoField.setText((String) dataRow[1]);
		//calendar.setValue(dataRow[2]);
		kontoBankoweField.setText((String) dataRow[3]);
		peselField.setText((String) dataRow[4]);
		telefonField.setText((String) dataRow[5]);
		emailField.setText((String) dataRow[6]);
		ulicaField.setText((String) dataRow[7]);
		miastoField.setText((String) dataRow[8]);
		kodPocztowyField.setText((String) dataRow[9]);
		krajField.setText((String) dataRow[10]);
		nrDomuField.setText(Integer.toString((int) dataRow[11]));
		nrMieszkaniaField.setText(Integer.toString((int) dataRow[12]));
		idZnizkiField.setText(Integer.toString((int) dataRow[13]));
		idKawiarniField.setText(Integer.toString((int) dataRow[14]));
		idKontaField.setText(Integer.toString((int) dataRow[15]));
	}

	private void clearForm(){
		imieField.setText(null);
		nazwiskoField.setText(null);
		calendar.setValue(new Date());
		kontoBankoweField.setText(null);
		peselField.setText(null);
		telefonField.setText(null);
		emailField.setText(null);
		ulicaField.setText(null);
		miastoField.setText(null);
		kodPocztowyField.setText(null);
		krajField.setText(null);
		nrDomuField.setText(null);
		nrMieszkaniaField.setText(null);
		idZnizkiField.setText(null);
		idKawiarniField.setText(null);
		idKontaField.setText(null);
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