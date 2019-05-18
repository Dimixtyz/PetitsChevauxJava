package fr.joueur;

import fr.jeu.Couleur;

/**
 * Classe Pion du jeu des petits chevaux
 * @author Quentin Fontaine
 */

public class Pion {
	
	/**
	 * Identifiant du pion
	 */
	private String id;
	/**
	 * Couleur du pion
	 */
	private Couleur couleur;
	
	/**
	 * Constructeur d'un pion
	 * @param id code unique pour identifier un pion
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
	
	/**
	 * Getter de l'identifiant du pion
	 * @return l'identifiant du pion
	 */
	public String getId() {
		return this.id;
	}
	

}
