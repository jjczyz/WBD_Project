package wbdpackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class KontaPanel extends JPanel {
	
	private JButton addButton, saveButton, cancelButton, deleteButton;
	private JTextField loginField, hasloField, typKontaField;
	private TablePanel tablePanel;
	private SearchPanel searchPanel;
	
	private String[] tableStrings = {"Login", "Has³o", "Typ konta"};
	
	public KontaPanel(){
		
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
			
	}
	
	
}
