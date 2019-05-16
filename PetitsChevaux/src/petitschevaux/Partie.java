package petitschevaux;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Partie {
	
	private Random de;
	private Joueur joueurCourant;
	private Plateau plateau;
	private ArrayList<Joueur> joueurs;
	public static final Scanner sc = new Scanner(System.in);
	
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
				if(sc.hasNextLine())
					sc.nextLine();
			}
			
			
			
		}
		plateau.setJoueurs(joueurs);/*On passe la liste des joueurs pour l'affichage*/
		
		/*Definition du joueur qui commence*/
		this.joueurCourant = joueurs.get(de.nextInt(joueurs.size()));
		System.out.println(joueurCourant.getCouleur().getCCode()+joueurCourant.getNom()+" Commence !\033[0m");
		
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
		
		/*Affichage du plateau initial*/
		plateau.afficher();
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
		
		int nombreTours = 0;
		Pion pionABouger;
		int resultatDe;
		
		/*Recherche de l'echelle */
		int indiceDeLEchelle = 0;
		for(int j = 0; j<plateau.getEchelles().size(); j++) {
			if(plateau.getEchelles().get(j).get(0).getCouleur() == joueurCourant.getCouleur())
				indiceDeLEchelle = j;
		}
		
		do {
			nombreTours++;
			resultatDe = lancerDe();
			System.out.println();
			System.out.println("Resultat de : "+resultatDe);
			System.out.println(joueurCourant.getCouleur().getCCode()+"C'est le tour de : "+joueurCourant.getNom()+"\033[0m");
			pionABouger = joueurCourant.choisirPion(resultatDe, plateau);
			
			
			if(pionABouger != null) {/*Si le joueur peux jouer un pion*/
				
				Case caseDArriver = null;
				
				/*Si le pion est a l ecurie*/
				for(Case cases : plateau.getEcuries()) {
					if(cases.getChevaux().indexOf(pionABouger) != -1)
						caseDArriver = joueurCourant.getCaseDeDepart();
				}
				
				/*Si le pion est sur le plateau*/
				for(Case cases : plateau.getChemin()) {
					if(cases.getChevaux().indexOf(pionABouger) != -1) {
						
						/*Cas exceptionel echelle joueur 1*/
						if(plateau.getChemin().indexOf(cases) == 55 && joueurCourant == joueurs.get(0)) {
							caseDArriver = plateau.getEchelles().get(0).get(0);
						}
						
						/*Monter des echelle joueur 2/3/4*/
						else if(plateau.getChemin().indexOf(cases) == cases.getChevaux().indexOf(joueurCourant.getCaseDeDepart())-1)
							caseDArriver = plateau.getEchelles().get(indiceDeLEchelle).get(0);
						
						/*Passage de la case 55*/
						else if(plateau.getChemin().indexOf(cases)+resultatDe > 55)
							caseDArriver = plateau.getChemin().get((plateau.getChemin().indexOf(cases)+resultatDe)-56);
						
						/*Si il n y a pas de monter sur les echelles*/
						else {
							caseDArriver = plateau.getChemin().get((plateau.getChemin().indexOf(cases)+resultatDe));
						}
				
					}
				}
				
				
				/*Si le pion est sur une echelle*/
				for(ArrayList<CaseDEchelle> ech : plateau.getEchelles()) {
					for(Case cases : ech) {
						if(cases.getChevaux().indexOf(pionABouger) != -1)
							caseDArriver = ech.get((plateau.getChemin().indexOf(cases)+1));
					}
				}
				
				
				plateau.deplacerPionA(pionABouger, caseDArriver);
			}
			else {
				System.out.println("Vous ne pouvez pas jouer");
				sc.nextLine();
			}
			plateau.afficher();
		}while(resultatDe == 6 && nombreTours <= 1);/*Le joueur peut rejouer s'il fait un 6 mais seulement 1 fois maximum*/
		
		
		setjoueurCourant(joueurCourant);
		
		
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
	public Joueur getjoueurCourant() {
		return joueurCourant;
	}
	
	/**
	 * Definie le joueur suivant qui va jouer
	 * @param joueurActuel le joueur qui vient de joueur
	 */
	public void setjoueurCourant(Joueur joueurActuel) {
		int numJ = joueurs.indexOf(joueurActuel);
		numJ = (numJ+1)%joueurs.size();
		this.joueurCourant = joueurs.get(numJ);
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
