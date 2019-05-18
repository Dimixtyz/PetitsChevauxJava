package fr.jeu;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

import fr.cases.Case;
import fr.cases.CaseDEchelle;
import fr.cases.CaseDeChemin;
import fr.cases.CaseEcurie;
import fr.exceptionpetitschevaux.CasePleineException;
import fr.exceptionpetitschevaux.ConflitDeCouleurException;
import fr.exceptionpetitschevaux.PasDeJoueursException;
import fr.joueur.Joueur;
import fr.joueur.JoueurHumain;
import fr.joueur.Pion;

/**
 * Classe d'une partie de petits chevaux
 * @author Quentin Fontaine
 */
public class Partie {
	
	/**
	 * Variable aléatoire pour simuler un dé
	 */
	private Random de;
	/**
	 * Joueur qui est en train de jouer
	 */
	private Joueur joueurCourant;
	/**
	 * Plateau de jeu de la partie
	 */
	private Plateau plateau;
	/**
	 * Tableau des joueurs de la partie
	 */
	private ArrayList<Joueur> joueurs;
	/**
	 * Scanner général des entrées
	 */
	public static final Scanner sc = new Scanner(System.in);
	
	/**
	 * Constructeur d'une partie, initialise les variables de la classe
	 */
	public Partie() {
		de = new Random();
		plateau = new Plateau();
		joueurs = new ArrayList<Joueur>();
	}
	
	/**
	 * Initialise les joueurs du jeu (remplit le tableau joueurs)
	 * @param nb nombre de joueurs
	 * @throws PasDeJoueursException Si le nombre de joueurs à initialiser est égal a 0
	 */
	public void initialiserJoueurs(int nb) throws PasDeJoueursException { 
		
		/* Exception s'il n'y a aucun joueur passer en paramètre */
		if(nb == 0) {
			throw new PasDeJoueursException();
		}else {
			
			/*
			 * Définition des variables globales de la fonction
			 * couleurChoisiee : Couleur sélectionnée par l'utilisateur
			 * couleursDisponibless : ArrayList des couleurs disponibles à la sélection
			 * nomChoisi : nom du joueur
			 */
			String nomChoisi;
			Couleur couleurChoisie;
			ArrayList<Couleur> couleursDisponibles = new ArrayList<Couleur>();
			couleursDisponibles.add(Couleur.BLEU);
			couleursDisponibles.add(Couleur.JAUNE);
			couleursDisponibles.add(Couleur.ROUGE);
			couleursDisponibles.add(Couleur.VERT);
			
			/* On crée le nombre de joueurs passer en paramètre de la fonction */
			for(int i = 0; i < nb; i++) {  
				
				/*Définition du nom du joueur*/
				System.out.println();
				System.out.println("Quel est le nom du joueur "+(i+1));
				nomChoisi = sc.nextLine();
				System.out.println();
				
				
				/* Définition de la couleur du joueur */
				int rep = -1;
				/* Vérification que l'entier rentré est bien un numéro de couleur proposée */
				while(rep < 0 || rep >= couleursDisponibles.size()) { 
					
					System.out.println("Choissisez une des couleurs suivantes : ");
					/*Affichage des couleurs disponibles*/
					for(int j = 0; j < couleursDisponibles.size(); j++) {
						System.out.println(j+" "+couleursDisponibles.get(j).getCCode()+couleursDisponibles.get(j).getNom()+"\033[0m");
					}
					
					/*Vérification que l'entrée est bien un entier*/
					try {
						rep = sc.nextInt();
					}catch (InputMismatchException exception) { 
					    System.out.println("Mauvaise entrée");
					    sc.next();
					    rep = -1;
					}
				}
				
				couleurChoisie = couleursDisponibles.get(rep);
				couleursDisponibles.remove(rep);
				
				/*Ajout du joueur au tableau de joueurs*/
				joueurs.add(new JoueurHumain(nomChoisi ,couleurChoisie));
				
				/* Vide le scanner */
				if(sc.hasNextLine())
					sc.nextLine();
				
			}
			
			
			
		}
		/*On passe la liste des joueurs pour l'affichage*/
		plateau.setJoueurs(joueurs);
		
		/*Définition du joueur qui commence*/
		this.joueurCourant = joueurs.get(de.nextInt(joueurs.size()));
		/*Affichage du joueur qui commence*/
		System.out.println(joueurCourant.getCouleur().getCCode()+joueurCourant.getNom()+" Commence !\033[0m");
		
	}
	
	/**
	 * Initialise le plateau de jeu (remplit les différents types de cases de l'objet plateau)
	 */
	public void initialiserPlateau() {
		
		/* Ajoute les cases de chemin au plateau */
		for(int i = 0; i<56; i++) {
		plateau.addCaseChemin(new CaseDeChemin());
		}
		
		/* Ajoute les cases d'écuries */
		for(int i = 0; i< 4; i++) {
			
			if(i< joueurs.size()) {
				plateau.addCaseEcuries(new CaseEcurie(joueurs.get(i).getCouleur()));
			}
			/*S'il n'y a pas de joueur pour une écurie une écurie blanche est créée*/
			else
				plateau.addCaseEcuries(new CaseEcurie(Couleur.BLANC));
			
		}
		
		/* Ajoute les cases d'échelles */ 
		 
		ArrayList<CaseDEchelle> CE = new ArrayList<CaseDEchelle>();
		
		/* On recrée 4 fois l'ArrayList, une fois pour chaque couleur */
		for(int i = 0; i< 4; i++) {
			
			CE = new ArrayList<CaseDEchelle>();
			
			if(i< joueurs.size()) {
				for(int j = 0; j<6; j++) {
					CE.add(new CaseDEchelle(joueurs.get(i).getCouleur()));
				}
			}
			/*S'il n'y a pas de joueur pour une échelle une échelle blanche est créée*/
			else {
				for(int j = 0; j<6; j++) {
					CE.add(new CaseDEchelle(Couleur.BLANC));
				}
			}
			
			plateau.addCaseEchelles(CE);
		}
		
		/* Initialisation des cases de départ des joueurs */
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
		
		/*Affichage du plateau initial*/
		plateau.afficher();
	}
	
	/**
	 * Fonction qui simule un lancer de dé
	 * @return retourne un nombre aléatoire entre 1 et 6
	 */
	private int lancerDe() {
		return de.nextInt(6) + 1;
	}
	
	/**
	 * Fonction qui permet de jouer un tour de la partie
	 * @throws ConflitDeCouleurException Si une des cases de chemin contient des chevaux de différentes couleurs
	 * @throws CasePleineException Si une case d'échelle où le pion va arriver comporte déjà un pion
	 */
	public void jouerUnTour() throws ConflitDeCouleurException, CasePleineException {
		
		/*Vérification qu'il n'y a pas de conflit de couleurs sur les cases de chemin*/
		Couleur verificationConflit = null;
		for(CaseDeChemin CDC : plateau.getChemin()) {
			/*On vérifie que tous les pions d'une case ont la même couleur sinon on déclenche l'exception*/
			for(int i = 0 ; i < CDC.getChevaux().size(); i++) {
				if(i == 0) {
					verificationConflit = CDC.getChevaux().get(i).getCouleur();
				}
				else {
					if(CDC.getChevaux().get(i).getCouleur() != verificationConflit) {
						throw new ConflitDeCouleurException("Plusieurs pions de différentes couleurs se trouvent sur la même case");
					}
				}
			}
		}
		/*Variable pour compter le nombre de tours d'un joueur*/
		int nombreTours = 0;
		Pion pionABouger;
		int resultatDe;
		
		/*Recherche de l'indice de l'échelle du joueur courant*/
		int indiceDeLEchelle = 0;
		for(int j = 0; j<plateau.getEchelles().size(); j++) {
			if(plateau.getEchelles().get(j).get(0).getCouleur() == joueurCourant.getCouleur())
				indiceDeLEchelle = j;
		}
		
		/*Le joueur courant peut jouer jusqu'à 2 tours s'il fait un 6*/
		do {
			nombreTours++;
			resultatDe = lancerDe();
			System.out.println();
			System.out.println("Resultat du dé : "+resultatDe);
			System.out.println(joueurCourant.getCouleur().getCCode()+"C'est le tour de : "+joueurCourant.getNom()+"\033[0m");
			/*Determination du pion à bouger*/
			pionABouger = joueurCourant.choisirPion(resultatDe, plateau);
			
			/*Si le joueur peut jouer un pion*/
			if(pionABouger != null) {
				/*Recherche de la case d'arrivée*/
				Case caseDArrivee = null;
				
				/*Si le pion est à l'écurie*/
				for(Case cases : plateau.getEcuries()) {
					if(cases.getChevaux().indexOf(pionABouger) != -1)
						caseDArrivee = joueurCourant.getCaseDeDepart();
				}
				
				/*Si le pion est sur le plateau*/
				for(Case cases : plateau.getChemin()) {
					if(cases.getChevaux().indexOf(pionABouger) != -1) {
						
						/*Cas exceptionnel, échelle du joueur 1*/
						if(plateau.getChemin().indexOf(cases) == 55 && joueurCourant == joueurs.get(0)) {
							caseDArrivee = plateau.getEchelles().get(0).get(0); 
						}
						
						/*Monter des échelles joueurs 2/3/4*/
						else if(plateau.getChemin().indexOf(cases) == plateau.getChemin().indexOf(joueurCourant.getCaseDeDepart())-1) {
							caseDArrivee = plateau.getEchelles().get(indiceDeLEchelle).get(0);
						}
						
						/*Passage de la case 55*/
						else if(plateau.getChemin().indexOf(cases)+resultatDe > 55) {
							caseDArrivee = plateau.getChemin().get((plateau.getChemin().indexOf(cases)+resultatDe)-56);
						}
						/*S'il n'y a pas de monter sur les échelles, ni de passage de la case 55*/
						else {
							caseDArrivee = plateau.getChemin().get((plateau.getChemin().indexOf(cases)+resultatDe));
						}
				
					}
				}
				
				
				/*Si le pion est sur une échelle*/
				for(ArrayList<CaseDEchelle> ech : plateau.getEchelles()) {
					for(Case cases : ech) {
						if(cases.getChevaux().indexOf(pionABouger) != -1) {
							caseDArrivee = ech.get((ech.indexOf(cases)+1));
							/*Vérification que la case n'est pas pleine :*/
							if(caseDArrivee.getChevaux().size()>= 1) {
								throw new CasePleineException("Case d'echelle deja pleine");
							}
						}
					}
				}
				
				
				/*S'il y a des pions d'une autre couleur sur la case d'arrivée, on les mange*/
				if(caseDArrivee.getChevaux().size() >= 1 && caseDArrivee.getChevaux().get(0).getCouleur() != joueurCourant.getCouleur()) {
					this.mangerLesPions(caseDArrivee);
				}
				/*On déplace le pion sur sa case d'arrivée*/
				plateau.deplacerPionA(pionABouger, caseDArrivee);
			}
			/*Si le joueur ne peut pas jouer*/
			else {
				System.out.println("Vous ne pouvez pas jouer");
				sc.nextLine();
			}
			plateau.afficher();
			
		}while(resultatDe == 6 && nombreTours <= 1);
		
		/*On définit le joueur suivant comme joueur courant*/
		setjoueurCourant(joueurCourant);
		
		
	}
	
	/**
	 * Vérifie si la partie est finie
	 * @return Vrai si elle est finie, faux sinon
	 */
	public boolean estPartieTermine() {
		/*Pour chaque échelle*/
		for(ArrayList<CaseDEchelle> listesCE : plateau.getEchelles()) {
			boolean victoire = true;
			
			/*On regarde sur les 4 dernières cases de l'échelle s'il y a un pion*/
			for(int i = 2; i<6; i++) {
				/*S'il n'y a pas de pion sur une des cases finales*/
				if(listesCE.get(i).getChevaux().size() != 1) {
					victoire = false;
				}
			}
			/*Affichage de victoire + retourne que c'est une partie terminée*/
			if(victoire) {
				System.out.println();
				System.out.println();
				System.out.println("Victoire du joueur "+listesCE.get(0).getCouleur().getCCode()+listesCE.get(0).getCouleur().getNom()+"\033[0m");
				System.out.println();
				return true;
			}
			
		}
		
		return false;
	}
	
	/**
	 * Fonction getter du joueur courant
	 * @return retourne le joueur courant
	 */
	public Joueur getjoueurCourant() {
		return joueurCourant;
	}
	
	/**
	 * Définit le joueur suivant qui va jouer
	 * @param joueurActuel le joueur qui vient de jouer
	 */
	public void setjoueurCourant(Joueur joueurActuel) {
		int numJ = joueurs.indexOf(joueurActuel);
		numJ = (numJ+1)%joueurs.size();
		this.joueurCourant = joueurs.get(numJ);
	}
	
	/**
	 * Fonction getter du plateau
	 * @return retourn le plateau de la partie
	 */
	public Plateau getPlateau() {
		return plateau;
	}
	
	/**
	 * Fonction getter des joueurs
	 * @return retourne la liste des joueurs de la partie
	 */
	public ArrayList<Joueur> getJoueurs(){
		return joueurs;
	}
	
	/**
	 * Fonction qui retourne a leur écurie les pions présents sur une case
	 * @param c case pour laquelle les pions vont retourner à leur écurie
	 */
	private void mangerLesPions(Case c) {
		
		for(Pion p : c.getChevaux()) {
			/*On ajoute le cheval à la case écurie de sa couleur*/
			for(CaseEcurie ce : plateau.getEcuries()) {
				if(p.getCouleur() == ce.getCouleur()) {
					ce.ajouteCheval(p);
				}
			}
		}
		/*On retire les chevaux qui étaient sur la case passée en paramètre*/
		c.getChevaux().clear();
	}

}
