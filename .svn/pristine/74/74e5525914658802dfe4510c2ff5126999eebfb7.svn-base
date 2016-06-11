package osmpackage;

import java.awt.Component;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Utility {

	public static boolean isAlpha(String string) {
	    char[] chars = string.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c) || chars.length == 0) {
	            return false;
	        }
	    }

	    return true;
	}
	
	public static boolean isDigit(String string) {
	 
	    char[] chars = string.toCharArray();
	    for (char c : chars) 
	    {
	        if(!Character.isDigit(c) || chars.length == 0) 
	        {	
	            return false;	          	        
	        }
	    }
	    return true;
	}
	public static boolean checkLength(String string, int length){
		char[] chars = string.toCharArray();
	    if(chars.length != 11)
	    {
	    	return false;    	
	    }
	    return true;
	}
	public static boolean checkForSamePesel(String pesel)
	{
	    return true;
	}
	
	public static void setPanelEnabled(JPanel panel, Boolean isEnabled) { 
	    panel.setEnabled(isEnabled);

	    Component[] components = panel.getComponents();

	    for(int i = 0; i < components.length; i++) {
	        if(components[i].getClass().getName() == "javax.swing.JTextField") {
	           ((JTextField)components[i]).setText(null);
	        }   
	        components[i].setEnabled(isEnabled);
	    }
	}

}
