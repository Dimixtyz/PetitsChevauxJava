package petitschevaux;

import java.util.ArrayList;

public abstract class Case {
	
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
	 * @param p le pion a ajouter
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
	 * Verifie si un pion peut passer la case
	 */
	public abstract boolean peutPasser(Pion p);
	
	/**
	 * Verifie si un pion peut s'arreter sur la case
	 */
	public abstract boolean peutSArreter(Pion p);
	
	/**
	 * Fonction pour retirer un cheval de la case
	 * @param p un pion
	 */
	public void retirerCheval(Pion p) {
		/*Verifie que le pion est bien present sur la case */
		for(Pion x : chevaux) {
			if(x == p)
				chevaux.remove(p);
		}
		
	}

}
