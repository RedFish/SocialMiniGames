/**
 * Permet de soumettre un score.
 */

package bdd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

import javax.swing.JOptionPane;

public class SubmitScore
{
	//private static final String adresse = "http://127.0.0.1/application/submit_score.php";
	private final static String adresse = "http://alexandre.frantz57.free.fr/application/submit_score.php";

	public static boolean enregistrerScore(String id_user, String score)
	{
		try
		{
			// Ouverture URL
			URL url = new URL(adresse);
			URLConnection urlConnection = url.openConnection();

			// Envoi données
			urlConnection.setDoOutput(true);
			String data = URLEncoder.encode("id_user", "UTF-8") + "=" + URLEncoder.encode(id_user, "UTF-8");
			data += "&" + URLEncoder.encode("score", "UTF-8") + "=" + URLEncoder.encode(score, "UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
			writer.write(data);
			writer.flush();

			// Récupération résultats
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			Integer res;
			res = Integer.valueOf(reader.readLine());
			if(res != null && res == 1)
				return true;
			else
				if(res != null && res == 0)
					JOptionPane.showMessageDialog(null, "Erreur lors de l'insertion du score.", "Erreur !", JOptionPane.ERROR_MESSAGE);
				else
					if(res != null && res == -1)
						JOptionPane.showMessageDialog(null, "Erreur lors de l'envoi des données au serveur.", "Erreur !", JOptionPane.ERROR_MESSAGE);
					else
						JOptionPane.showMessageDialog(null, "Erreur d'exécution du script PHP.", "Erreur !", JOptionPane.ERROR_MESSAGE);
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