package graphics;

import games.Jeux;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;

import kinect.KinectMonitor;

public class VueJeux extends JFrame implements Observer,ActionListener {
	private static final long serialVersionUID = 1L;
	JLayeredPane panel_principal;
	JLayeredPane panel_jeu;
	JLabel timer_lbl1,timer_lbl2,score_lbl1,score_lbl2, lbl_trophees, lbl_trophees_liste;
	JProgressBar timer;
	final Font police = new Font("Calibri", Font.BOLD, 20);
	final Font police1 = new Font("Calibri", Font.ITALIC, 20);
	String msg_final="";
	JButton btn_pause,btn_restart,btn_retour;

	Jeux modele_jeux;
	KinectMonitor kinect;

	public VueJeux(Jeux modele_jeux, KinectMonitor k) {
		this.modele_jeux=modele_jeux;
		this.modele_jeux.addObserver(this);
		kinect = k;

		setSize(830,650);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);

		panel_principal = new JLayeredPane();
		add(panel_principal);

		if(modele_jeux.isTrainning()){
			btn_pause = new JButton("Pause");
			btn_pause.setBounds(480, 10, 100, 30);
			btn_pause.addActionListener(this);
			panel_principal.add(btn_pause);
			btn_restart = new JButton("Remise à zero");
			btn_restart.setBounds(200, 10, 140, 30);
			btn_restart.addActionListener(this);
			panel_principal.add(btn_restart);
		}
		btn_retour = new JButton("Retour au menu");
		btn_retour.setBounds(340, 10, 140, 30);
		btn_retour.addActionListener(this);
		panel_principal.add(btn_retour);



		timer = new JProgressBar(JProgressBar.VERTICAL);
		timer.setBounds(800, 20, 20, 590);
		timer.setMaximum(modele_jeux.getTemps_init());
		timer.setMinimum(0);
	
		
		timer.setValue(0);
		
		
		timer_lbl1 = new JLabel("Temps restant :       sec");
		timer_lbl2 = new JLabel(modele_jeux.getTemps_restant()+"");
		timer_lbl1.setBounds(595, 13, 250, 20);
		timer_lbl2.setBounds(735, 13, 124, 20);
		timer_lbl1.setFont(police);
		timer_lbl2.setFont(police);
		panel_principal.add(timer_lbl1);
		panel_principal.add(timer_lbl2);
		panel_principal.add(timer);
		score_lbl1 = new JLabel("Score :");
		score_lbl2 = new JLabel(modele_jeux.getScore()+"");
		score_lbl1.setBounds(10, 13, 150, 20);
		score_lbl2.setBounds(75, 13, 125, 20);
		score_lbl1.setFont(police);
		score_lbl2.setFont(police);
		panel_principal.add(score_lbl1);
		panel_principal.add(score_lbl2);
		
		panel_jeu = new JLayeredPane();
		panel_jeu.setBounds(0, 50, 800, 600);
		panel_principal.add(panel_jeu);

		setVisible(true);
		modele_jeux.start();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof Jeux){
			Jeux j = (Jeux) arg0;
			score_lbl2.setText(j.getScore()+"");
			if(j.isNew_game()){
				j.setNew_game(false);
				if(j.getNo_jeu()==1&&j.getNo_jeu()<=j.getMax_jeu()){
					setTitle("Jeu Numerique");
					VueJeuNumerique vue = new VueJeuNumerique(modele_jeux.getModele_jeu_numerique(),j);
					vue.setScoreInvisible();
					panel_jeu = vue;
					panel_jeu.setBounds(0, 50, 800, 600);
					panel_principal.add(panel_jeu);
					msg_final="Fin du jeu Numérique!";
				}
				else if(j.getNo_jeu()==2&&j.getNo_jeu()<=j.getMax_jeu()){
					if(!msg_final.equals("")) message(msg_final);
					setTitle("Jeu de Lettres");
					panel_principal.remove(panel_jeu);
					panel_jeu.removeAll();
					panel_principal.validate();
					VueJeuDeLettres vue = new VueJeuDeLettres(modele_jeux.getModele_jeu_de_lettre(),j);
					vue.setScoreInvisible();
					panel_jeu = vue;
					panel_jeu.setBounds(0, 50, 800, 600);
					panel_principal.add(panel_jeu);
					msg_final="Fin du jeu de Lettre!";
				}
				else if(j.getNo_jeu()==3&&j.getNo_jeu()<=j.getMax_jeu()){
					if(!msg_final.equals("")) message(msg_final);
					setTitle("Jeu de Géographie");
					panel_principal.remove(panel_jeu);
					panel_jeu.removeAll();
					panel_principal.validate();
					VueJeuGeographie vue = new VueJeuGeographie(modele_jeux.getModele_jeu_geographie(),j);
					vue.setScoreInvisible();
					panel_jeu = vue;
					panel_jeu.setBounds(0, 50, 800, 600);
					panel_principal.add(panel_jeu);
					msg_final="Fin du jeu de Géographie!";
				}
				else{
					if(!msg_final.equals("")) message(msg_final);
					setTitle("Game Over");
					panel_jeu.removeAll();
					panel_jeu.validate();
					panel_principal.removeAll();
					panel_principal.validate();
					panel_principal = new JLayeredPane();
					
					add(panel_principal);
					this.validate();
					JLabel fin_label = new JLabel();
					if(!modele_jeux.isTrainning()) fin_label.setText("Fin de la partie!");
					else fin_label.setText("Fin de l'entrainement!");
					fin_label.setBounds(0, 160, 800, 20);
					fin_label.setFont(police);
					fin_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
					panel_principal.add(fin_label);
					JLabel score_label = new JLabel("Votre Score "+j.getScore());
					score_label.setBounds(0, 250, 800, 20);
					score_label.setFont(police);
					score_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
					panel_principal.add(score_label);
					JLabel highscore_label = new JLabel("HighScore "+j.getHighScore());
					highscore_label.setBounds(0, 290, 800, 20);
					highscore_label.setFont(police);
					highscore_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
					panel_principal.add(highscore_label);
					
					
					JLabel lbl_trophees = new JLabel("Trophees debloques");
					lbl_trophees.setBounds(0, 315, 800, 20);
					lbl_trophees.setFont(police);
					lbl_trophees.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
					if(modele_jeux.getTrophees_debloques().size()>0) panel_principal.add(lbl_trophees);
					JLabel lbl_trophees_liste = new JLabel();
					
					modele_jeux.test_score();		
					
					for (String s : modele_jeux.getTrophees_debloques())
					{
						
						if (lbl_trophees_liste.getText().equals(""))
						{
							lbl_trophees_liste.setText(s);
						}
						else 
						{
							lbl_trophees_liste.setText(lbl_trophees_liste.getText()+"  "+s);
						}
					}
					
					lbl_trophees_liste.setFont(police);
					lbl_trophees_liste.setBounds(0, 340, 800, 20);
					lbl_trophees_liste.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
					panel_principal.add(lbl_trophees_liste);
					
					
					if(modele_jeux.isTrainning()){
						JLabel info_label = new JLabel("Le score n'a pas été enregistré");
						info_label.setBounds(0, 350, 800, 20);
						info_label.setFont(police1);
						info_label.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
						panel_principal.add(info_label); 
					}
					btn_retour = new JButton("Retour au menu");
					btn_retour.setBounds(320, 440, 160, 40);
					btn_retour.addActionListener(this);
					panel_principal.add(btn_retour);
				}
			}
			timer_lbl2.setText(j.getTemps_restant()+"");
			timer.setValue(j.getTemps_restant());
			if( timer.getValue()>= 10){
				timer.setForeground(Color.green);
			}else if (timer.getValue()>=5){
				timer.setForeground(Color.orange);
			}else{
				timer.setForeground(Color.red);
			}
			}
		}
	

	public void message(String s){
		JOptionPane.showMessageDialog(this,s);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if(src==btn_pause){
			if(btn_pause.getText().equals("Pause")){
				btn_pause.setText("Reprise");
			}
			else{
				btn_pause.setText("Pause");
			}
			modele_jeux.pause();
		}
		else if(src==btn_restart){
			modele_jeux.reset();
		}
		else if(src==btn_retour){
			setVisible(false);
			modele_jeux.getTimer().pause();
		}
	}

	public void setVisible(boolean b){
		super.setVisible(b);
		if(!b){
			modele_jeux.getTimer().pause();
			if(kinect!=null) {
				kinect.embed.kinect.interrupt();
				kinect.embed.stop();
				kinect.embed.destroy();
				kinect.embed.kinect = null;
				kinect.embed = null;
				kinect.setVisible(false);
				kinect = null;
			}
		}
	}

	public static void main(String args[]){
		Jeux j = new Jeux("2",1,3);
		new VueJeux(j,null);
	}
}
