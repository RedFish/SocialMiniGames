/**
 * Permet de soumettre les achievements
 */

package bdd;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class SubmitAchievements
{
	//private static final String adresse = "http://127.0.0.1/application/submit_score.php";
	private final static String adresse = "http://alexandre.frantz57.free.fr/application/submit_achievements.php";
	
	
	public static int retournerIndice(String s)
	{
		if (s.equals("Score > 20"))
			return 2;
		if (s.equals("Score > 50"))
			return 3;
		if (s.equals("Score > 100"))
			return 5;
		else 
			return -1;
	}
	
	public static boolean enregistrerTrophees(String id_user, ArrayList<String> trophees)
	{
		String liste_trophees = "";
		try
		{
			for (String s : trophees)
			{
				if (liste_trophees.equals(""))
				{
					liste_trophees = ""+retournerIndice(s);
				}
				else
				{
					liste_trophees = liste_trophees + "_" + retournerIndice(s) ; 
				}
			}	
			
			// Ouverture URL
			URL url = new URL(adresse);
			URLConnection urlConnection = url.openConnection();
			
			// Envoi données
			urlConnection.setDoOutput(true);
			String data = URLEncoder.encode("id_user", "UTF-8") + "=" + URLEncoder.encode(id_user, "UTF-8");
			data += "&" + URLEncoder.encode("liste_trophees", "UTF-8") + "=" + URLEncoder.encode(liste_trophees, "UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
			writer.write(data);
			writer.flush();
		}
		catch (MalformedURLException e)
		{
			JOptionPane.showMessageDialog(null, "URL mal formée.", "Erreur !", JOptionPane.ERROR_MESSAGE);
		}
		catch (IOException e)
		{
			JOptionPane.showMessageDialog(null, "Pas de connexion Internet.", "Impossible de joindre le serveur! Le score n'a pas pu être sauvegardé.", JOptionPane.ERROR_MESSAGE);
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur d'exécution du script PHP.", "Erreur !", JOptionPane.ERROR_MESSAGE);
		}
		return false;
	}
}