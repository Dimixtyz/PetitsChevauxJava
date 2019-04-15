package petitschevaux;

public enum Couleur {
	
	ROUGE ("Rouge"),
	JAUNE ("Jaune"),
	VERT ("Vert"),
	BLEU ("Bleu");
	   
	private String nom = "";
	 
	//Constructeur
	Couleur(String nom){
	  this.nom = nom;
	}
	   
	public String getNom(){
	  return nom;
	}
}
