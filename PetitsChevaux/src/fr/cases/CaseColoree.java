package fr.cases;

import fr.jeu.Couleur;
import fr.joueur.Pion;

public abstract class CaseColoree extends Case {

	private Couleur couleur;
	
	/**
	 * Constructeur d'une case coloree
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
	 * Verifie si un pion peut passer la case
	 */
	public abstract boolean peutPasser(Pion p);
	
	/**
	 * Verifie si un pion peut s'arreter sur la case
	 */
	public abstract boolean peutSArreter(Pion p);
	
}
