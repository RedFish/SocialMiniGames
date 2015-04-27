package graphics;

import games.Jeux;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;

import kinect.KinectMonitor;

import components.ImagePanel;

import bdd.GetInfosPlayer;


public class FenetrePlateau extends JFrame implements ActionListener, MouseListener{
	private static final long serialVersionUID = 1L;

	JLayeredPane panel_jeuNumerique;
	JLayeredPane panel_jeuLettre;
	JLayeredPane panel_jeuGeo;
	JLayeredPane panel_principal;

	JButton S_entrainer_1;
	JButton S_entrainer_2;
	JButton S_entrainer_3;
	JButton lancer_partie;
	JButton Quitter;
	JButton se_deconnecter;
	JLabel nom;
	JLabel nom_joueur;
	JLabel niveau;
	JSpinner niveau_joueur;
	JLabel acces_site;
	JCheckBox kinect_cb;
	Color bleu;
	Color vert;
	Color rouge;
	String id_user;
	JSpinner.NumberEditor spinnerEditor;


	VueJeux fenetre_jeux;

	int decalage = 0;
	boolean hors_connexion;

	public FenetrePlateau(){
		decalage = -60;
		hors_connexion=true;
		consctuct("");
	}

	public FenetrePlateau(final String id_user){
		hors_connexion=false;
		consctuct(id_user);
	}

	private void consctuct(final String id_user){
		this.id_user=id_user;

		setTitle("Plateau du Jeux");
		setSize(700,decalage+600);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Définition des différentes polices utilisées dans la fenetre du plateau:
		Font police = new Font("Calibri", Font.ROMAN_BASELINE, 20);
		Font police1 = new Font("Arial", Font.ITALIC+ Font.BOLD, 20);
		Font police2 = new Font("Calibri", Font.BOLD+Font.ITALIC, 14);
		bleu=new Color(185,222,236);
		vert=new Color(191,225,35);
		rouge=new Color(229,64,16);

		//Définition des composants:
		panel_principal=new JLayeredPane();
		panel_jeuNumerique= new JLayeredPane();
		panel_jeuLettre= new JLayeredPane();
		panel_jeuGeo= new JLayeredPane();

		//Detection de l'OS
		kinect_cb = new JCheckBox("utiliser Kinect");
		if(System.getProperty("os.name").equals("Mac OS X")){
			kinect_cb.setBounds(275, 15, 120, 20);
			panel_principal.add(kinect_cb);
		}

		Quitter = new JButton("Quitter");
		se_deconnecter = new JButton("Se déconnecter");
		Quitter.setFont(police1);
		Quitter.addActionListener(this);
		se_deconnecter.setFont(police1);
		se_deconnecter.addActionListener(this);
		if(!hors_connexion){
			//Logo de l'utilisateur:
			ImagePanel panel_image = new ImagePanel(new ImageIcon(getClass().getResource("logo_profil.jpeg")).getImage());
			Border border_line_img = BorderFactory.createLineBorder(Color.GRAY);
			panel_image.setBorder(border_line_img);
			panel_image.setBounds(10, 10, 80, 80);
			panel_principal.add(panel_image);

			nom = new JLabel("Pseudo :" );
			nom.setFont(police2);
			nom.setBounds(100, 10, 60, 15);
			panel_principal.add(nom);

			niveau = new JLabel("Niveau :");
			niveau.setFont(police2);
			niveau.setBounds(100,30,60, 15);
			panel_principal.add(niveau);

			ArrayList<String> infos_joueur = GetInfosPlayer.getInfosPlayer(id_user);
			String str="";
			try{str=infos_joueur.get(0);}catch(Exception e){}
			nom_joueur= new JLabel(str);
			nom_joueur.setBounds(160, 10, 100,15);
			panel_principal.add(nom_joueur);

			try{str=infos_joueur.get(1);}catch(Exception e){}
			niveau_joueur = new JSpinner();
			spinnerEditor = new JSpinner.NumberEditor(niveau_joueur);
			niveau_joueur.setEditor(spinnerEditor);
			spinnerEditor.getModel().setMinimum(1);
			spinnerEditor.getModel().setMaximum(3);
			spinnerEditor.getModel().setStepSize(1);
			spinnerEditor.getModel().setValue(Integer.valueOf(str));
			
			niveau_joueur.setBounds(160,30,50,15);
			panel_principal.add(niveau_joueur);

			Quitter.setBounds(540,20,130,30);
			Quitter.setFont(police2);
			Quitter.setBackground(bleu);
			se_deconnecter.setBounds(540,60,130,30);
			se_deconnecter.setFont(police2);
			se_deconnecter.setBackground(bleu);

			lancer_partie=new JButton("Jouer");
			lancer_partie.setBounds(400,20,100,70);
			lancer_partie.setFont(police1);
			lancer_partie.setBackground(bleu);
			lancer_partie.addActionListener(this);
			panel_principal.add(lancer_partie);
		}
		else{
			se_deconnecter.setText("Se connecter");
			se_deconnecter.setFont(police2);
			se_deconnecter.setBounds(420,10,130,30);
			Quitter.setBounds(560,10,130,30);
			Quitter.setFont(police2);

			Quitter.setBackground(bleu);

			se_deconnecter.setBackground(Color.lightGray);

		}

		S_entrainer_1=new JButton("s'entraîner");
		S_entrainer_2=new JButton("s'entraîner");
		S_entrainer_3=new JButton("s'entraîner");

		//Gestion panel_jeuNumerique:
		ImagePanel image_panel_chiffre = new ImagePanel(new ImageIcon(getClass().getResource("jeu_numerique.png")).getImage());
		image_panel_chiffre.setBounds(0,7,680,140);
		panel_jeuNumerique.setBounds(10, decalage+100,680,150);
		panel_principal.add(panel_jeuNumerique);
		TitledBorder border_panel_jeuNumerique =BorderFactory.createTitledBorder("Chiffres");
		panel_jeuNumerique.setBorder(border_panel_jeuNumerique);
		panel_jeuNumerique.setFont(police);	
		S_entrainer_1.setBounds(520,18,150,120);
		S_entrainer_1.setFont(police1);
		S_entrainer_1.setBackground(vert);
		S_entrainer_1.addActionListener(this);
		panel_jeuNumerique.add(image_panel_chiffre,new Integer(0));
		panel_jeuNumerique.add(S_entrainer_1,new Integer(1));
	



		//Gestion du panel_jeuLettre:

		ImagePanel image_panel_lettre = new ImagePanel(new ImageIcon(getClass().getResource("jeu_lettre.png")).getImage());
		image_panel_lettre.setBounds(2, 7, 680, 140);
		panel_jeuLettre.setBounds(10,decalage+420,680,150);
		panel_principal.add(panel_jeuLettre);
		TitledBorder border_panel_jeuLettre =BorderFactory.createTitledBorder("Lettres");
		panel_jeuLettre.setBorder(border_panel_jeuLettre);
		panel_jeuLettre.setFont(police);
		S_entrainer_2.setBounds(520,18,150,120);
		S_entrainer_2.setFont(police1);
		S_entrainer_2.setBackground(Color.PINK);
		S_entrainer_2.addActionListener(this);
		panel_jeuLettre.add(image_panel_lettre, new Integer(0));
		panel_jeuLettre.add(S_entrainer_2,new Integer(1));

		//Gestion du panel_jeuGeo:
		ImagePanel image_panel_geo = new ImagePanel(new ImageIcon(getClass().getResource("jeu_geographie.png")).getImage());
		image_panel_geo.setBounds(0, 9, 680, 137);
		panel_jeuGeo.setBounds(10,decalage+260,680,150);
		panel_principal.add(panel_jeuGeo);
		TitledBorder border_panel_jeuGeo =BorderFactory.createTitledBorder("Géographie");
		panel_jeuGeo.setBorder(border_panel_jeuGeo);
		panel_jeuGeo.setFont(police);
		S_entrainer_3.setBounds(520,18,150,120);
		S_entrainer_3.setFont(police1);
		S_entrainer_3.setBackground(rouge);
		S_entrainer_3.addActionListener(this);
		panel_jeuGeo.add(image_panel_geo, new Integer(0));
		panel_jeuGeo.add(S_entrainer_3,new Integer(1));

		panel_principal.add(Quitter);
		panel_principal.add(se_deconnecter);
		add(panel_principal);


		setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		int level = 2;
		if(spinnerEditor != null) level = (Integer)spinnerEditor.getModel().getNumber();
		KinectMonitor k = null;
		Jeux j = null;
		if(source== S_entrainer_1){
			if(fenetre_jeux==null||!fenetre_jeux.isVisible()){
				j = new Jeux("", level, 1);
			}
		}
		else if(source == S_entrainer_2)
		{
			if(fenetre_jeux==null||!fenetre_jeux.isVisible()){
				j = new Jeux("", level, 2);
			}
		}
		else if(source == S_entrainer_3)
		{
			if(fenetre_jeux==null||!fenetre_jeux.isVisible()){
				j = new Jeux("", level, 3);
			}
		}
		else if (source == lancer_partie){
			if(fenetre_jeux==null||!fenetre_jeux.isVisible()){
				j = new Jeux(id_user, level);
			}
		}
		else if(source== Quitter){ 
			quit();
		}
		else if(source == se_deconnecter){
			setVisible(false);
			new FenetreMenu();
		}

		if(source==S_entrainer_1||source==S_entrainer_2||source==S_entrainer_3||source==lancer_partie){
			if(fenetre_jeux==null||!fenetre_jeux.isVisible()){
				if(kinect_cb.isSelected()) k = new KinectMonitor();
				fenetre_jeux = new VueJeux(j,k);
			}
		}
	}

	public void quit(){
		final JOptionPane optionPane = new JOptionPane("Voulez vous quitter?",JOptionPane.QUESTION_MESSAGE,JOptionPane.YES_NO_OPTION);
		final JDialog dialog = new JDialog(this,"",true);
		dialog.setContentPane(optionPane);
		dialog.setLocationRelativeTo(null);
		dialog.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		optionPane.addPropertyChangeListener(
				new PropertyChangeListener() {
					public void propertyChange(PropertyChangeEvent e) {
						String prop = e.getPropertyName();
						if (dialog.isVisible() && (e.getSource() == optionPane)&& (prop.equals(JOptionPane.VALUE_PROPERTY))) {
							dialog.setVisible(false);
						}
					}
				});
		dialog.pack();
		Point p = dialog.getLocation();
		Dimension d = dialog.getSize();
		dialog.setLocation(p.x-d.width/2, p.y-d.height/2);
		dialog.setVisible(true);

		int value = ((Integer)optionPane.getValue()).intValue();
		if (value == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	public static void main(String args[]){
		new FenetrePlateau();
	}
}
