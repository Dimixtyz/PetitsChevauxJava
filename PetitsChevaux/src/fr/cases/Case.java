package fr.cases;

import java.util.ArrayList;

import fr.joueur.Pion;

/**
 * Classe abstraite des cases
 * @author Quentin Fontaine
 */

public abstract class Case {
	/**
	 * Tableau comportant les chevaux sur une case
	 */
	private ArrayList<Pion> chevaux = new ArrayList<Pion>();
	
	/**
	 * Constructeur d'une case
	 */
	public Case() {
		
	}
	
	/**
	 * Getter des pions de la case
	 * @return la liste des pions sur la case
	 */
	public ArrayList<Pion> getChevaux() {
		return chevaux;
	}
	
	/**
	 * Fonction pour ajouter un pion sur la case
	 * @param p le pion à ajouter
	 */
	public void ajouteCheval(Pion p) {
		boolean deja = false;
		
		for(Pion x : chevaux) {
			if(x == p)
				deja = true;
		}
		
		if(!deja)
			chevaux.add(p);
	}
	
	/**
	 * Vérifie si un pion peut passer la case
	 */
	public abstract boolean peutPasser(Pion p);
	
	/**
	 * Vérifie si un pion peut s'arrêter sur la case
	 */
	public abstract boolean peutSArreter(Pion p);
	
	/**
	 * Fonction pour retirer un cheval de la case
	 * @param p un pion
	 */
	public void retirerCheval(Pion p) {
		/*Vérifie que le pion est bien présent sur la case */
		for(int i = 0; i < chevaux.size(); i++) {
			if(chevaux.get(i) == p)
				chevaux.remove(p);
		}
		
	}

}
