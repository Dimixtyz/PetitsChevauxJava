package fr.cases;

import fr.jeu.Couleur;
import fr.joueur.Pion;

/**
 * Classe abstraite des cases colorées
 * @author Quentin Fontaine
 */

public abstract class CaseColoree extends Case {
	/**
	 * Couleur de la case
	 */
	private Couleur couleur;
	
	/**
	 * Constructeur d'une case colorée
	 * @param c couleur de la case
	 */
	public CaseColoree(Couleur c) {
		this.couleur = c;
	}
	
	/**
	 * Getter de la couleur de la case
	 * @return retourne la couleur de la case
	 */
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Vérifie si un pion peut passer la case
	 */
	public abstract boolean peutPasser(Pion p);
	
	/**
	 * Vérifie si un pion peut s'arrêter sur la case
	 */
	public abstract boolean peutSArreter(Pion p);
	
}
