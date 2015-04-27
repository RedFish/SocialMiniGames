package games;


import java.awt.Color;
import java.awt.Component;
import java.awt.Point;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;

import autres.Pays;

import components.PopUp;

public class JeuGeographie extends Observable {
	private String filePath = "games/db_geo.txt";
	private ArrayList<Pays> pays;
	private int type_de_choix;
	private int no_pays_juste;
	private ArrayList<Integer> no_pays_faux;
	private int score;
	private int difficulte;
	private int multiplicateur;
	private int compteur;
	private int bonus_temps;

	public JeuGeographie(int difficulte) {
		Scanner scanner = null;
		try{
			scanner = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath));
		}
		catch (Exception e){
			System.err.println("Dictinnaire \"" + filePath + "\" introuvable.");
			return;
		}

		pays = new ArrayList<Pays>();
		while(scanner.hasNext()){
			String s_tab[]=scanner.nextLine().split("_");
			if(!s_tab[0].contains("**")&&s_tab[0].length()>1)
				pays.add(
						new Pays(
								s_tab[1].trim(),
								s_tab[0].trim(),
								new Point(Integer.valueOf(s_tab[2].split(",")[0].trim()),Integer.valueOf(s_tab[2].split(",")[1].trim())),
								new Color(Integer.valueOf(s_tab[3].split(",")[0].trim()),Integer.valueOf(s_tab[3].split(",")[1].trim()),Integer.valueOf(s_tab[3].split(",")[2].trim()))
								)
						);
		}

		scanner.close();

		compteur=0;
		this.difficulte=difficulte;
		if(difficulte!=1&&difficulte!=2&&difficulte!=3) this.difficulte=1;
		score=0;
	}

	public void init() {
		int limit = 4;
		switch(difficulte){
		case 1:
			limit = 20;
			break;
		case 2:
			limit = 35;
			break;
		case 3:
			limit = 49;
			//limit = pays.size();
			break;
		}
		no_pays_juste = new Random().nextInt(limit);
		no_pays_faux = new ArrayList<Integer>();
		while(no_pays_faux.size()<3){
			int i = new Random().nextInt(limit);
			if(!no_pays_faux.contains(i)&&i!=no_pays_juste) no_pays_faux.add(i);
		}
		type_de_choix = new Random().nextInt(3);
		setChanged();
		notifyObservers();
	}

	public void check(String s,Color c,Component cmp){
		boolean juste = false;
		switch(type_de_choix){
		case 0://trouver le pays
			juste=s.equals(pays.get(no_pays_juste).getNom());
			break;
		case 1://trouver la capitale
			juste=s.equals(pays.get(no_pays_juste).getCapitale());
			break;
		case 2://trouver la localisation
			Color c1 = pays.get(no_pays_juste).getCouleur();
			int b=2;
			juste=c1.getRed()-b<c.getRed()&&c.getRed()<c1.getRed()+b&&
					c1.getGreen()-b<c.getGreen()&&c.getGreen()<c1.getGreen()+b&&
					c1.getBlue()-b<c.getBlue()&&c.getBlue()<c1.getBlue()+b;
			break;
		}

		if(juste){
			compteur++;
			bonus_temps=0;
			if(compteur<3){
				multiplicateur=1;
			}
			else if(compteur<6){
				multiplicateur=2;
				bonus_temps=1;
			}
			else if(compteur<9){
				multiplicateur=3;
				bonus_temps=3;
			}
			else if(compteur<12){
				multiplicateur=4;
				bonus_temps=5;
			}
			else{
				multiplicateur=5;
				bonus_temps=8;
			}
			score+=multiplicateur;
			PopUp.good(cmp);
			if(multiplicateur>1) PopUp.multiplicateur(cmp,multiplicateur);
			if(bonus_temps>0) PopUp.bonusTemps(cmp,bonus_temps);
		}
		else{
			if(compteur>0) compteur=0;
			else compteur--;
			bonus_temps=0;
			multiplicateur = 0;
			if(compteur>-3){	
			}
			else if(compteur>-6){
				bonus_temps=-1;
			}
			else if(compteur>-9){
				bonus_temps=-3;
			}
			else if(compteur>-12){
				bonus_temps=-5;
			}
			else{
				bonus_temps=-8;
			}
			PopUp.bad(cmp);
			if(bonus_temps<0) PopUp.malusTemps(cmp, bonus_temps);
		}
		init();
	}

	public void next() {
		init();
	}

	public int getTypeDeChoix() {
		return type_de_choix;
	}

	public void setTypeDeChoix(int type_de_choix) {
		this.type_de_choix = type_de_choix;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNoPaysJuste() {
		return no_pays_juste;
	}

	public void setNoPaysJuste(int no_pays) {
		this.no_pays_juste = no_pays;
	}

	public int getDifficulte() {
		return difficulte;
	}

	public void setDifficulte(int difficulte) {
		this.difficulte = difficulte;
	}

	public int getNoPaysFaux(int i) {
		return no_pays_faux.get(i);
	}

	public void setNoPaysFaux(ArrayList<Integer> no_pays_faux) {
		this.no_pays_faux = no_pays_faux;
	}

	public ArrayList<Pays> getPays() {
		return pays;
	}

	public void setPays(ArrayList<Pays> pays) {
		this.pays = pays;
	}

	public int getMultiplicateur() {
		return multiplicateur;
	}

	public void setMultiplicateur(int multiplicateur) {
		this.multiplicateur = multiplicateur;
	}

	public int getCompteur() {
		return compteur;
	}

	public void setCompteur(int compteur) {
		this.compteur = compteur;
	}

	public int getBonus_temps() {
		return bonus_temps;
	}

	public void setBonus_temps(int bonus_temps) {
		this.bonus_temps = bonus_temps;
	}
}
