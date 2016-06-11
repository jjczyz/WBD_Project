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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.SpinnerModel;
import javax.swing.table.DefaultTableModel;

public class WynagrodzeniaPanel extends JPanel implements ActionListener, MouseListener{
	
	private JButton addButton, saveButton, cancelButton, deleteButton;
	private JTextField idPracownikaField, kwotaField;
	private TablePanel tablePanel;
	private JSpinner calendar;
	
	private SearchPanel searchPanel;
	
	private KawiarniaBAZA kawiarniaBAZA;
	
	private String[] tableStrings = {"idPracownika", "Data wyp³aty", "Kwota"};
	
	public WynagrodzeniaPanel(){
		
		kawiarniaBAZA = new KawiarniaBAZA();
		
		tablePanel = new TablePanel(tableStrings);
		
		searchPanel = new SearchPanel(tableStrings,tablePanel);
		
		addButton = new JButton("Dodaj nowe");
		saveButton = new JButton("Zapisz");
		cancelButton = new JButton("Anuluj");
		deleteButton = new JButton("Usuñ");
		
		idPracownikaField = new JTextField();
		kwotaField = new JTextField();
		
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
		add(new JLabel("idPracownika:"), c);
		c.gridx = 1;
		c.gridy = 0;
		add(idPracownikaField, c);
		c.gridx = 0;
		c.gridy = 1;
		add(new JLabel("Kwota:"), c);
		c.gridx = 1;
		c.gridy = 1;
		add(kwotaField, c);
		c.gridx = 0;
		c.gridy = 2;
		add(new JLabel("Data wyp³aty:"), c);
		c.gridx = 1;
		c.gridy = 2;
		add(calendar, c);
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
		c.gridwidth = 1;
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
		if(checkField(kwotaField)&&checkField(idPracownikaField))
		{
		Date value = (Date)calendar.getValue();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(value);
		float kwota = Float.parseFloat(kwotaField.getText());
		int idPracownika = Integer.parseInt(idPracownikaField.getText());	
		kawiarniaBAZA.insertWynagrodzenia(data, kwota, idPracownika);
		}
	}
	
	/**
	 * Aktualizuje tabele zgodnie z tym co jest aktualnie w bazie danych
	 */
	private void updateTable(){
		List<Wynagrodzenia> wynagrodzeniaList = kawiarniaBAZA.selectWynagrodzenia();
		
		List<Object[]> wynagrodzeniaObjectList = new LinkedList<Object[]>();
		
		for(Wynagrodzenia wynagrodzenie: wynagrodzeniaList){
			Object[] dataObjects = new Object[4];
			dataObjects[0] = wynagrodzenie.getIdWynagrodzenia();
			dataObjects[1] = wynagrodzenie.getIdPracownika();
			dataObjects[2] = wynagrodzenie.getDataWyplaty();
			dataObjects[3] = wynagrodzenie.getKwota();
			wynagrodzeniaObjectList.add(dataObjects);
		}
		tablePanel.updateTable(wynagrodzeniaObjectList);
	}
	
	private void updateSelectedRowInDataBase(){
		int id = tablePanel.getSelectedRowID();
		float kwota = Float.parseFloat(kwotaField.getText());
		Date value = (Date)calendar.getValue();
		String data = new SimpleDateFormat("dd/MM/yyyy").format(value);
		int idPracownika = Integer.parseInt(idPracownikaField.getText());
		
		kawiarniaBAZA.updateWynagrodzenia(id, data, kwota, idPracownika);
	}
	
	/**
	 * Usuwanie rekordu z bazy danych. tablePanel zwraca atrybut ID z zaznaczonego rzêdu.
	 */
	private void deleteSelectedRowFromDataBase(){
		int id = tablePanel.getSelectedRowID();
		kawiarniaBAZA.deleteRow("Wynagrodzenia", "ID_wynagrodzenia", id);
	}
	
	private void loadForm(Object[] dataRow){
		idPracownikaField.setText(Integer.toString((int) dataRow[0]));
		//calendar.setValue(dataRow[1]);
		kwotaField.setText(Float.toString((float) dataRow[2]));
	}

	private void clearForm(){
		idPracownikaField.setText(null);
		kwotaField.setText(null);
		calendar.setValue(new Date());
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
	public boolean checkField(JTextField f)
	{
		if (f.getText() == null || f.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(this, "Wszystkie pola powinny byæ uzupe³nione");
			return false;
		}
		return true;
	}

	
}
