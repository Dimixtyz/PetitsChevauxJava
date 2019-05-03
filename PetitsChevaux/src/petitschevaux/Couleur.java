package petitschevaux;

public enum Couleur {
	
	ROUGE ('R',"Rouge","\033[1;31m"),
	JAUNE ('J',"Jaune","\033[01;33m"),
	VERT ('V',"Vert","\033[1;32m"),
	BLEU ('B',"Bleu","\033[34;01m"),
	BLANC ('B',"Blanc","\033[00m");
	   
	private char symbol;
	private String nom;
	private String codeCouleur;
	 
	/**
	 * Constructeur de couleur
	 * @param symbol premiere lettre de la couleur
	 * @param nom Nom complet de la couleur
	 */
	Couleur(char symbol, String nom, String cd){
	  this.symbol = symbol;
	  this.nom = nom;
	  this.codeCouleur = cd;
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
	
	public String getCCode() {
		return this.codeCouleur;
	}
}
