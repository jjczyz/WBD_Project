package wbdpackage;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;

public class SearchPanel extends JPanel implements ActionListener {

	private JComboBox searchBy;
	private JTextField searchField;
	private JButton searchButton, searchNextButton, cancelButton;
	private TablePanel tablePanel;
	private SearchPanel searchPanel;
	
	public SearchPanel(String[] searchByStrings, TablePanel tp){
		
		tablePanel = tp;
		searchBy = new JComboBox(searchByStrings);
		searchField = new JTextField();
		
		searchButton = new JButton("Szukaj");
		searchButton.addActionListener(this);
		searchNextButton = new JButton("Szukaj dalej");
		searchNextButton.addActionListener(this);
		cancelButton = new JButton("Anuluj");
		cancelButton.addActionListener(this);
		
		setLayout(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.BOTH;
		c.insets = new Insets(5,5,5,5);
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		add(searchField, c);
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 1;
		add(new JLabel("Szukaj jako: "),c);
		c.gridx = 1;
		c.gridy = 1;
		add(searchBy, c);
		c.gridx = 0;
		c.gridy = 2;
		add(searchButton, c);
		c.gridx = 1;
		c.gridy = 2;
		add(cancelButton, c);
		c.gridx = 0;
		c.gridy = 3;
		add(searchNextButton, c);
		
		setBorder(BorderFactory.createTitledBorder("Wyszukiwarka"));
			
	}
	
	private void clearForm(){
		searchField.setText(null);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == cancelButton)
		{
			this.clearForm();
		}
		if(e.getSource() == searchButton)
		{
			int resultFlag = 0;
			if(!searchField.getText().isEmpty())
			{
				int column = searchBy.getSelectedIndex()+1;			
				DefaultTableModel model = tablePanel.getModel();
				for(int row = 0;row < model.getRowCount(); row++)
				{
						if(model.getValueAt(row, column).toString().toLowerCase().contains(searchField.getText().toLowerCase()))
						{
							resultFlag = 1;
							tablePanel.selectRow(row);						
							break;
						}					
				}
			}
			if(resultFlag == 0) JOptionPane.showMessageDialog(this, "Nie znaleziono wynikow");
		}
		if(e.getSource() == searchNextButton)
		{
			int resultFlag = 0;
			if(!searchField.getText().isEmpty())
			{
				int column = searchBy.getSelectedIndex()+1;
				int row = 0;				
				DefaultTableModel model = tablePanel.getModel();
				if(tablePanel.getSelectedRow() != -1)
				{
					row = tablePanel.getSelectedRow()+1;
				}
				for(;row < model.getRowCount(); row++)
				{
						if(model.getValueAt(row, column).toString().toLowerCase().contains(searchField.getText().toLowerCase()))
						{
							resultFlag = 1;
							tablePanel.selectRow(row);						
							break;
						}					
				}
			}
			if(resultFlag == 0) JOptionPane.showMessageDialog(this, "Nie znaleziono wynikow");
		}
	}	
}
