/**
 * Représente une vue relative à un jeu de lettres.
 */

package graphics;

import games.JeuDeLettres;
import games.Jeux;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JList;
import javax.swing.JScrollPane;

import controlers.CheatListenerJeuDeLettres;
import controlers.ControlerJeuDeLettres;

public class VueJeuDeLettres extends JLayeredPane implements Observer
{
	private static final long serialVersionUID = 1L;
	private JeuDeLettres modele;
	Jeux modele_jeux;
	private JButton lettres[],btn_valider,btn_annuler,btn_passer;
	private JLabel lbl_mot;
	private JLabel lbl_score;
	private JLabel lbl_mots_trouves;
	private JLabel lbl_mots_restants;
	@SuppressWarnings("rawtypes")
	private JList jl_mots_restants;
	private JScrollPane scroll_mots_restants;
	private boolean cheated = false;
	final Font police = new Font("Calibri", Font.BOLD, 20);
	final Font police1 = new Font("Calibri", Font.BOLD, 30);
	
	@SuppressWarnings("rawtypes")
	public VueJeuDeLettres(JeuDeLettres modele, Jeux j)
	{
		this.modele = modele;
		this.modele_jeux=j;
		this.modele_jeux.addObserver(this);
		ControlerJeuDeLettres action = new ControlerJeuDeLettres(modele,this,modele_jeux);
		CheatListenerJeuDeLettres key = new CheatListenerJeuDeLettres(this);
		
		lbl_score = new JLabel();
		lbl_score.setBounds(0, 0, 100, 20);
		add(lbl_score);
		
		lbl_mot = new JLabel("Mot :");
		lbl_mot.setBounds(0, 100, 800, 20);
		lbl_mot.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		lbl_mot.setFont(police1);
		add(lbl_mot);
		
		lettres = new JButton[modele.getNombreLettresProposees()];
		int x = 400-(modele.getNombreLettresProposees()*50/2);
		for(int i=0;i<modele.getNombreLettresProposees();i++)
		{
			lettres[i] = new JButton();
			lettres[i].addActionListener(action);
			lettres[i].addKeyListener(action);
			lettres[i].addKeyListener(key);
			lettres[i].setFont(police);
			add(lettres[i]);
			lettres[i].setBounds(x, 180, 50, 50);
			x += 50;
		}
		
		lbl_mots_trouves = new JLabel();
		lbl_mots_trouves.setBounds(350, 280, 200, 20);
		add(lbl_mots_trouves);
		
		lbl_mots_restants = new JLabel();
		lbl_mots_restants.setBounds(350, 310, 200, 20);
		add(lbl_mots_restants);
		
		btn_valider = new JButton("Valider");
		btn_valider.setBounds(220, 400, 100, 40);
		btn_valider.addActionListener(action);
		btn_valider.addKeyListener(key);
		btn_valider.setEnabled(false);
		add(btn_valider);
		
		btn_annuler = new JButton("Effacer");
		btn_annuler.setBounds(350, 400, 100, 40);
		btn_annuler.addActionListener(action);
		btn_annuler.addKeyListener(key);
		btn_annuler.setEnabled(false);
		add(btn_annuler);
		
		btn_passer = new JButton("Passer");
		btn_passer.setBounds(480, 400, 100, 40);
		btn_passer.addActionListener(action);
		btn_passer.addKeyListener(key);
		add(btn_passer);
		
		jl_mots_restants = new JList();
		scroll_mots_restants = new JScrollPane(jl_mots_restants);
		scroll_mots_restants.setBounds(20, 80, 200, 280);
		scroll_mots_restants.addKeyListener(key);
		scroll_mots_restants.setVisible(false);
		add(scroll_mots_restants);
		
		modele.addObserver(this);
		modele.genererLettres();
	}
	
	public void cheatAfficherMots()
	{
		if(!cheated)
		{
			//getParent().getParent().getParent().getParent().getParent().setSize(450, 620);
			//setSize(450, 620);
			//((JFrame) getParent().getParent().getParent().getParent().getParent()).setLocationRelativeTo(null);
			scroll_mots_restants.setVisible(true);
			cheated = true;
		}
		else
		{
			//getParent().getParent().getParent().getParent().getParent().setSize(450, 420);
			//setSize(450, 420);
			//((JFrame) getParent().getParent().getParent().getParent().getParent()).setLocationRelativeTo(null);
			scroll_mots_restants.setVisible(false);
			cheated = false;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void update(Observable arg0, Object arg1)
	{
		for(int i=0; i<modele.getNombreLettresProposees(); i++)
		{
			lettres[i].setText(modele.getLettres().get(i));
		}
		lbl_mot.setText("Mot : " + modele.getMotSoumis());
		lbl_score.setText("Score = " + modele.getScore());
		lbl_mots_restants.setText("Mots restants : " + String.valueOf(modele.getNombreMotsPossibles() - modele.getNombreMotsTrouves()));
		lbl_mots_trouves.setText("Mots trouvés : " + String.valueOf(modele.getNombreMotsTrouves()));
		if(modele.getMotSoumis().equals(""))
		{
			btn_annuler.setEnabled(false);
			btn_valider.setEnabled(false);
		}
		else
		{
			btn_annuler.setEnabled(true);
			btn_valider.setEnabled(true);
		}
		jl_mots_restants.setListData(modele.getMotsPossibles().toArray());
	}

	public JButton[] getLettres()
	{
		return lettres;
	}

	public JButton getBtn_valider()
	{
		return btn_valider;
	}

	public JButton getBtn_annuler()
	{
		return btn_annuler;
	}
	
	public JButton getBtn_passer()
	{
		return btn_passer;
	}

	public void setScoreInvisible() {
		lbl_score.setVisible(false);
	}
}
