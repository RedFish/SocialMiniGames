package controlers;

import games.JeuNumerique;
import games.Jeux;
import graphics.VueJeuNumerique;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;


public class ControlerJeuNumerique implements ActionListener {
	private JeuNumerique modele;
	private VueJeuNumerique vue;
	Jeux modele_jeux;
	
	public ControlerJeuNumerique(JeuNumerique modele,VueJeuNumerique vue, Jeux modele_jeux) {
		this.modele=modele;
		this.vue=vue;
		this.modele_jeux=modele_jeux;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object o = e.getSource();
		if(o instanceof JButton){
			JButton btn = (JButton) o;
			if(btn==vue.getBtn_next())
			{
				modele.lancerJeu();
			}
			else{
				for(int i=0; i<4;i++){
					if(btn==vue.getBtn()[i])
					{
						modele.checkResulat(vue.getBtn()[i].getText(), vue);
						modele.lancerJeu();
					}
				}
			}
			modele_jeux.maj();
		}
	}

	public JeuNumerique getModele() {
		return modele;
	}

	public void setModele(JeuNumerique modele) {
		this.modele = modele;
	}

	public VueJeuNumerique getVue() {
		return vue;
	}

	public void setVue(VueJeuNumerique vue) {
		this.vue = vue;
	}

}

