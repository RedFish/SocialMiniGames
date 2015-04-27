package kinect;
import java.awt.AWTException;
import java.awt.Dimension;
import java.awt.Robot;
import java.awt.event.InputEvent;

/**
 * Classe qui permet de prendre le controle de la souris (grace au coordonn�es r�cup�r� avec Kinect)
 * @author Richard GUERCI
 * @see KinectTracker PositionHistory
 */
public class Mouse extends Robot {
	private PositionHistory hist;
	
	/**
	 * Constructeur qui initialise l'objet PositionHistory() qui va enregister les X derniere positions du curseur
	 */
	public Mouse() throws AWTException {
		super();
		hist = new PositionHistory(2);
	}
	
	/**
	 * On bouge le curseur en fonction de x et y relativement � la taille de l'�cran.
	 * On d�clenche un clic si la position est la meme qu'il a X secondes (voir parametre pass� en parametre de PositionHistory() dans le constructeur
	 * @param position x et y dans la fenetre du tracker kinect
	 */
	public void mouseMove(int x,int y, boolean isauto, boolean wait){
		//on r�cupere la taille de l'�cran
		Dimension dimension = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		int height = (int)dimension.getHeight();
		int width  = (int)dimension.getWidth();
		
		//calcul des positions relative � l'�cran
		int xr=x*width/640;
		int yr=y*height/480;
		
		if(isauto){//si la detection et le clic sont automatique
			if(hist.newPosition(xr, yr)){
				if(!wait){//si le syteme n'est pas en attente (des actions (clics) sont possibles)
					mousePress(InputEvent.BUTTON1_MASK);
					mouseRelease(InputEvent.BUTTON1_MASK);
				}
			}
		}
		
		//on applique la position relative � l'�cran
		super.mouseMove(xr,yr);
	}

}
