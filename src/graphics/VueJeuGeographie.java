package graphics;

import games.JeuGeographie;
import games.Jeux;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.Observer;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JSlider;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import components.ImagePanel;
import components.Ligne;

public class VueJeuGeographie extends JLayeredPane implements Observer,MouseMotionListener,MouseListener,ActionListener {
	private static final long serialVersionUID = 1L;
	JeuGeographie modele_geo;
	Jeux modele_jeux;
	JLayeredPane panel_pays,panel_capitale,panel_map,panel_zoom,panel_question;
	ImagePanel panel_image,panel_image_zoom,panel_image_position,panel_image_position_zoom;
	TitledBorder border_panel_pays,border_panel_capitale,border_panel_map;
	Image image_map,image_map_HD,image_map_HD_src,image_position,image_position_zoom;
	JLayeredPane ligne_x,ligne_y;
	JSlider slider;
	JLabel lbl_zoom_in,lbl_zoom_out,lbl_info1,lbl_info2,lbl_score1,lbl_score2;
	JButton btn[],btn_passer;
	Point position;

	int width,height,widthHD,heightHD;
	final double scl = 0.1777;
	final double scale = 0.55;
	double scaleHD = 0.5;


	public VueJeuGeographie(JeuGeographie jg,Jeux j) {
		this.modele_geo=jg;
		this.modele_geo.addObserver(this);
		this.modele_jeux=j;
		this.modele_jeux.addObserver(this);

		//Pays
		panel_pays = new JLayeredPane();
		border_panel_pays =BorderFactory.createTitledBorder("Pays");
		panel_pays.setBorder(border_panel_pays);
		panel_pays.setBounds(5, 0, 390, 200);
		add(panel_pays);

		//Capitale
		panel_capitale = new JLayeredPane();
		border_panel_capitale =BorderFactory.createTitledBorder("Capitale");
		panel_capitale.setBorder(border_panel_capitale);
		panel_capitale.setBounds(405, 0, 390, 200);
		add(panel_capitale);

		//Map
		panel_map = new JLayeredPane();
		border_panel_map =BorderFactory.createTitledBorder("Localisation");
		panel_map.setBorder(border_panel_map);
		panel_map.setBounds(5, 205, 790, 370);
		add(panel_map);
		image_map = new ImageIcon(getClass().getResource("map1.png")).getImage();
		width = image_map.getWidth(null);
		height = image_map.getHeight(null);
		panel_image = new ImagePanel(image_map.getScaledInstance((int) (width*scale), (int) (height*scale), 0));
		panel_image.setBounds(4, 18, 780, 346);
		panel_image.addMouseMotionListener(this);
		panel_image.addMouseListener(this);
		panel_map.add(panel_image);

		//Zoom
		panel_zoom = new JLayeredPane();
		panel_zoom.setBounds(4, 214, 150, 150);
		panel_map.add(panel_zoom,0);

		image_map_HD_src = new ImageIcon(getClass().getResource("mapHD1.png")).getImage();
		widthHD = image_map_HD_src.getWidth(null);
		heightHD = image_map_HD_src.getHeight(null);
		image_map_HD=image_map_HD_src.getScaledInstance((int) (widthHD*scaleHD), (int) (heightHD*scaleHD), 0);
		panel_image_zoom = new ImagePanel(image_map_HD);
		panel_zoom.add(panel_image_zoom,0);

		//croix viser
		ligne_x = new Ligne(0,0,40,0);
		ligne_x.setBounds(150/2-20, 150/2, 40, 1);
		ligne_y = new Ligne(0,0,0,40);
		ligne_y.setBounds(150/2, 150/2-20, 1, 40);

		//Zoom slider
		slider = new JSlider();
		slider.setOrientation(SwingConstants.VERTICAL);
		slider.setBounds(20, 20, 15, 180);
		slider.addMouseListener(this);
		slider.setMaximum(90);
		slider.setMinimum(40);
		slider.setValue((int)(scaleHD*100));
		panel_map.add(slider,0);
		lbl_zoom_in = new JLabel("+");
		lbl_zoom_out = new JLabel("-");
		lbl_zoom_in.setBounds(35, 25, 20, 20);
		lbl_zoom_out.setBounds(35, 175, 20, 20);
		panel_map.add(lbl_zoom_in,0);
		panel_map.add(lbl_zoom_out,0);

		//Question
		panel_question = new JLayeredPane();
		panel_question.setBounds(5, 30, 380, 150);
		btn = new JButton[4];
		for(int i=0;i<4;i++){
			btn[i] = new JButton("Reponse"+(i+1));
			btn[i].addActionListener(this);
			panel_question.add(btn[i]);
		}
		btn[0].setBounds(10, 10, 175, 60);
		btn[1].setBounds(195, 10, 175, 60);
		btn[2].setBounds(10, 80, 175, 60);
		btn[3].setBounds(195, 80, 175, 60);

		panel_pays.add(panel_question);

		btn_passer = new JButton("Passer");
		btn_passer.setBounds(690, 25, 90, 25);
		btn_passer.addActionListener(this);
		panel_map.add(btn_passer,0);

		//Info
		lbl_info1 = new JLabel("Info1");
		lbl_info1.setBounds(5, 80, 380, 30);
		lbl_info1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		lbl_info2 = new JLabel("Info2");
		lbl_info2.setBounds(5, 80, 380, 30);
		lbl_info2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

		panel_capitale.add(lbl_info1);

		//Position
		image_position = new ImageIcon(getClass().getResource("position.png")).getImage();
		panel_image_position = new ImagePanel(image_position);

		image_position_zoom = image_position.getScaledInstance(image_position.getWidth(null), image_position.getHeight(null), 0);
		panel_image_position_zoom = new ImagePanel(image_position_zoom);

		lbl_score1 = new JLabel("Score :");
		lbl_score2 = new JLabel("0");
		lbl_score1.setBounds(715, 45, 70, 20);
		lbl_score2.setBounds(765, 45, 70, 20);
		panel_map.add(lbl_score1,0);
		panel_map.add(lbl_score2,0);

		modele_geo.init();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
		if(arg0 instanceof JeuGeographie){
			JeuGeographie jg = (JeuGeographie) arg0;
			Color c1 = Color.BLACK;
			Color c2 = Color.BLUE;
			lbl_score2.setText(jg.getScore()+"");

			panel_pays.removeAll();
			panel_capitale.removeAll();
			panel_map.remove(panel_image_position);
			switch(jg.getTypeDeChoix()){
			case 0://trouver le pays
				border_panel_pays.setTitle("Selectionnez le bon pays");
				border_panel_pays.setTitleColor(c2);
				border_panel_capitale.setTitle("Capitale");
				border_panel_capitale.setTitleColor(c1);
				border_panel_map.setTitle("Localisation");
				border_panel_map.setTitleColor(c1);

				int c = new Random().nextInt(4);
				btn[c].setText(jg.getPays().get(jg.getNoPaysJuste()).getNom());
				for(int i=1;i<4;i++) btn[(c+i)%4].setText(jg.getPays().get(jg.getNoPaysFaux(i-1)).getNom());
				panel_pays.add(panel_question);

				lbl_info2.setText(jg.getPays().get(jg.getNoPaysJuste()).getCapitale());
				panel_capitale.add(lbl_info2);

				position = jg.getPays().get(jg.getNoPaysJuste()).getPosition();
				panel_image_position.setBounds(
						(int)(position.x*scl)-image_position_zoom.getWidth(null)/2,
						(int)(position.y*scl)-image_position_zoom.getHeight(null)+18,
						image_position.getWidth(null),
						image_position.getHeight(null)
						);
				panel_map.add(panel_image_position,0);
				break;
			case 1://trouver la capitale
				border_panel_pays.setTitle("Pays");
				border_panel_pays.setTitleColor(c1);
				border_panel_capitale.setTitle("Selectionnez la bonne capitale");
				border_panel_capitale.setTitleColor(c2);
				border_panel_map.setTitle("Localisation");
				border_panel_map.setTitleColor(c1);

				c = new Random().nextInt(4);
				btn[c].setText(jg.getPays().get(jg.getNoPaysJuste()).getCapitale());
				for(int i=1;i<4;i++) btn[(c+i)%4].setText(jg.getPays().get(jg.getNoPaysFaux(i-1)).getCapitale());
				panel_capitale.add(panel_question);

				lbl_info1.setText(jg.getPays().get(jg.getNoPaysJuste()).getNom());
				panel_pays.add(lbl_info1);

				position = jg.getPays().get(jg.getNoPaysJuste()).getPosition();
				panel_image_position.setBounds(
						(int)(position.x*scl)-image_position_zoom.getWidth(null)/2,
						(int)(position.y*scl)-image_position_zoom.getHeight(null)+18,
						image_position.getWidth(null),
						image_position.getHeight(null)
						);
				panel_map.add(panel_image_position,0);
				break;
			case 2://trouver la localisation
				border_panel_pays.setTitle("Pays");
				border_panel_pays.setTitleColor(c1);
				border_panel_capitale.setTitle("Capitale");
				border_panel_capitale.setTitleColor(c1);
				border_panel_map.setTitle("Cliquez sur le bon pays");
				border_panel_map.setTitleColor(c2);

				lbl_info1.setText(jg.getPays().get(jg.getNoPaysJuste()).getNom());
				panel_pays.add(lbl_info1);

				lbl_info2.setText(jg.getPays().get(jg.getNoPaysJuste()).getCapitale());
				panel_capitale.add(lbl_info2);

				position = null;
				break;
			}
			panel_question.validate();
			panel_pays.validate();
			panel_capitale.validate();
			panel_map.validate();

			repaint();
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {}

	@Override
	public void mouseMoved(MouseEvent arg0) {

		JLayeredPane panel_zoom_tmp = new JLayeredPane();
		panel_zoom_tmp.setBounds(4, 214, 150, 150);

		ImagePanel panel_image_zoom_tmp = new ImagePanel(image_map_HD);
		panel_image_zoom_tmp.setBounds(
				-arg0.getPoint().x*((int) (widthHD*scaleHD))/((int) (width*scale))+(int) (150/2),
				-arg0.getPoint().y*((int) (heightHD*scaleHD))/((int) (height*scale))+(int) (150/2),
				widthHD,
				heightHD
				);

		panel_zoom.removeAll();
		panel_map.remove(panel_zoom);

		panel_zoom=panel_zoom_tmp;
		panel_image_zoom=panel_image_zoom_tmp;
		panel_map.add(panel_zoom,0);
		panel_zoom.add(panel_image_zoom,0);
		panel_zoom.add(ligne_x,0);
		panel_zoom.add(ligne_y,0);

		if(position!=null){
			panel_image_position_zoom.setBounds(
					-arg0.getPoint().x*((int) (widthHD*scaleHD))/((int) (width*scale))+(int) (150/2)+(int) (position.x*scaleHD)-image_position_zoom.getWidth(null)/2, 
					-arg0.getPoint().y*((int) (heightHD*scaleHD))/((int) (height*scale))+(int) (150/2)+(int) (position.y*scaleHD)-image_position_zoom.getHeight(null),
					image_position_zoom.getWidth(null),
					image_position_zoom.getHeight(null)
					);
			panel_zoom.add(panel_image_position_zoom,0);
		}


		panel_image_zoom.validate();
		panel_zoom.validate();
		panel_map.validate();
	}

	@Override
	public void mouseClicked(MouseEvent arg0) {
		try{
			if(modele_geo.getTypeDeChoix()==2&&arg0.getSource()==panel_image){
				int x=(int)((arg0.getPoint().x*((int) (widthHD*scaleHD))/((int) (width*scale)))/scaleHD);
				int y=(int)((arg0.getPoint().y*((int) (heightHD*scaleHD))/((int) (height*scale)))/scaleHD);

				BufferedImage bufferedImage = toBufferedImage(image_map_HD);
				int rgb = bufferedImage.getRGB((int)(x*scaleHD), (int)(y*scaleHD));
				int rouge = (rgb >>16 ) & 0xFF;
				int vert = (rgb >> 8 ) & 0xFF;
				int bleu = rgb & 0xFF;
				Color c = new Color(rouge,vert,bleu);

				Color c1 = new Color(0,0,0);
				Color c2 = new Color(255,255,255);
				Color c3 = new Color(181,220,251);
				int b = 2;
				if(!((c1.getRed()-b<c.getRed()&&c.getRed()<c1.getRed()+b&&
						c1.getGreen()-b<c.getGreen()&&c.getGreen()<c1.getGreen()+b&&
						c1.getBlue()-b<c.getBlue()&&c.getBlue()<c1.getBlue()+b)||(
								c2.getRed()-b<c.getRed()&&c.getRed()<c2.getRed()+b&&
								c2.getGreen()-b<c.getGreen()&&c.getGreen()<c2.getGreen()+b&&
								c2.getBlue()-b<c.getBlue()&&c.getBlue()<c2.getBlue()+b)||(
										c3.getRed()-b<c.getRed()&&c.getRed()<c3.getRed()+b&&
										c3.getGreen()-b<c.getGreen()&&c.getGreen()<c3.getGreen()+b&&
										c3.getBlue()-b<c.getBlue()&&c.getBlue()<c3.getBlue()+b))){
					modele_geo.check(null,c,this);
					modele_jeux.maj();
				}
			}

		}catch(OutOfMemoryError e){
			mouseClicked(arg0);
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {
		panel_zoom.removeAll();
		panel_map.removeAll();
		panel_map.add(panel_image);
		panel_map.add(slider,0);
		panel_map.add(lbl_zoom_in,0);
		panel_map.add(lbl_zoom_out,0);
		if(position!=null)panel_map.add(panel_image_position,0);
		panel_map.add(btn_passer,0);
		panel_map.add(lbl_score1,0);
		panel_map.add(lbl_score2,0);
		panel_image_zoom.validate();
		panel_zoom.validate();
		panel_map.validate();
	}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		Object src = arg0.getSource();
		if(src==slider){
			scaleHD=( double)slider.getValue()/100;
			image_map_HD = image_map_HD_src.getScaledInstance((int) (widthHD*scaleHD), (int) (heightHD*scaleHD), 0);
		}
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		Object src = arg0.getSource();
		if(src==btn_passer){
			modele_geo.next();
			modele_jeux.maj();
		}
		else{
			for(int i=0;i<4;i++){
				if(src==btn[i]) modele_geo.check(btn[i].getText(),null,this);
				modele_jeux.maj();
			}
		}
	}

	//http://java.developpez.com/faq/gui/?page=graphique_general_images
	BufferedImage toBufferedImage(Image image) {
		/** On test si l'image n'est pas déja une instance de BufferedImage */
		if( image instanceof BufferedImage ) {
			return( (BufferedImage)image );
		} else {
			/** On s'assure que l'image est complètement chargée */
			image = new ImageIcon(image).getImage();

			/** On crée la nouvelle image */
			BufferedImage bufferedImage = new BufferedImage(
					image.getWidth(null),
					image.getHeight(null),
					BufferedImage.TYPE_INT_RGB);

			Graphics g = bufferedImage.createGraphics();
			g.drawImage(image,0,0,null);
			g.dispose();

			return( bufferedImage );
		} 
	}

	public void setScoreInvisible(){
		lbl_score1.setVisible(false);
		lbl_score2.setVisible(false);
	}
}
