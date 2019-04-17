package petitschevaux;

public class CaseColoree extends Case {

	private Couleur couleur;
	
	public CaseColoree(Couleur c) {
		this.couleur = c;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public boolean peutPasser(Pion p) {
	
		return false; ////////////////////////////////
	}
	
	public boolean peutSArreter(Pion p) {
		
		return false; ////////////////////////////////
	}
	
}
