package petitschevaux;

public enum Couleur {
	
	ROUGE ('R',"Rouge"),
	JAUNE ('J',"Jaune"),
	VERT ('V',"Vert"),
	BLEU ('B',"Bleu");
	   
	private char symbol;
	private String nom;
	 
	/**
	 * Constructeur de couleur
	 * @param symbol premiere lettre de la couleur
	 * @param nom Nom complet de la couleur
	 */
	Couleur(char symbol, String nom){
	  this.symbol = symbol;
	  this.nom = nom;
	}
	  
	/**
	 * Getter de la premiere lettre de la couleur
	 * @return retourne la premiere lettre de la couleur
	 */
	public char getSymbol(){
	  return symbol;
	}
	
	/**
	 * Getter du nom complet de la couleur
	 * @return retourne le nom complet de la couleur
	 */
	public String getNom() {
		return nom;
	}
}
