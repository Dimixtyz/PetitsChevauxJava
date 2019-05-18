package fr.jeu;

/**
 * Enumération des couleurs
 * @author Quentin Fontaine
 */

public enum Couleur {
	
	ROUGE ('R',"Rouge","\033[1;41m",""),
	JAUNE ('J',"Jaune","\033[01;43m",""),
	VERT ('V',"Vert","\033[1;42m",""),
	BLEU ('B',"Bleu","\033[1;44m",""),
	BLANC ('B',"Blanc","\033[40m","\033[47m");
	
	/**
	 * Première lettre de la couleur
	 */
	private char symbol;
	/**
	 * Nom de la couleur
	 */
	private String nom;
	/**
	 * Code couleur shell correspondant à la couleur
	 */
	private String codeCouleur;
	/**
	 * Code couleur shell correspondant à la couleur secondaire
	 */
	private String codeCouleurSecondaire;
	 
	/**
	 * Constructeur de couleur
	 * @param symbol première lettre de la couleur
	 * @param nom Nom complet de la couleur
	 * @param cd Code shell de la couleur
	 * @param cdSec Code shell secondaire de la couleur
	 */
	Couleur(char symbol, String nom, String cd, String cdSec){
	  this.symbol = symbol;
	  this.nom = nom;
	  this.codeCouleur = cd;
	  this.codeCouleurSecondaire = cdSec;
	}
	  
	/**
	 * Getter de la première lettre de la couleur
	 * @return retourne la première lettre de la couleur
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
	
	/**
	 * Getter du code shell de la couleur
	 * @return retourne le code shell de la couleur
	 */
	public String getCCode() {
		return this.codeCouleur;
	}
	
	/**
	 * Getter du code shell de la couleur secondaire 
	 * @return retourne le code shell de la couleur secondaire
	 */
	public String getCCodeSec() {
		return this.codeCouleurSecondaire;
	}
}
