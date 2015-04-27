package bdd;

import java.awt.Desktop;
import java.awt.Menu;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Browser {

	public static void open(String adresse){
		Desktop desktop = null; 
		java.net.URI url; 
		try { 
			url = new java.net.URI(adresse); 
			if (Desktop.isDesktopSupported()) 
			{ 
				desktop = Desktop.getDesktop(); 
				desktop.browse(url); 
			} 
		} 
		catch (Exception ex) { 
			Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex); 
		}
	}

}
