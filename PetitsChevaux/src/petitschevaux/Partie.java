package petitschevaux;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Partie {
	
	private Random de;
	private Joueur joueurCourrant;
	private Plateau plateau;
	private ArrayList<Joueur> joueurs;
	
	/**
	 * Initialisation des variables de la classe
	 */
	public Partie() {
		de = new Random();
		plateau = new Plateau();
		joueurs = new ArrayList<Joueur>();
	}
	
	/**
	 * Initialise les joueurs du jeu (remplit le tableau joueurs)
	 * @param nb nombre de joueurs
	 */
	public void initialiserJoueurs(int nb) throws PasDeJoueursException { 
		
		/* Exception s'il n'y a aucun joueur passer en parametre */
		if(nb == 0) {
			throw new PasDeJoueursException();
		}else {
			
			/*
			 * Definition des variables global de la fonction
			 * couleurChoisi : Couleur selectionnée par l'utilisateur
			 * couleursDisponible : ArrayList des couleurs disponibles à la selection
			 */
		
			Scanner sc = new Scanner(System.in);
			String nomChoisi;
			Couleur couleurChoisi;
			ArrayList<Couleur> couleursDisponible = new ArrayList<Couleur>();
			couleursDisponible.add(Couleur.BLEU);
			couleursDisponible.add(Couleur.JAUNE);
			couleursDisponible.add(Couleur.ROUGE);
			couleursDisponible.add(Couleur.VERT);
			
			/* On cree le nombre de joueurs passer en paramtre de la fonction */
			for(int i = 0; i < nb; i++) {  
				
				/*Definition du nom du joueur*/
				
				System.out.println("Qu'elle est le nom du joueur "+(i+1));
				nomChoisi = sc.nextLine();
				
				
				/* Definition de la couleur du joueur */
				int rep = -1;
				
				/* Verification que l'entier rentrer est bien un numero de couleur proposé */
				while(rep < 0 || rep > couleursDisponible.size()) { 
					
					System.out.println("Choissiser une des couleurs suivantes : ");
					for(int j = 0; j < couleursDisponible.size(); j++) {
						System.out.println(j+" "+couleursDisponible.get(j).getNom());
					}
					
					
					try {
						rep = sc.nextInt();
					}catch (InputMismatchException exception) { 
					    System.out.println("Mauvaise entrée");
					    sc.next();
					    rep = -1;
					}
				}
				
				couleurChoisi = couleursDisponible.get(rep);
				couleursDisponible.remove(rep);
				
					
				joueurs.add(new JoueurHumain(nomChoisi ,couleurChoisi));
				/* Vide le scanner */
				sc.nextLine();
			}
			
			sc.close();
			
			/* Initialisation des pions des joueurs */
			ArrayList<Pion> pionsDesJoueurs = new ArrayList<Pion>();
			String idPions = "";
			
			/*Pour chaque joueurs on vide la liste des pions et la remplit de 4 pions de leur couleur*/
			for(Joueur j : joueurs) {
				
				pionsDesJoueurs.clear();
				
				for(int k = 0; k < 4; k++) {
					idPions = j.getCouleur()+" "+(k+1);
					pionsDesJoueurs.add(new Pion(idPions, j.getCouleur()));
				}
				
				j.setChevaux(pionsDesJoueurs);
				
			}
			
		}
	}
	
	/**
	 * Initialise le plateau de jeu (remplit les differents types de cases de l'objet Plateau)
	 */
	public void initialiserPlateau() {
		
		/* Case Chemin */
		for(int i = 0; i<56; i++) {
		plateau.addCaseChemin(new CaseDeChemin());
		}
		
		/* Cases Ecurie */
		plateau.addCaseEcuries(new CaseEcurie(Couleur.ROUGE));
		plateau.addCaseEcuries(new CaseEcurie(Couleur.VERT));
		plateau.addCaseEcuries(new CaseEcurie(Couleur.JAUNE));
		plateau.addCaseEcuries(new CaseEcurie(Couleur.BLEU));
		
		/* Cases Echelles */ 
		 
		ArrayList<CaseDEchelle> CE = new ArrayList<CaseDEchelle>();
		
		/* On recrée 4 fois l'ArrayList, une fois pour chaque couleur */
		for(int i = 0; i<4; i++) {
			
			CE.clear();
			
			for(int j = 0; j<6; j++) {
				if(i==0)
					CE.add(new CaseDEchelle(Couleur.ROUGE));
				if(i==1)
					CE.add(new CaseDEchelle(Couleur.VERT));
				if(i==2)
					CE.add(new CaseDEchelle(Couleur.JAUNE));
				if(i==3)
					CE.add(new CaseDEchelle(Couleur.BLEU));
			}
			
			plateau.addCaseEchelles(CE);
		}
		
	}
	
	/**
	 * Retourne aléatoirement un chiffre entre 1 et 6
	 */
	private int lancerDe() {
		return de.nextInt(6) + 1;
	}
	
	/**
	 * Fonction qui permet de jouer un tour de la partie
	 */
	public void jouerUnTour() {
		
	}
	
	/**
	 * Verifie si la partie est finie
	 * @return Vrai si elle est finie, faux sinon
	 */
	public boolean estPartieTermine() {
		
		return false;//////////////////////////////////
	}
	
	/**
	 * Retourne le joueur qui est en train de jouer
	 */
	public Joueur getJoueurCourrant() {
		return joueurCourrant;
	}
	
	/**
	 * Definie le joueur qui va jouer
	 * @param j un joueur
	 */
	public void setJoueurCourrant(Joueur j) {
		this.joueurCourrant = j;
	}
	
	/**
	 * Retourne le plateau actuel
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
	 * Retourne la liste des joueur de la partie
	 */
	public ArrayList<Joueur> getJoueurs(){
		return joueurs;
	}
	
	/**
	 * Fonction qui retourne à leur ecurie les pions present sur une case
	 * @param c case pour laquel les pions vont retourner à leur écurie
	 */
	private void mangerLesPions(Case c) {
		
		for(Pion p : c.getChevaux()) {
			/*On ajoute le cheval à la case ecuries de sa couleur puis on le retire de la case*/
			for(CaseEcurie ce : plateau.getEcuries()) {
				if(p.getCouleur() == ce.getCouleur()) {
					ce.ajouteCheval(p);
				}
			}
			c.retirerCheval(p);
		}
	}

}
