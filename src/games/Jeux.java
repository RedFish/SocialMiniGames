package games;


import java.util.ArrayList;
import java.util.Observable;

import autres.Timer;
import bdd.SubmitAchievements;
import bdd.SubmitScore;

public class Jeux extends Observable {
	private final int temps_init = 30;
	private int temps_restant = 0;
	private int score = 0;
	private int no_jeu = 1;
	private int max_jeu;
	private boolean new_game;
	private boolean trainning;
	private ArrayList<String> trophees_debloques;
	private int level=1;
	

	Timer timer;
	final String id_user;
	
	JeuNumerique modele_jeu_numerique;
	JeuDeLettres modele_jeu_de_lettre;
	JeuGeographie modele_jeu_geographie;
	
	public Jeux(String id_user,int niveau) {
		this.id_user=id_user;
		setLevel(niveau);
		modele_jeu_numerique = new JeuNumerique(getLevel());
		modele_jeu_de_lettre = new JeuDeLettres(getLevel());
		modele_jeu_geographie = new JeuGeographie(getLevel());
		max_jeu=3;
		trainning=false;
		temps_restant=temps_init;
		trophees_debloques = new ArrayList<String>();
	}

	public Jeux(String id_user,int niveau,int jeu) {
		this(id_user,niveau);
		no_jeu=jeu;
		max_jeu=jeu;
		trainning=true;
	}
	
	public void test_score()
	{
		if (score > 20)  trophees_debloques.add("Score > 20"); 
		if (score > 50) trophees_debloques.add("Score > 50");  
		if (score > 100) trophees_debloques.add("Score > 100");
	}
	
	public int getTemps_restant() {
		return temps_restant;
	}

	public void setTemps_restant(int temps_restant) {
		this.temps_restant = temps_restant;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getNo_jeu() {
		return no_jeu;
	}

	public void setNo_jeu(int no_jeu) {
		this.no_jeu = no_jeu;
	}

	public JeuNumerique getModele_jeu_numerique() {
		return modele_jeu_numerique;
	}

	public void setModele_jeu_numerique(JeuNumerique modele_jeu_numerique) {
		this.modele_jeu_numerique = modele_jeu_numerique;
	}

	public JeuDeLettres getModele_jeu_de_lettre() {
		return modele_jeu_de_lettre;
	}

	public void setModele_jeu_de_lettre(JeuDeLettres modele_jeu_de_lettre) {
		this.modele_jeu_de_lettre = modele_jeu_de_lettre;
	}

	public JeuGeographie getModele_jeu_geographie() {
		return modele_jeu_geographie;
	}

	public void setModele_jeu_geographie(JeuGeographie modele_jeu_geographie) {
		this.modele_jeu_geographie = modele_jeu_geographie;
	}

	public int getTemps_init() {
		return temps_init;
	}

	public boolean isNew_game() {
		return new_game;
	}

	public void setNew_game(boolean new_game) {
		this.new_game = new_game;
	}

	public void start() {
		new_game=true;
		setChanged();
		notifyObservers();
		timer = new Timer(this);
	}

	public void execute() {
		new_game=false;
		temps_restant--;
		temps_restant=temps_restant+modele_jeu_geographie.getBonus_temps()+modele_jeu_numerique.getBonus_temps()+modele_jeu_de_lettre.getBonus_temps();
		modele_jeu_geographie.setBonus_temps(0);
		modele_jeu_numerique.setBonus_temps(0);
		modele_jeu_de_lettre.setBonus_temps(0);
		score=modele_jeu_numerique.getScore()+modele_jeu_de_lettre.getScore()+modele_jeu_geographie.getScore();

		setChanged();
		notifyObservers();
		if(temps_restant<=0){
			temps_restant=temps_init;
			timer.pause();
			next();
		}
	}
	
	public void next(){
		new_game=true;
		no_jeu++;
		if(no_jeu<=max_jeu){
			setChanged();
			notifyObservers();
			timer.run();
		}
		else{
			setChanged();
			notifyObservers();
			if(!trainning) 
				{
					SubmitScore.enregistrerScore(id_user, score+"");
					SubmitAchievements.enregistrerTrophees(id_user, trophees_debloques);
				}
		}
	}

	public int getMax_jeu() {
		return max_jeu;
	}

	public boolean isTrainning() {
		return trainning;
	}

	public void setTrainning(boolean trainning) {
		this.trainning = trainning;
	}

	public ArrayList<String> getTrophees_debloques() {
		return trophees_debloques;
	}

	public void setTrophees_debloques(ArrayList<String> trophees_debloques) {
		this.trophees_debloques = trophees_debloques;
	}

	
	public int getHighScore() {
		return score;
	}

	public void pause() {
		if(timer.isRunning()){
			timer.pause();
		}
		else{
			timer = new Timer(this);
		}
		new_game=false;
		setChanged();
		notifyObservers();
	}

	public void reset() {
		setTemps_restant(getTemps_init());
		new_game=false;
		setChanged();
		notifyObservers();
		
	}

	public Timer getTimer() {
		return timer;
	}

	public void maj() {
		score=modele_jeu_numerique.getScore()+modele_jeu_de_lettre.getScore()+modele_jeu_geographie.getScore();
		setChanged();
		notifyObservers();
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	
}
