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
			 * couleurChoisi : Couleur selectionn�e par l'utilisateur
			 * couleursDisponible : ArrayList des couleurs disponibles � la selection
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
				
				System.out.println("Quel est le nom du joueur "+(i+1));
				nomChoisi = sc.nextLine();
				
				
				/* Definition de la couleur du joueur */
				int rep = -1;
				
				/* Verification que l'entier rentrer est bien un numero de couleur propos� */
				while(rep < 0 || rep >= couleursDisponible.size()) { 
					
					System.out.println("Choissiser une des couleurs suivantes : ");
					for(int j = 0; j < couleursDisponible.size(); j++) {
						System.out.println(j+" "+couleursDisponible.get(j).getNom());
					}
					
					
					try {
						rep = sc.nextInt();
					}catch (InputMismatchException exception) { 
					    System.out.println("Mauvaise entr�e");
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
			
			
		}
		plateau.setJoueurs(joueurs);/*On passe la liste des joueurs pour l'affichage*/
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
		
		for(int i = 0; i< 4; i++) {
			
			if(i< joueurs.size()) {
				plateau.addCaseEcuries(new CaseEcurie(joueurs.get(i).getCouleur()));
			}
			else
				plateau.addCaseEcuries(new CaseEcurie(Couleur.BLANC));
			
		}
		
		/* Cases Echelles */ 
		 
		ArrayList<CaseDEchelle> CE = new ArrayList<CaseDEchelle>();
		
		/* On recree 4 fois l'ArrayList, une fois pour chaque couleur */
		for(int i = 0; i< 4; i++) {
			
			CE = new ArrayList<CaseDEchelle>();
			
			if(i< joueurs.size()) {
				for(int j = 0; j<6; j++) {
					CE.add(new CaseDEchelle(joueurs.get(i).getCouleur()));
				}
			}
			else {
				for(int j = 0; j<6; j++) {
					CE.add(new CaseDEchelle(Couleur.BLANC));
				}
			}
			
			plateau.addCaseEchelles(CE);
		}
		
		/* Initialisation cases de depart des joueurs */
		int numCaseDepart = 0;
		
		for(Joueur j : joueurs) {
			
			j.setCaseDeDepart(plateau.getChemin().get(numCaseDepart));
			
			numCaseDepart += 14;
		}
		
		/*Initialisation des pions du joueur sur le plateau*/
		for(int i = 0; i < joueurs.size(); i++) {
			
			for(Pion p : joueurs.get(i).getChevaux()) {
				plateau.getEcuries().get(i).ajouteCheval(p);
			}
			
		}
		
	}
	
	/**
	 * Retourne aleatoirement un chiffre entre 1 et 6
	 */
	private int lancerDe() {
		return de.nextInt(6) + 1;
	}
	
	/**
	 * Fonction qui permet de jouer un tour de la partie
	 */
	public void jouerUnTour() {
		
		int resultatDe = lancerDe();
		
		Pion pionABouger = joueurCourrant.choisirPion(resultatDe, plateau);
		
		if(pionABouger != null) {/*Si le joueur peux jouer un pion*/
			
			Case caseDArriver = null;
			
			/*Si le pion est a l ecurie*/
			for(Case cases : plateau.getEcuries()) {
				if(cases.getChevaux().indexOf(pionABouger) != -1)
					caseDArriver = joueurCourrant.getCaseDeDepart();
			}
			
			/*Si le pion est sur le plateau*/
			for(Case cases : plateau.getChemin()) {
				if(cases.getChevaux().indexOf(pionABouger) != -1)
					caseDArriver = plateau.getChemin().get((plateau.getChemin().indexOf(cases)+resultatDe));
			}
			
			/*Si le pion est sur une echelle*/
			for(ArrayList<CaseDEchelle> ech : plateau.getEchelles()) {
				for(Case cases : ech) {
					if(cases.getChevaux().indexOf(pionABouger) != -1)
						caseDArriver = ech.get((plateau.getChemin().indexOf(cases)+resultatDe));
				}
			}
			
			
			plateau.deplacerPionA(pionABouger, caseDArriver);
		}
		
		/*Si le de a fait un 6 le joueur rejoue*/
		if(resultatDe == 6) {
			
			resultatDe = lancerDe();
			
			pionABouger = joueurCourrant.choisirPion(resultatDe, plateau);
			
			if(pionABouger != null) {/*Si le joueur peux jouer un pion*/
				
				Case caseDArriver = null;
				
				/*Si le pion est a l ecurie*/
				for(Case cases : plateau.getEcuries()) {
					if(cases.getChevaux().indexOf(pionABouger) != -1)
						caseDArriver = joueurCourrant.getCaseDeDepart();
				}
				
				/*Si le pion est sur le plateau*/
				for(Case cases : plateau.getChemin()) {
					if(cases.getChevaux().indexOf(pionABouger) != -1)
						caseDArriver = plateau.getChemin().get((plateau.getChemin().indexOf(cases)+resultatDe));
				}
				
				/*Si le pion est sur une echelle*/
				for(ArrayList<CaseDEchelle> ech : plateau.getEchelles()) {
					for(Case cases : ech) {
						if(cases.getChevaux().indexOf(pionABouger) != -1)
							caseDArriver = ech.get((plateau.getChemin().indexOf(cases)+resultatDe));
					}
				}
				
				
				plateau.deplacerPionA(pionABouger, caseDArriver);
			}
		}
		
		setJoueurCourrant(joueurCourrant);
		
		
	}
	
	/**
	 * Verifie si la partie est finie
	 * @return Vrai si elle est finie, faux sinon
	 */
	public boolean estPartieTermine() {
		
		for(ArrayList<CaseDEchelle> listesCE : plateau.getEchelles()) {
			boolean victoire = true;
			
			/*On regarde sur les 4 dernieres cases de l'echelle s il y a un pion*/
			for(int i = 2; i<6; i++) {
				/*S il n'y a pas de pion sur une des cases finales*/
				if(listesCE.get(i).getChevaux().size() != 1) {
					victoire = false;
				}
			}
			
			if(victoire) {
				System.out.println("Victoire du "+listesCE.get(0).getCouleur().getNom());
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * Retourne le joueur qui est en train de jouer
	 */
	public Joueur getJoueurCourrant() {
		return joueurCourrant;
	}
	
	/**
	 * Definie le joueur suivant qui va jouer
	 * @param joueurActuel le joueur qui vient de joueur
	 */
	public void setJoueurCourrant(Joueur joueurActuel) {
		int numJ = joueurs.indexOf(joueurActuel);
		numJ = (numJ+1)%4;
		this.joueurCourrant = joueurs.get(numJ);
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
	 * Fonction qui retourne a leur ecurie les pions present sur une case
	 * @param c case pour laquel les pions vont retourner � leur �curie
	 */
	private void mangerLesPions(Case c) {
		
		for(Pion p : c.getChevaux()) {
			/*On ajoute le cheval � la case ecuries de sa couleur puis on le retire de la case*/
			for(CaseEcurie ce : plateau.getEcuries()) {
				if(p.getCouleur() == ce.getCouleur()) {
					ce.ajouteCheval(p);
					c.retirerCheval(p);
				}
			}
			
		}
	}

}
