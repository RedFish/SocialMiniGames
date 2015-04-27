package graphics;

import games.JeuNumerique;
import games.Jeux;

import java.awt.Font;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;

import controlers.ControlerJeuNumerique;

public class VueJeuNumerique extends JLayeredPane implements Observer {
	private static final long serialVersionUID = 1L;
	private JeuNumerique modele;
	Jeux modele_jeux;
	private JButton btn[],btn_next;
	private JLabel lbl_question;
	private JLabel lbl_score;

	private JLabel lbl_timer;
	final Font police = new Font("Calibri", Font.BOLD, 36);


	public VueJeuNumerique(JeuNumerique modele, Jeux j) {
		this.setModele(modele);
		modele_jeux = j;
		modele_jeux.addObserver(this);

		ControlerJeuNumerique action = new ControlerJeuNumerique(modele,this,modele_jeux);

		btn = new JButton[4];
		for(int i=0;i<4;i++){
			btn[i] = new JButton(i+"");
			btn[i].addActionListener(action);
			btn[i].setFont(police);
			add(btn[i]);
		}
		btn[0].setBounds(190, 200, 180, 80);
		btn[1].setBounds(410, 200, 180, 80);
		btn[2].setBounds(190, 300, 180, 80);
		btn[3].setBounds(410, 300, 180, 80);


		lbl_score = new JLabel();
		lbl_score.setBounds(0, 0, 100, 20);
		add(lbl_score);


		lbl_question = new JLabel();
		lbl_question.setBounds(0, 90, 800, 40);
		lbl_question.setFont(police);
		lbl_question.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
		add(lbl_question);

		btn_next = new JButton("Passer");
		btn_next.setBounds(350, 450, 100, 40);
		btn_next.addActionListener(action);
		add(btn_next);


		modele.addObserver(this);
		modele.lancerJeu();
	}

	@Override
	public void update(Observable arg0, Object arg1)
	{
		Random random = new Random();
		lbl_question.setText(modele.afficherEquation());
		lbl_score.setText("Score = " + modele.getScore().toString());

		if(modele.isNew_game())
			switch(random.nextInt(4))
			{
			case 0:
				btn[0].setText(modele.getInconnueBis());
				btn[1].setText(modele.getRepFausse1());
				btn[2].setText(modele.getRepFausse2());
				btn[3].setText(modele.getRepFausse3());
				break;
			case 1:
				btn[3].setText(modele.getInconnueBis());
				btn[0].setText(modele.getRepFausse1());
				btn[1].setText(modele.getRepFausse2());
				btn[2].setText(modele.getRepFausse3());
				break;
			case 2:
				btn[2].setText(modele.getInconnueBis());
				btn[3].setText(modele.getRepFausse1());
				btn[0].setText(modele.getRepFausse2());
				btn[1].setText(modele.getRepFausse3());
				break;
			case 3:
				btn[1].setText(modele.getInconnueBis());
				btn[2].setText(modele.getRepFausse1());
				btn[3].setText(modele.getRepFausse2());
				btn[0].setText(modele.getRepFausse3());
				break;				
			}

	}

	public JButton[] getBtn() {
		return btn;
	}

	public void setBtn(JButton btn[]) {
		this.btn = btn;
	}

	public JLabel getLbl_question() {
		return lbl_question;
	}

	public void setLbl_question(JLabel lbl_question) {
		this.lbl_question = lbl_question;
	}

	public JButton getBtn_next() {
		return btn_next;
	}

	public void setBtn_next(JButton btn_next) {
		this.btn_next = btn_next;
	}

	public JLabel getLbl_timer() {
		return lbl_timer;
	}

	public void setLbl_timer(JLabel lbl_timer) {
		this.lbl_timer = lbl_timer;
	}

	public JeuNumerique getModele() {
		return modele;
	}

	public void setModele(JeuNumerique modele) {
		this.modele = modele;
	}

	public void setEnabled(boolean enabled){
		for(JButton b:btn){
			b.setEnabled(enabled);
		}
		btn_next.setEnabled(enabled);
	}

	public void setScoreInvisible() {
		lbl_score.setVisible(false);
	}
}
