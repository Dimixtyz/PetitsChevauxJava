package fr.cases;

import fr.joueur.Pion;

/**
 * Classe des cases de chemin
 * @author Quentin Fontaine
 */

public class CaseDeChemin extends Case {
	
	/**
	 * Constructeur d'une case de chemin
	 */
	public CaseDeChemin(){
		
	}
	
	/**
	 * Vérifie si un pion peut passer une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut passer la case, faux (false) sinon.
	 */
	public boolean peutPasser(Pion p) {
		/*Par defaut le pion peut passer la case*/
		boolean peutPasser = true;
		
		/*Vérifie qu'il n'y a pas de pions d'une autre couleur sur la case*/
		for(Pion x : super.getChevaux()) {
			
			if(x.getCouleur() != p.getCouleur()) {
				peutPasser = false;
			}
			
		}
		
		return peutPasser;
	}
	
	/**
	 * Vérifie si un pion peut s'arrêter sur une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut s'arrêter sur cette case, faux (false) sinon.
	 */
	public boolean peutSArreter(Pion p) { 
		/* Un pion peut toujours s'arrêter sur une case de chemin*/
		return true;
	}
	

}
