package petitschevaux;

public class Pion {
	
	private String id;
	private Couleur couleur;
	
	public Pion(String id, Couleur c) {
		this.id = id;
		this.couleur = c;
	}
	
	public Couleur getCouleur() {
		return this.couleur;
	}
	

}
