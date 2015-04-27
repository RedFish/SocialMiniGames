package games;

import java.awt.Component;
import java.util.Observable;
import java.util.Random;

import components.PopUp;

public class JeuNumerique extends Observable
{
  	private Integer score = 0;
  	private Integer const1;
  	private Integer const2;
  	private Integer inconnue;
  	private char operateur;
  	private Integer indice;
  	private Integer reponseFausse1;
  	private Integer reponseFausse2;
  	private Integer reponseFausse3;
  	private boolean fin = false;  	
  	private String repFausse1;
  	private String repFausse2;
  	private String repFausse3;
  	private String inconnueBis;
  	private Integer difficulteJeu = 2;
  	private Integer compteur = 0;
  	private boolean new_game;
	private int bonus_temps;
	private int multiplicateur;
  	
	public JeuNumerique(int level) {
		difficulteJeu=level;
	}

	public void lancerJeu()
	{
		createEquation();
		new_game=true;
		setChanged();
		notifyObservers();
		new_game=false;
	}
        	
	private void createEquation()
	{
		int difficulteGrandeurNb = 0;
		int diffRepFausse = 0;
		
		
		switch(difficulteJeu)
		{
		case 1: // Cas le plus facile
			difficulteGrandeurNb = 50;
			diffRepFausse = 50;
			break;
		case 2: // Cas moyen
			difficulteGrandeurNb = 100;
			diffRepFausse = 20;
			break;
		case 3: // Cas difficile
			difficulteGrandeurNb = 150;
			diffRepFausse = 20;
			
		}	
		
		Random randomgenerator = new Random();
						
		int rand3 = randomgenerator.nextInt(3);
		int rand4 = randomgenerator.nextInt(4);
		int rand5 = randomgenerator.nextInt(5);
		
		const1 = randomgenerator.nextInt(difficulteGrandeurNb)+1;
		const2 = randomgenerator.nextInt(difficulteGrandeurNb)+1;
		inconnue = randomgenerator.nextInt(difficulteGrandeurNb);
				
		switch(rand5) {
		
		case 0 : // Cas d'une addition
			
			while (const1 >= const2) {
				const1 = randomgenerator.nextInt(100)+1;
			}
			
			operateur = '+';
			
			switch(rand3) {
			
			case 0 : // Cas de la premiere operande
				inconnue = const2 - const1;
				indice = 1;
				break;
			
			case 1 : // Cas de la deuxieme operande
				inconnue = const2 - const1;
				indice = 2;
				break;
			
			case 2 : // Cas du resulat
				inconnue = const1 + const2;
				indice = 3;
				break;
			}
				
			break;
			
			
		case 1 : // Cas d'une soustraction
		
			while (const2 >= const1) {
				const2 = randomgenerator.nextInt(difficulteGrandeurNb);
			}
			
			operateur = '-';
			
			switch(rand3) {
			
			case 0 : // Cas de la premiere operande
				inconnue = const2 + const1;
				indice = 1;
				break;
			
			case 1 : // Cas de la deuxieme operande
				inconnue = const1 - const2;
				indice = 2;
				break;
			
			case 2 : // Cas du resulat
				inconnue = const1 - const2;
				indice = 3;
				break;
			}
			
			break;
			
		case 2 : // Cas d'une multiplication
			
			const1 = randomgenerator.nextInt(difficulteGrandeurNb/10)+1;
			const2 = (randomgenerator.nextInt(difficulteGrandeurNb/10)+1)*const1;
			
			while (const2 > diffRepFausse || const1 > diffRepFausse || const1 == 0 || const2 == 0) {
				const1 = randomgenerator.nextInt(difficulteGrandeurNb/10)+1;
				const2 = (randomgenerator.nextInt(difficulteGrandeurNb/10)+1)*const1;
			}
			
			operateur = '*';
			
			switch(rand3) {	
			
			
			case 0 : // Cas de la premiere operande
				inconnue = const2 / const1;
				indice = 1;
				break;
			
			case 1 : // Cas de la deuxieme operande
				inconnue = const2 / const1;
				indice = 2;
				break;
			
			case 2 : // Cas du resulat
				inconnue = const1 * const2;
				indice = 3;
				break;
			}
			
		break;
		
		case 3 : // Cas d'une divison
			
			const2 = randomgenerator.nextInt(difficulteGrandeurNb/10)+1;
			const1 = (randomgenerator.nextInt(difficulteGrandeurNb/20)+1)*const2;	
		
			
			while (const1 > diffRepFausse || const2 > diffRepFausse || const1 == 0 || const2 == 0)
			{
				const2 = randomgenerator.nextInt(difficulteGrandeurNb/10)+1;
				const1 = (randomgenerator.nextInt(difficulteGrandeurNb/20)+1)*const2;	
			}
			
			operateur = '/';				
				
			switch(rand3) {	
						
			
			case 0 : // Cas de la premiere operande
				inconnue = const2 * const1;
				indice = 1;
				break;
			
			case 1 : // Cas de la deuxieme operande
				inconnue = const1 / const2;
				indice = 2;
				break;
			
			case 2 : // Cas du resulat
				inconnue = const1 / const2;
				indice = 3;
				break;
			}
			
			break;
			
		case 4 : // Cas de recherche de l'operateur
			indice = 4;
			switch(rand4) {				
			
			case 0 : // Cas de l'addition			
				inconnue = const2 + const1;			
				break;
			
			case 1 : // Cas de la soustraction
				
				while (const2 >= const1) 
				{
					const2 = randomgenerator.nextInt(difficulteGrandeurNb);
				}
				
				inconnue = const1 - const2;				
				break;
			
			case 2 : // Cas de la multiplication
				
				const2 = randomgenerator.nextInt(difficulteGrandeurNb/10)+1;
				const1 = (randomgenerator.nextInt(difficulteGrandeurNb/20)+1)*const2;
				
				while (const1 > diffRepFausse || const2 > diffRepFausse || const1 == 0 || const2 == 0) 
				{
					const2 = randomgenerator.nextInt(difficulteGrandeurNb/10)+1;
					const1 = (randomgenerator.nextInt(difficulteGrandeurNb/20)+1)*const2;	
				}
				
				inconnue = const1 * const2;
				break;
			
			case 3 : // Cas de la division
				const2 = randomgenerator.nextInt(difficulteGrandeurNb/10)+1;
				const1 = (randomgenerator.nextInt(difficulteGrandeurNb/20)+1)*const2;
				
				while (const1 > diffRepFausse || const2 > diffRepFausse || const1 == 0 || const2 == 0) {
					const2 = randomgenerator.nextInt(difficulteGrandeurNb/10)+1;
					const1 = (randomgenerator.nextInt(difficulteGrandeurNb/20)+1)*const2;	
				}
				
				inconnue = const1 / const2;
				break;
			}
			
			
		break;
		}	
		
		if (rand5 != 4)
		{	
			int id = 0;
			switch (difficulteJeu)
			{
			case 1: // Les reponses n'ont a priori aucun rapport avec l'inconnue
				
				reponseFausse1 = inconnue + randomgenerator.nextInt(diffRepFausse)+1;
				reponseFausse2 = inconnue - randomgenerator.nextInt(diffRepFausse)-1;
				reponseFausse3 = inconnue + randomgenerator.nextInt(diffRepFausse)+1;
							
				while (reponseFausse1 ==  reponseFausse2 || reponseFausse1 == reponseFausse3 || reponseFausse2 == reponseFausse3 ||
					 reponseFausse1 == inconnue || reponseFausse2 == inconnue || reponseFausse3 == inconnue || reponseFausse2 <= 0)
				{
					reponseFausse1 = inconnue + randomgenerator.nextInt(diffRepFausse)+1;
					reponseFausse2 = inconnue - randomgenerator.nextInt(diffRepFausse)+10;
					reponseFausse3 = inconnue + randomgenerator.nextInt(diffRepFausse)+1;
				}
				
				break;
				
			case 2: 
				
				if (inconnue % 7 == 0)	{id = 7;} // Cas du modulo 7
				else if (inconnue % 5 == 0) {id = 5;} // Cas du modulo 5
				else if (inconnue % 3 == 0) {id = 3;} // Cas du modulo 3
				else if (inconnue % 2 == 0) {id = 2;} // Cas du modulo 2 
				else {id = 1;} // Autres cas
				
				reponseFausse1 = inconnue + id*randomgenerator.nextInt(diffRepFausse/id)+id;
				reponseFausse2 = inconnue - id*randomgenerator.nextInt(diffRepFausse/id)+id*id;
				reponseFausse3 = inconnue + id*randomgenerator.nextInt(diffRepFausse/id)+id;
			
				while (reponseFausse1 ==  reponseFausse2 || reponseFausse1 == reponseFausse3 || reponseFausse2 == reponseFausse3 ||
						 reponseFausse1 == inconnue || reponseFausse2 == inconnue || reponseFausse3 == inconnue || reponseFausse2 <= 0)
					{
						reponseFausse1 = inconnue + id*randomgenerator.nextInt(diffRepFausse/id)+id;
						reponseFausse2 = inconnue - id*randomgenerator.nextInt(diffRepFausse/id)+id*id;
						reponseFausse3 = inconnue + id*randomgenerator.nextInt(diffRepFausse/id)+id;
					}	
				break;

			case 3:
				
			
				if (inconnue % 7 == 0)	{id = 7;} // Cas du modulo 7
				else if (inconnue % 5 == 0) {id = 5;} // Cas du modulo 5
				else if (inconnue % 3 == 0) {id = 3;} // Cas du modulo 3
				else if (inconnue % 2 == 0) {id = 2;} // Cas du modulo 2 
				else {id = 1;} // Autres cas
				
				reponseFausse1 = inconnue + id*randomgenerator.nextInt(diffRepFausse/id)+id;
				reponseFausse2 = inconnue - id*randomgenerator.nextInt(diffRepFausse/id)+id*id;
				reponseFausse3 = inconnue + id*randomgenerator.nextInt(diffRepFausse/id)+id;
			
				while (reponseFausse1 ==  reponseFausse2 || reponseFausse1 == reponseFausse3 || reponseFausse2 == reponseFausse3 ||
						 reponseFausse1 == inconnue || reponseFausse2 == inconnue || reponseFausse3 == inconnue || reponseFausse2 <= 0)
					{
						reponseFausse1 = inconnue + id*randomgenerator.nextInt(diffRepFausse/id)+id;
						reponseFausse2 = inconnue - id*randomgenerator.nextInt(diffRepFausse/id)+id*id;
						reponseFausse3 = inconnue + id*randomgenerator.nextInt(diffRepFausse/id)+id;
					}
				break;
			
			}
			
			repFausse1 = ""+reponseFausse1;
			repFausse2 = ""+reponseFausse2;
			repFausse3 = ""+reponseFausse3;
			inconnueBis = ""+inconnue;
			
		}		

		else
		{
			if (rand4 == 0) // Addition
			{
				inconnueBis = "+";
				repFausse1 = "-";
				repFausse2 = "*";
				repFausse3 = "/";
			}
			
			else if (rand4 == 1) // Soustraction
			{
				inconnueBis = "-";
				repFausse1 = "+";
				repFausse2 = "*";
				repFausse3 = "/";
			}
			
			else if (rand4 == 2) // Multiplication
			{
				inconnueBis = "*";
				repFausse1 = "-";
				repFausse2 = "+";
				repFausse3 = "/";
			}
			
			else // Division
			{
				inconnueBis = "/";
				repFausse1 = "-";
				repFausse2 = "*";
				repFausse3 = "+";
			}
		}
	}

	
	public String afficherEquation()
	{		
		switch(indice)
		{
			case 1:
				return new String("? " + operateur + " " + const1 + " = " + const2);
			case 2:
				return new String(const1 + " " + operateur + " ? = " + const2);
			case 3:
				return new String(const1 + " " + operateur + " " + const2 + " = ?");
			case 4:
				return new String(const1 + " ? " + const2 + " = " + inconnue);
		}
		return null;
	}
	
	public Integer getScore()
	{
		return score;
	}

	public Integer getConst1()
	{
		return const1;
	}

	public Integer getConst2()
	{
		return const2;
	}

	public Integer getInconnue()
	{
		return inconnue;
	}
	
	public char getOperateur()
	{
		return operateur;
	}

	public Integer getIndice()
	{
		return indice;
	}

	public Integer getReponseFausse1()
	{
		return reponseFausse1;
	}

	public Integer getReponseFausse2()
	{
		return reponseFausse2;
	}

	public Integer getReponseFausse3()
	{
		return reponseFausse3;
	}

	public boolean isFin()
	{
		return fin;
	}
	
	public String getRepFausse1() 
	{
		return repFausse1;
	}

	public String getRepFausse2()
	{
		return repFausse2;
	}

	public String getRepFausse3()
	{
		return repFausse3;
	}

	public String getInconnueBis()
	{
		return inconnueBis;
	}
	
	public Integer getDifficulteJeu() 
	{
		return difficulteJeu;
	}
	
	public Integer getCompteur() {
		return compteur;
	}

	public int getBonus_temps() {
		return bonus_temps;
	}

	public void setBonus_temps(int bonus_temps) {
		this.bonus_temps = bonus_temps;
	}
	
	public boolean checkResulat(String resultat, Component cmp)
	{
		boolean result = false;
		
		if (resultat.equals("/") && inconnue == const1 / const2 )
		{
			result = true;
		}
		
		if (resultat.equals("*") && inconnue == const1 * const2 )
		{
			result = true;
		}
		
		if (resultat.equals("-") && inconnue == const1 - const2 )
		{
			result = true;
		}
		
		if (resultat.equals("+") && inconnue == const1 + const2 )
		{
			result = true;
		}
		
		if (!resultat.equals("/") && !resultat.equals("*") && !resultat.equals("-") && !resultat.equals("+") && inconnue == Integer.valueOf(resultat)) 
		{
			result = true;
		}	
		
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
			
			return result;
		}


	public boolean isNew_game() {
		return new_game;
	}

	public void setNew_game(boolean new_game) {
		this.new_game = new_game;
	}

	
}



