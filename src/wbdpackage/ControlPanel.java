package wbdpackage;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ControlPanel extends JPanel implements ActionListener{
	
	private ArrayList<Faktury> receiptList = new ArrayList<Faktury>();
	private String receiptColumnNames[] = {"idFaktury" ,"idKawiarni","idPracownika","idZnizki", "data", "kwota"};
	private ArrayList<Produkty> productList = new ArrayList<Produkty>();
	private String productColumnNames[] = {"idProduktu" ,"idKawiarni","idPodatku", "nazwa", "cena"};
	public static ArrayList<Wynagrodzenia> payList = new ArrayList<Wynagrodzenia>();
	private String paymentColumnNames[] = {"idWynagrodzenia" ,"idPracownika","dataWyplaty", "kwota"};
	private ArrayList<Pracownicy> employeeList = new ArrayList<Pracownicy>();
	private String employeeColumnNames[] = {"idPracownika" ,"imie","nazwisko", "dataZatrudnienia","pesel", "kontoBankowe"};
	private static final long serialVersionUID = 1L;
	private JComboBox<?> tableSelectionBox;
	private String[] tableStrings;
	private JTable patientTable;
	private DefaultTableModel tableModel;
	private JScrollPane scrollPane;
	
	private FakturyPanel fakturyPanel;
	private ProduktyPanel produktyPanel;
	private KontaPanel kontaPanel;
	private PodatkiVatPanel podatkiVatPanel;
	private PracownicyPanel pracownicyPanel;
	private WynagrodzeniaPanel wynagrodzeniaPanel;
	private ZnizkiPanel znizkiPanel;
	private JTabbedPane tabbedPane;
	

	public ControlPanel(int loggedInAs) throws ClassNotFoundException			//konstruktor
	{
		tabbedPane = new JTabbedPane();
		switch(loggedInAs) 
		{
			case 0: 																					 //admin
				fakturyPanel = new FakturyPanel();
				produktyPanel = new ProduktyPanel();
				kontaPanel = new KontaPanel();
				podatkiVatPanel = new PodatkiVatPanel();
				pracownicyPanel = new PracownicyPanel();
				wynagrodzeniaPanel = new WynagrodzeniaPanel();
				znizkiPanel = new ZnizkiPanel();
				
				tabbedPane.add("Faktury", fakturyPanel);
				tabbedPane.add("Produkty", produktyPanel);
				tabbedPane.add("Konta firmowe", kontaPanel);
				tabbedPane.add("Podatki Vat", podatkiVatPanel);
				tabbedPane.add("Pracownicy", pracownicyPanel);
				tabbedPane.add("Wynagrodzenia", wynagrodzeniaPanel);
				tabbedPane.add("Zni¿ki", znizkiPanel);
				break;
			case 1: 																				//kierownik
				fakturyPanel = new FakturyPanel();
				produktyPanel = new ProduktyPanel();
				kontaPanel = new KontaPanel();
				podatkiVatPanel = new PodatkiVatPanel();
				pracownicyPanel = new PracownicyPanel();
				wynagrodzeniaPanel = new WynagrodzeniaPanel();
				znizkiPanel = new ZnizkiPanel();
				
				tabbedPane.add("Faktury", fakturyPanel);
				tabbedPane.add("Produkty", produktyPanel);
				tabbedPane.add("Konta firmowe", kontaPanel);
				tabbedPane.add("Podatki Vat", podatkiVatPanel);
				tabbedPane.add("Pracownicy", pracownicyPanel);
				tabbedPane.add("Wynagrodzenia", wynagrodzeniaPanel);
				tabbedPane.add("Zni¿ki", znizkiPanel);
				break;
			case 2: 																				//pracownik
				fakturyPanel = new FakturyPanel();
				produktyPanel = new ProduktyPanel();
				
				tabbedPane.add("Faktury", fakturyPanel);
				tabbedPane.add("Produkty", produktyPanel);
				break;
		}
		add(tabbedPane);
	}
	
	public void manageTable()		//zmienia tabele zale¿nie od wybranej opcji
	{
		switch((String)tableSelectionBox.getSelectedItem())
		{
			case "---":
			{
				String[] columnNames = null;	
				tableModel.setColumnIdentifiers(columnNames);
				break;
			}
			case "Produkty":		
			{
				tableModel.setColumnIdentifiers(productColumnNames);
				deleteAllRows(tableModel);
				for(int i=0; i<productList.size(); i++)
				{
					Produkty product = productList.get(i);
					String data[] ={Integer.toString(product.getIdProduktu()),Integer.toString(product.getIdKawiarni()),
									Integer.toString(product.getIdPodatku()), product.getNazwa(), Float.toString(product.getCena())
									};
					tableModel.addRow(data);
				}
				
				break;
			}
			case "Faktury": 
			{
				tableModel.setColumnIdentifiers(receiptColumnNames);
				deleteAllRows(tableModel);
				for(int i=0; i<productList.size(); i++)
				{
					Faktury receipt = receiptList.get(i);
					String data[] ={Integer.toString(receipt.getIdFaktury()), Integer.toString(receipt.getIdKawiarni()),
									Integer.toString(receipt.getIdPracownika()), Integer.toString(receipt.getIdZnizki()),
									receipt.getData(), Float.toString(receipt.getKwota())
									};
					tableModel.addRow(data);
				}
				break;
			}
			case "Pracownicy":
			{
				tableModel.setColumnIdentifiers(employeeColumnNames);
				deleteAllRows(tableModel);
				for(int i=0; i<employeeList.size(); i++)
				{
					Pracownicy employee = employeeList.get(i);
					String data[] ={Integer.toString(employee.getIdPracownika()), employee.getImie(), employee.getNazwisko(),
									employee.getDataZatrudnienia(), employee.getPesel(), employee.getKontoBankowe()
									};
					tableModel.addRow(data);
				}
				break;
			}
			case "Wynagrodzenia": 
			{
				tableModel.setColumnIdentifiers(paymentColumnNames);
				deleteAllRows(tableModel);
				for(int i=0; i<productList.size(); i++)
				{
					Wynagrodzenia pay = payList.get(i);
					String data[] ={Integer.toString(pay.getIdWynagrodzenia()), Integer.toString(pay.getIdPracownika()),
									pay.getDataWyplaty(), Float.toString(pay.getKwota())
									};
					tableModel.addRow(data);
				}
				break;
			}
		}	
	}
	
	public void populateDataBase()		//stworzy³em jakieœ tam dane tej bazy i wrzuci³em je do ArrayList'ów
	{
		for(int i=1;i<4;i++)
		{
		Faktury receipt = new Faktury(i, 1, 1, 1, "jakies dane", 10.2f*i);
		receiptList.add(receipt);
		}
		for(int i=1;i<4;i++)
		{
		Produkty product = new Produkty(i, 1, 1, "produkt"+i, 2.35f*i);
		productList.add(product);
		}
		for(int i=1;i<4;i++)
		{
		Wynagrodzenia pay = new Wynagrodzenia(i, 1, "0"+i+"/03/2016", 2736.12f*0.5f*i);
		payList.add(pay);
		}
		for(int i=0;i<4;i++)
		{
		Pracownicy employee = new Pracownicy(i, 1, 1, 1, 1, 1, "dane", "dane", "dane", 
											"dane", "dane", "dane", "dane", "dane", "dane", "dane", "dane");
		employeeList.add(employee);
		}
	}
	
	public void deleteAllRows(DefaultTableModel tm)
	{
		if (tm.getRowCount() > 0) {
		    for (int i = tm.getRowCount() - 1; i > -1; i--) {
		        tm.removeRow(i);
		    }
		}
	}
	
	public void actionPerformed(ActionEvent event) 
	{
		if (event.getSource() == tableSelectionBox)
		{	
			manageTable();		
		}
	}	
} 
	


