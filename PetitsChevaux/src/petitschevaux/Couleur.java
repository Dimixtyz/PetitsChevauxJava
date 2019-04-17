package petitschevaux;

public enum Couleur {
	
	ROUGE ('R'),
	JAUNE ('J'),
	VERT ('V'),
	BLEU ('B');
	   
	private char symbol;
	 
	//Constructeur
	Couleur(char symbol){
	  this.symbol = symbol;
	}
	   
	public char getSymbol(){
	  return symbol;
	}
}
