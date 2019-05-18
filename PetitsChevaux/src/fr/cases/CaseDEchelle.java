package fr.cases;

import fr.jeu.Couleur;
import fr.joueur.Pion;

/**
 * Classe des cases d'échelles
 * @author Quentin Fontaine
 */

public class CaseDEchelle extends CaseColoree {
	/**
	 * Constructeur d'une case d'échelle
	 * @param c la couleur de la case
	 */
	public CaseDEchelle(Couleur c) {
		super(c);
		
	}
	
	/**
	 * Vérifie si un pion peut passer une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut passer la case, faux (false) sinon.
	 */
	public boolean peutPasser(Pion p) {
		/* On ne peut pas sauter de case échelle */
		return false;
	}
	
	/**
	 * Vérifie si un pion peut s'arrêter sur une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut s'arrêter sur cette case, faux (false) sinon.
	 */
	public boolean peutSArreter(Pion p) {
		/* S'il n'y a aucun pion, le pion peut s'arrêter sur la case */
		if(this.getChevaux().size() == 0)
			return true;
		else
			return false;
	}

}
