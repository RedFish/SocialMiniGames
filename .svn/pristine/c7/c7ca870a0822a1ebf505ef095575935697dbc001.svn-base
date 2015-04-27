/**
 * Controleur entre la vue VueJeuDeLettres et le modèle JeuDeLettres.
 */

package controlers;

import games.JeuDeLettres;
import games.Jeux;
import graphics.VueJeuDeLettres;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;

public class ControlerJeuDeLettres implements ActionListener, KeyListener
{
	private JeuDeLettres modele;
	private VueJeuDeLettres vue;
	Jeux modele_jeux;
	
	public ControlerJeuDeLettres(JeuDeLettres modele, VueJeuDeLettres vue, Jeux modele_jeux)
	{
		this.modele = modele;
		this.vue = vue;
		this.modele_jeux=modele_jeux;
	}
	
	@Override
	public void actionPerformed(ActionEvent e)
	{
		Object o = e.getSource();
		if(o instanceof JButton){
			JButton btn = (JButton) o;
			if(btn==vue.getBtn_annuler())
			{
				modele.effacerMot();
			}
			else if(btn==vue.getBtn_valider())
			{
				modele.verifierMot(vue);
				if(modele.getNombreMotsTrouves() == modele.getNombreMotsPossibles())
					modele.genererLettres();
			}
			else if(btn==vue.getBtn_passer())
				modele.genererLettres();
			else
			{
				for(int i=0; i<modele.getNombreLettresProposees();i++){
					if(btn==vue.getLettres()[i])
					{
						modele.ajouterLettre(vue.getLettres()[i].getText());
					}
				}
			}
		}
		modele_jeux.maj();
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		Object o = e.getSource();
		if(o instanceof JButton)
		{
			if(e.getKeyCode() == KeyEvent.VK_ENTER)
			{
				modele.verifierMot(vue);
				if(modele.getNombreMotsTrouves() == modele.getNombreMotsPossibles())
					modele.genererLettres();
				modele_jeux.maj();
			}	
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
}