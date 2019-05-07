package petitschevaux;

public enum Couleur {
	
	ROUGE ('R',"Rouge","\033[1;41m"),
	JAUNE ('J',"Jaune","\033[01;43m"),
	VERT ('V',"Vert","\033[1;42m"),
	BLEU ('B',"Bleu","\033[1;44m"),
	BLANC ('B',"Blanc","\033[40m");
	   
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
