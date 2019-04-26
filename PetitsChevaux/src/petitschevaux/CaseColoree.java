package petitschevaux;

public abstract class CaseColoree extends Case {

	private Couleur couleur;
	
	public CaseColoree(Couleur c) {
		this.couleur = c;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	public abstract boolean peutPasser(Pion p);
	
	public abstract boolean peutSArreter(Pion p);
	
}
