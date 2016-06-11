/*
 * Projekt WBD
 * Twórcy:	Jakub Czy¿   261244
 * 			Konrad Karaœ   ??????
 */


package wbdpackage;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

public class MainWindow extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private ControlPanel ctrlPane;
	private int defaulMainFrametWidth = 920, defaultMainFrameHeight = 720;
	private String login;
	private String password;
	private int loggedInAs;
	private ArrayList<Konta> accountList = new ArrayList<Konta>();
	
	public MainWindow() throws ClassNotFoundException
	{
		populateAccountDataBase();
		loggedInAs = -1;
		while(loggedInAs == -1)
		{
			login = JOptionPane.showInputDialog("Wpisz login");
			password = JOptionPane.showInputDialog("Wpisz has³o");
			
			for(int i=0; i<accountList.size(); i++)
			{
				Konta account = accountList.get(i);
				if(account.getLogin().equals(login))
				{
					System.out.println("test");
					if(account.getPassword().equals(password))
					{
						loggedInAs = account.getAccountType();
						break;
					}
				}
			}
			if(loggedInAs == -1)JOptionPane.showMessageDialog(null, "Niepoprawny login lub/i has³o");
		}
			
		this.setTitle("Cafe Control Panel");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(defaulMainFrametWidth, defaultMainFrameHeight);
		this.setLayout(new BorderLayout());	
		
		ctrlPane = new ControlPanel(loggedInAs);
		ctrlPane.setSize(defaulMainFrametWidth, defaultMainFrameHeight);
		this.add(ctrlPane, BorderLayout.CENTER);
		this.setVisible(true);
	}
	
	public ControlPanel getCtrlPane(){
		return ctrlPane;
	}
	
	@Override
	public void actionPerformed(ActionEvent event) {
	}
	
	public static void main(String[] args)
	{
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            	try {
						new MainWindow();
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
					}
	        }
	    });
	}
	
	public void populateAccountDataBase()
	{
		Konta account = new Konta();
		account.setLogin("admin");
		account.setPassword("admin");
		account.setAccountType(0);
		accountList.add(account);
		Konta account1 = new Konta();
		account1.setLogin("kierownik");
		account1.setPassword("kierownik");
		account1.setAccountType(1);
		accountList.add(account1);
		Konta account2 = new Konta();
		account2.setLogin("pracownik");
		account2.setPassword("pracownik");
		account2.setAccountType(2);
		accountList.add(account2);
	}
}
