package petitschevaux;

import java.util.ArrayList;

public abstract class Joueur {

	private String nom;
	private Couleur couleur;
	private Case caseDeDepart;
	private ArrayList<Pion> chevaux;
	
	
	public Joueur(String nom, Couleur couleur){
		this.nom = nom;
		this.couleur = couleur;
	}
	
	public Case getCaseDeDepart() {
		return caseDeDepart;
	}
	
	/**
	 * Defini la case de depart d'un joueur
	 * @param c la case de depart
	 */
	public void setCaseDeDepart(Case c) {
		this.caseDeDepart = c;
	}
	
	/**
	 * Getter des pions
	 * @return retourne les pions d'un joueur
	 */
	public ArrayList<Pion> getChevaux(){
		return chevaux;
	}
	
	/**
	 * Setter des pions
	 * @param pions la liste des pion d'un joueur
	 */
	public void setChevaux(ArrayList<Pion> pions) {
		this.chevaux = pions;
	}
	
	/**
	 * Getter du nom du joueur
	 * @return le nom du joueur
	 */
	public String getNom() {
		return nom;
	}
	
	/**
	 * Setter du nom du joueur
	 * @param nom le nom du joueur
	 */
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Getter de la couleur du joueur
	 * @return la couleur du joueur
	 */
	public Couleur getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Fonction de choix du pion a bouger
	 * @param valeurDe la valeur du de
	 * @param p le plateau de la partie
	 * @return retourne le pion que le joueur joue
	 */
	public abstract Pion choisirPion(int valeurDe, Plateau p);
}
