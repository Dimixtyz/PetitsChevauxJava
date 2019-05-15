package petitschevaux;

public class CaseDEchelle extends CaseColoree {
	
	public CaseDEchelle(Couleur c) {
		super(c);
		
	}
	
	/**
	 * Verifie si un pion peut passer une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut passer la case, faux (false) sinon.
	 */
	public boolean peutPasser(Pion p) {
		/* On ne peut pas sauter de case echelle */
		return false;
	}
	
	/**
	 * Verifie si un pion peut s'arreter sur une case
	 * @param p un pion
	 * @return vrai (true) si le pion peut s'arreter sur cette case, faux (false) sinon.
	 */
	public boolean peutSArreter(Pion p) {
		/* S'il n'y a aucun pion le pion peut s'arreter sur la case */
		if(this.getChevaux().size() == 0)
			return true;
		else
			return false;
	}

}
