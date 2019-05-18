package fr.cases;

import fr.jeu.Couleur;
import fr.joueur.Pion;

/**
 * Classe des cases d'écuries
 * @author Quentin Fontaine
 */

public class CaseEcurie extends CaseColoree {
	
	/**
	 * Constructeur d'une case d'écurie
	 * @param c Couleur de la case d'écurie
	 */
	public CaseEcurie(Couleur c) {
		super(c);
	}
	
	/**
	 * Vérifie si un pion peut passer une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut passer la case, faux (false) sinon.
	 */
	public boolean peutPasser(Pion p) {
		/*Un pion peut sortir de l'écurie seulement si c'est celle de sa couleur*/
		if(p.getCouleur() == this.getCouleur())
			return true;
		else
			return false;
	}
	
	/**
	 * Vérifie si un pion peut s'arrêter sur une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut s'arrêter sur cette case, faux (false) sinon.
	 */
	public boolean peutSArreter(Pion p) {
		/*Un pion peut s'arrêter sur une case écurie seulement si c'est celle de sa couleur*/
		if(p.getCouleur() == this.getCouleur())
			return true;
		else
			return false;
	}

}
