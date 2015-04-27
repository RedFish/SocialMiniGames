package graphics;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.plaf.metal.MetalLookAndFeel;

public class Main {
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(new MetalLookAndFeel());
		} catch (UnsupportedLookAndFeelException e1) {e1.printStackTrace();}
		FenetreIntroduction intro = new FenetreIntroduction();
		try{
			Thread.sleep(2500);
		}
		catch (InterruptedException e) {} 	
		FenetreMenu menu = new FenetreMenu();// lancement du menu
		intro.setVisible(false);
		menu.setVisible(true);
	}
}
