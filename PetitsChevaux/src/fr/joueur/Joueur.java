package fr.joueur;

import java.util.ArrayList;

import fr.cases.Case;
import fr.jeu.Couleur;
import fr.jeu.Plateau;

public abstract class Joueur {

	private String nom;
	private Couleur couleur;
	private Case caseDeDepart;
	private ArrayList<Pion> chevaux = new ArrayList<Pion>();
	
	/**
	 * Constructeur d'un joueur
	 * @param nom nom du joueur
	 * @param couleur couleur du joueur
	 */
	public Joueur(String nom, Couleur couleur){
		this.nom = nom;
		this.couleur = couleur;
		
		for(int k = 0; k < 4; k++) {
			String idPions = couleur.getNom()+" "+(k+1);
			chevaux.add(new Pion(idPions, couleur));
		}
		
	}
	
	/**
	 * Getter de la case de depart du joueur
	 * @return retourne la case de depart du joueur
	 */
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
