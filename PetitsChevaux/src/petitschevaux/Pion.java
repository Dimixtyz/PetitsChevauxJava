package petitschevaux;

public class Pion {
	
	private String id;
	private Couleur couleur;
	
	/**
	 * Constructeur d'un pion
	 * @param id numero unique pour identifier un pion
	 * @param c couleur du pion
	 */
	public Pion(String id, Couleur c) {
		this.id = id;
		this.couleur = c;
	}
	
	/**
	 * Getter de la couleur d'un pion
	 * @return retourne la couleur du pion
	 */
	public Couleur getCouleur() {
		return this.couleur;
	}
	

}
