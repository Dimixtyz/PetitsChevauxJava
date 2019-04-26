package petitschevaux;

public class CaseEcurie extends CaseColoree {
	
	public CaseEcurie(Couleur c) {
		super(c);
	}
	
	/**
	 * Verifie si un pion peut passer case
	 * @param p un pion
	 * @return vrai (true) si le pion peut passer la case, faux (false) sinon.
	 */
	public boolean peutPasser(Pion p) {
		/*Un pion peut sortir de l'ecurie seulement si c'est celle de sa couleur*/
		if(p.getCouleur() == this.getCouleur())
			return true;
		else
			return false;
	}
	
	/**
	 * Verifie si un pion peut s'arreter sur une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut s'arreter sur cette case, faux (false) sinon.
	 */
	public boolean peutSArreter(Pion p) {
		/*Un pion peut s'arreter sur une case ecurie seulement si c'est celle de sa couleur*/
		if(p.getCouleur() == this.getCouleur())
			return true;
		else
			return false;
	}

}
