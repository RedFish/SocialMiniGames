package kinect;

import javax.swing.JLabel;
import javax.swing.JWindow;

/**
 * Classe qui affiche un message d'alerte
 * @author Richard GUERCI
 * @see KinectTracker
 */
public class DialogTropLoin extends JWindow {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public DialogTropLoin() {
		setSize(270, 50);
		JLabel l = new JLabel("     Approchez-vous du capteur Kinect.");
		add(l);
	}
}
