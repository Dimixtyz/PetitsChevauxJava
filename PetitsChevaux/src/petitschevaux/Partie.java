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
	 * Initialisation des variables
	 */
	
	public Partie() {
		de = new Random();
		plateau = new Plateau();
		joueurs = new ArrayList<Joueur>();
	}
	
	public void initialiserJoueurs(int nb) { 
		
		/**
		 * Definition des variables global de la fonction
		 * sc : Scanner des entrées de l'utilisateur
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
		
		for(int i = 0; i < nb; i++) {  // On cree le nombre de joueurs passer en paramtre de la fonction
			
			//Definition du nom du joueur
			
			System.out.println("Qu'elle est le nom du joueur "+(i+1));
			nomChoisi = sc.nextLine();
			
			
			/**
			 * Definition de la couleur du joueur
			 * rep : variable test de la reponse
			 */
			int rep = -1;
			
			
			while(rep < 0 || rep > couleursDisponible.size()) { // Verification que l'entier rentrer est bien un numero de couleur proposé
				
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
			sc.nextLine();// Vide le scanner 
		}
		
		sc.close();
		
	}
	
	public void initialiserPlateau() {
		
		/**
		 * Case Chemin
		 */
		
		for(int i = 0; i<56; i++) {
		plateau.addCaseChemin(new CaseDeChemin());
		}
		
		/**
		 * Cases Ecurie
		 */
		
		plateau.addCaseEcuries(new CaseEcurie(Couleur.ROUGE));
		plateau.addCaseEcuries(new CaseEcurie(Couleur.VERT));
		plateau.addCaseEcuries(new CaseEcurie(Couleur.JAUNE));
		plateau.addCaseEcuries(new CaseEcurie(Couleur.BLEU));
		
		/**
		 * Cases Echelles
		 * CE : Arraylist comportant les 6 cases d'une couleur
		 * On cree 4 ArrayList, une pour chaque couleur
		 */
		ArrayList<CaseDEchelle> CE = new ArrayList<CaseDEchelle>();
		
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
	
	private int lancerDe() {
		return de.nextInt(6) + 1;
	}
	
	public void jouerUnTour() {
		
	}
	
	public boolean estPartieTermine() {
		
		return false;//////////////////////////////////
	}
	
	public Joueur getJoueurCourrant() {
		return joueurCourrant;
	}
	
	public void setJoueurCourrant(Joueur j) {
		this.joueurCourrant = j;
	}
	
	public Plateau getPlateau() {
		return plateau;
	}
	
	public ArrayList<Joueur> getJoueurs(){
		return joueurs;
	}
	
	private void mangerLesPions(Case c) {
		
		/**
		 * Pour tous les pions p present sur la case c
		 * on les ajoute a l'ecurie qui correspond à leur 
		 * couleur et on les retire de la case c
		 */
		
		for(Pion p : c.getChevaux()) {
			
			for(CaseEcurie ce : plateau.getEcuries()) {
				if(p.getCouleur() == ce.getCouleur()) {
					ce.ajouteCheval(p);
				}
			}
			c.retirerCheval(p);
		}
	}

}
