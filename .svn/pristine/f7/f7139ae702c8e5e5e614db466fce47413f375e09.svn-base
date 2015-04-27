package graphics;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import components.ImagePanel;

import bdd.Browser;
import bdd.Connexion;

public class FenetreMenu extends JFrame implements ActionListener,MouseListener,KeyListener {
	private static final long serialVersionUID = 1L;
	private JButton se_connecter;
	private JButton quitter;
	private JButton hors_connexion;
	private JLabel mot_de_passe_oublie ;
	private JTextField login;
	private JPasswordField pwd;
	static int i_err=0;

	public FenetreMenu(){
		setTitle("Menu");
		setSize(500,350);
		setLocationRelativeTo(null);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//Panel_principal:
		JLayeredPane panel_principal = new JLayeredPane();

		ImagePanel image_panel = new ImagePanel(new ImageIcon(getClass().getResource("fenetre_menu1.jpg")).getImage());
		panel_principal.add(image_panel);
		//Panel_identification:
		JLayeredPane panel_identification = new JLayeredPane();
		panel_identification.setBounds(40,90,420,150);

		Font police = new Font("Arial", Font.ITALIC, 14);
		Font police1 = new Font("Arial", Font.ITALIC, 12);
		Font police2 = new Font("Calibri", Font.ROMAN_BASELINE, 12);


		login = new JTextField("");
		login.setBounds(110,10,250,30);
		login.setFont(police);


		pwd = new JPasswordField("");
		pwd.setBounds(110,50,250,30);
		pwd.setFont(police);


		JLabel label_login = new JLabel("Identifiant : ");
		label_login.setFont(police1);
		label_login.setBounds(20,10,100,30);


		JLabel label_pwd = new JLabel("Mot de passe : ");
		label_pwd.setFont(police1);
		label_pwd.setBounds(20,50,150,30);

		mot_de_passe_oublie = new JLabel("Mot de passe oublie?");
		mot_de_passe_oublie.setFont(police2);
		mot_de_passe_oublie.setBounds(110,80,300,20);
		mot_de_passe_oublie.setForeground(Color.BLUE);
		mot_de_passe_oublie.setText("<html><u>Mot de passe oublie ?</u></html>");
		mot_de_passe_oublie.addMouseListener(this);



		se_connecter=new JButton("Connexion");
		se_connecter.setBounds(110,110,120,23);
		se_connecter.setFont(police1);
		se_connecter.addActionListener(this);
		
		


		quitter=new JButton("Quitter");
		quitter.setBounds(240,110,120,23);
		quitter.setFont(police1);
		quitter.addActionListener(this);
		
		hors_connexion=new JButton("Mode hors connexion");
		hors_connexion.setBounds(320,10,170,23);
		hors_connexion.setFont(police1);
		hors_connexion.addActionListener(this);
		panel_principal.add(hors_connexion,0);

		//contenu du panel identification:
		panel_identification.setBackground(Color.GRAY);
		panel_identification.add(label_login);
		panel_identification.add(label_pwd);
		panel_identification.add(login);
		panel_identification.add(pwd);
		panel_identification.add(se_connecter);
		panel_identification.add(quitter);
		panel_identification.add(mot_de_passe_oublie);
		panel_identification.setVisible(true);

		//Contenu du panel principal:
		panel_principal.add(panel_identification, new Integer(1));

		//gestion du clavier
		se_connecter.addKeyListener(this);
		login.addKeyListener(this);
		pwd.addKeyListener(this);
		quitter.addKeyListener(this);
		mot_de_passe_oublie.addKeyListener(this);
		

		//Ajout du panel principal e la fenetre du menu:
		add(panel_principal, BorderLayout.CENTER);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		Object source = e.getSource();
		if(source == se_connecter){
			connect();
		}
		else if(source==hors_connexion){
			new FenetrePlateau();
			this.setVisible(false);
		}
		else if(source==quitter){
			quit();
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		Object source = e.getSource();
		if(source == mot_de_passe_oublie){

		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		Object src = arg0.getSource();
		
	 if(src==mot_de_passe_oublie){
			Browser.open("http://alexandre.frantz57.free.fr/forgotten_password.php");
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

	private void connect() {
		int c = Connexion.connexion(login.getText(), new String(pwd.getPassword()));
		if(c!=0)
		{
			new FenetrePlateau(c+"");
			this.setVisible(false);
		}
	}


	@Override
	public void keyPressed(KeyEvent arg0) {
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		Object src = arg0.getSource();
		if(arg0.getKeyCode()==KeyEvent.VK_ESCAPE||(src==quitter&&arg0.getKeyCode()==KeyEvent.VK_ENTER)) quit();
		if(src==se_connecter||src==login||src==pwd){
			if(arg0.getKeyCode()==KeyEvent.VK_ENTER&&i_err++%2==0) connect();
		}
	}


	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	public JButton getHors_connexion() {
		return hors_connexion;
	}

	public void setHors_connexion(JButton hors_connexion) {
		this.hors_connexion = hors_connexion;
	}
}