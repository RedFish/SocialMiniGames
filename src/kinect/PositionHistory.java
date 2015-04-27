package kinect;

import java.awt.Point;
import java.awt.geom.Point2D;
import java.util.ArrayList;

/**
 * Classe qui permet de calculer les mouvements sur T secondes (variable temps)
 * Elle stocke les positions et le moment o� elles ont �t� prise pour pouvoir effectuer une moyenne de ces points
 * s'ils ont un moyenne inferieur � sensibilit� alors newPosition() retourne vrai.
 * @author Richard GUERCI
 * @see Mouse
 */
public class PositionHistory {
	private ArrayList<Point> points = new ArrayList<Point>();
	private ArrayList<Long> times = new ArrayList<Long>();
	private int temps;
	private int compteur;
	private final int sensibilite = 20;

	/**
	 * Le constructeur permet d'initialiser le delai (temps) pour lequel il va falloir rester immobile pour valider un mouvement
	 * @param temps d'analyse du mouvement
	 */
	public PositionHistory(int temps) {
		this.temps=temps;
		this.compteur=0;
		
		//on initialise les points
		for(int i=0;i<temps;i++){
			points.add(new Point(0,0));
		}

		//on initialise � la date actuelle
		long current = System.currentTimeMillis()/1000;
		for(int i=0;i<temps;i++){
			times.add(current);
		}
	}

	/**
	 * Fontion qui autorise un clic dans la classe Mouse en fonction des 'temps' dernieres positions.
	 * @return distance moyennes des points par rapport au point le plus ancien
	 */
	public boolean newPosition(int x, int y){
		long current = System.currentTimeMillis()/1000;
		if(times.get(temps-1)<=(current)-1){
			Point p1 = new Point(x,y);
			points.remove(0);
			times.remove(0);
			points.add(p1);
			times.add(current);
			//System.out.println(avgRayon());
			if(avgRayon()<=sensibilite){//Si le curseur reste dans un rayon R=sensibilite
				if(compteur%4==0){//trois secondes apr�s un premier clic pour en faire un second
					compteur++;
					return true;
				}
				else{
					compteur=0;
					return false;
				}
			}
		}
		return false;
	}

	/**
	 * Moyenne des distances entre le plus ancien point et les autres
	 * @return distance moyennes des points par rapport au point le plus ancien
	 */
	private int avgRayon(){
		int avg=0;
		Point p0,p1;
		p0 = points.get(0);
		for(int i=1;i<temps;i++){
			p1 = points.get(i);
			avg+=Point2D.distance(p0.getX(), p0.getY(), p1.getX(), p1.getY());
		}
		return avg/temps-1;
	}
}
