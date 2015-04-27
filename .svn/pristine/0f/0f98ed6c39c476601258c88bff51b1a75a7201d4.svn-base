package bdd;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class GetInfosPlayer
{
	//private static final String adresse = "http://127.0.0.1/application/get_infos_user.php";
	private final static String adresse = "http://alexandre.frantz57.free.fr/application/get_infos_user.php";

	public static ArrayList<String> getInfosPlayer(String id_user)
	{
		ArrayList<String> res = new ArrayList<String>(); 
		try
		{
			// Ouverture URL
			URL url = new URL(adresse);
			URLConnection urlConnection = url.openConnection();

			// Envoi données
			urlConnection.setDoOutput(true);
			String data = URLEncoder.encode("id_user", "UTF-8") + "=" + URLEncoder.encode(id_user, "UTF-8");
			OutputStreamWriter writer = new OutputStreamWriter(urlConnection.getOutputStream());
			writer.write(data);
			writer.flush();

			// Récupération résultats
			BufferedReader reader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
			res.add(reader.readLine());
			if(res.get(0) == null || res.get(0).equals("-1"))
				throw new NumberFormatException();
			res.add(reader.readLine());
			res.add(reader.readLine());
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
		return res;
	}
}