/**
 * Permet la connexion avec le serveur web.
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

public class Connexion
{
	//private static final String adresse = "http://127.0.0.1/application/login.php";
	private final static String adresse = "http://alexandre.frantz57.free.fr/application/login.php";
	
	public static int connexion(String login, String password)
	{
		try
		{
			if(login.equals("") || password.equals(""))
			{
				JOptionPane.showMessageDialog(null, "Veuillez indiquer un login et un mot de passe.", "Erreur !", JOptionPane.ERROR_MESSAGE);
				return 0;
			}
			
			// Ouverture URL
			URL url = new URL(adresse);
			URLConnection urlConnection = url.openConnection();
			
			// Envoi données
			urlConnection.setDoOutput(true);
			String data = URLEncoder.encode("user", "UTF-8") + "=" + URLEncoder.encode(login, "UTF-8");
			data += "&" + URLEncoder.encode("mdp", "UTF-8") + "=" + URLEncoder.encode(password, "UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
			writer.write(data);
		    writer.flush();
		    
		    // Récupération résultats
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			Integer res;
			res = Integer.valueOf(reader.readLine());
			if(res != null && res > 0)
				return res;
			else
				if(res != null && res == 0)
					JOptionPane.showMessageDialog(null, "Couple login/password incorrect", "Erreur !", JOptionPane.ERROR_MESSAGE);
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
			JOptionPane.showMessageDialog(null, "Pas de connexion Internet.", "Impossible de joindre le serveur !", JOptionPane.ERROR_MESSAGE);
		}
		catch (NumberFormatException e)
		{
			JOptionPane.showMessageDialog(null, "Erreur d'exécution du script PHP.", "Erreur !", JOptionPane.ERROR_MESSAGE);
		}
		return 0;
	}
}