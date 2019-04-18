package petitschevaux;

public enum Couleur {
	
	ROUGE ('R',"Rouge"),
	JAUNE ('J',"Jaune"),
	VERT ('V',"Vert"),
	BLEU ('B',"Bleu");
	   
	private char symbol;
	private String nom;
	 
	//Constructeur
	Couleur(char symbol, String nom){
	  this.symbol = symbol;
	  this.nom = nom;
	}
	   
	public char getSymbol(){
	  return symbol;
	}
	
	public String getNom() {
		return nom;
	}
}
