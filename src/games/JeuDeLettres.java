/**
 * Représente les données d'un jeu de lettres.
 */

package games;

import java.awt.Component;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import components.PopUp;

public class JeuDeLettres extends Observable
{
	private String filePath = "games/dico336531.txt";	// Un dico de 336531 mots
	private ArrayList<String> motsDictionnaire;
	private ArrayList<String> motsPossibles;
	private ArrayList<String> lettres;
	private String motSoumis;
	private Integer nombreMotsPossibles;
	private Integer nombreMotsTrouves;
	private Integer nombreLettresProposees;
	private Integer score;
	private boolean result;
	private int multiplicateur;
	private int bonus_temps;
	private int compteur;
	private int difficulte;
	
	public int getBonus_temps() {
		return bonus_temps;
	}

	public void setBonus_temps(int bonus_temps) {
		this.bonus_temps = bonus_temps;
	}

	public JeuDeLettres(int level)
	{
		nombreLettresProposees = 7;	// Dépendra du niveau
		score = 0;
		motsDictionnaire = new ArrayList<String>();
		motsPossibles = new ArrayList<String>();
		lettres = new ArrayList<String>();
		motSoumis = new String();
		Scanner scanner = null;
		if(level == 1)
			difficulte = 2;
		else if(level == 2)
			difficulte = 3;
		else
			difficulte = 4;
		
		try
		{
			scanner = new Scanner(Thread.currentThread().getContextClassLoader().getResourceAsStream(filePath));
		}
		catch (Exception e)
		{
			System.err.println("Dictinnaire \"" + filePath + "\" introuvable.");
			return;
		}
		while(scanner.hasNext())
			motsDictionnaire.add(scanner.nextLine());
		scanner.close();
	}
	
	public void genererLettres()
	{
		motSoumis = "";
		lettres.clear();
		motsPossibles.clear();
		nombreMotsPossibles = 0;
		nombreMotsTrouves = 0;
		boolean fin = false;
		int i = 0;
		String caractere;
		Pattern pattern;
		Matcher matcher;
		
		while(!fin)
		{
			caractere = String.valueOf((char)((int)'a' + new Random().nextInt(26)));
			if (!lettres.contains(caractere))
			{
				lettres.add(String.valueOf(caractere));
				i++;
			}
			if(i==nombreLettresProposees)
				fin = true;
		}
		
		String motif = new String("(" + lettres.get(0));
		for(i=1; i<nombreLettresProposees; i++)
		{
			motif += "|" + lettres.get(i);
		}
		motif += ")+";
		pattern = Pattern.compile(motif);
		for(String mot : motsDictionnaire)
		{
			matcher = pattern.matcher(mot);
			if(matcher.matches() && mot.length() >= difficulte)
			{
				nombreMotsPossibles++;
				motsPossibles.add(mot);
			}
		}
		
		if(motsPossibles.size() <5)
			genererLettres();
		setChanged();
		notifyObservers();
	}
	
	public void verifierMot(Component cmp)
	{
		if(motsPossibles.remove(motSoumis))
		{
			nombreMotsTrouves++;
			score++;
			result = true;
		}
		
		else result = false;
		
		if(result){
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
			if(bonus_temps>0) PopUp.bonusTemps(cmp, bonus_temps);
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
		
			effacerMot();
	}
	
	public void ajouterLettre(String lettre)
	{
		motSoumis += lettre;
		setChanged();
		notifyObservers();
	}
	
	public void effacerMot()
	{
		motSoumis = "";
		setChanged();
		notifyObservers();
	}

	public ArrayList<String> getLettres()
	{
		return lettres;
	}

	public Integer getNombreLettresProposees()
	{
		return nombreLettresProposees;
	}

	public String getMotSoumis()
	{
		return motSoumis;
	}

	public Integer getScore()
	{
		return score;
	}

	public Integer getNombreMotsPossibles()
	{
		return nombreMotsPossibles;
	}

	public Integer getNombreMotsTrouves()
	{
		return nombreMotsTrouves;
	}

	public ArrayList<String> getMotsPossibles()
	{
		return motsPossibles;
	}
}