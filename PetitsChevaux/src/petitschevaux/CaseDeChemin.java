package petitschevaux;

/**
 * Classe des case de chemin 
 * @author Quentin Fontaine
 */

public class CaseDeChemin extends Case {
	
	public CaseDeChemin(){
		
	}
	
	/**
	 * Verifie si un pion peut passer case
	 * @param p un pion
	 * @return vrai (true) si le pion peut passer la case, faux (false) sinon.
	 */
	public boolean peutPasser(Pion p) {
		 /*Par defaut le pion peut passer la case*/
		boolean peutPasser = true;
		
		/*On verifie qu'il n'y a pas de pion d'une autre couleur sur la case*/
		for(Pion x : super.getChevaux()) {
			
			if(x.getCouleur() != p.getCouleur()) {
				peutPasser = false;
			}
			
		}
		
		return peutPasser;
	}
	
	/**
	 * Verifie si un pion peut s'arreter sur une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut s'arreter sur cette case, faux (false) sinon.
	 */
	public boolean peutSArreter(Pion p) { 
		/* Un Pion peut toujours s'arreter sur une case de chemin*/
		return true;
	}
	

}
