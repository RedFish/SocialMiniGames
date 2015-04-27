package components;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JWindow;

public class PopUp extends JWindow {
	private static final long serialVersionUID = 1L;
	JLayeredPane panel_principal;
	JLabel label;
	final Font police = new Font("Calibri", Font.BOLD, 16);

	public PopUp(final String s,final Component c,final int t) {
		new Thread(new Runnable() {
			public void run(){
				int width = 100+s.length()*6;
				setSize(width, 60);
				setLocationRelativeTo(c);
				setBackground(Color.LIGHT_GRAY);
				int x = getLocation().x;
				int y = getLocation().y;
				Color color = new Color(110,80,250);
				if(c!=null){
					switch(t){
					case 0:
						y=y-150;
						break;
					case 1:
						color = new Color(200,0,100);
						x=x-295;
						y=y-280;
						break;
					case 2:
						color = new Color(200,0,100);
						x=x+290;
						y=y-280;
						break;
					}
				}
				setLocation(new Point(x,y));
				panel_principal = new JLayeredPane();
				add(panel_principal);
				label = new JLabel(s);
				label.setFont(police);
				label.setBounds(0, 20, width, 20);
				label.setForeground(color);
				label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
				panel_principal.add(label);
				setVisible(true);
				validate();
				repaint();
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {}
				setVisible(false);
			}
		}).start();
	}

	public static void good(Component c){
		ArrayList<String> s = new ArrayList<String>();
		s.add("Bravo");
		s.add("So Brain!");
		s.add("Good");
		s.add("Excellent");
		s.add("Genious");
		s.add("Amazing!");
		s.add("Brilliant");
		s.add("On s'approche du HighScore!");
		s.add("C'est juste");
		s.add("La prochaine sera plus difficile");
		int i = new Random().nextInt(s.size());
		new PopUp(s.get(i),c,0);
	}

	public static void bad(Component c){
		ArrayList<String> s = new ArrayList<String>();
		s.add("Faux!");
		s.add("Essai encore");
		s.add("La prochaine sera la bonne");
		s.add("La chance n'est pas de ton côté");
		s.add("Faux! La question n'était pas facile");
		s.add("Tu as besoin d'entrainement");
		int i = new Random().nextInt(s.size());
		new PopUp(s.get(i),c,0);
	}
	
	public static void multiplicateur(Component c, int multiplicateur) {
		new PopUp("Multiplicateur X"+multiplicateur,c,1);
	}

	public static void bonusTemps(Component c, int bonus_temps) {
		new PopUp("Bonus de temps de "+bonus_temps,c,2);
	}
	
	public static void malusTemps(Component c, int bonus_temps) {
		new PopUp("Malus de temps de "+bonus_temps,c,2);
	}
		
}


