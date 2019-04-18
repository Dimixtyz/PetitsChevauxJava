package petitschevaux;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Partie {
	
	private Random de = new Random();
	private Joueur joueurCourrant;
	private Plateau plateau;
	private ArrayList<Joueur> joueurs = new ArrayList<Joueur>();
	
	public Partie() {
		
	}
	
	public void initialiserJoueurs(int nb) { 
		
		/**
		 * Definition des variables global de la fonction
		 * sc : Scanner des entrées de l'utilisateur
		 * couleurChoisi : Couleur selectionnée par l'utilisateur
		 * couleursDisponible : ArrayList des couleurs disponibles à la selection
		 */
		
		Scanner sc = new Scanner(System.in);
		Couleur couleurChoisi;
		ArrayList<Couleur> couleursDisponible = new ArrayList<Couleur>();
		couleursDisponible.add(Couleur.BLEU);
		couleursDisponible.add(Couleur.JAUNE);
		couleursDisponible.add(Couleur.ROUGE);
		couleursDisponible.add(Couleur.VERT);
		
		for(int i = 0; i < nb; i++) {  // On cree le nombre de joueurs passer en paramtre de la fonction
			
			//Definition du nom du joueur
			String nomChoisi = "tets";
			System.out.println("Qu'elle est le nom du joueur "+(i+1));
			
			
			
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
				    System.out.println("Seulement des entiers");
				    sc.next();
				    rep = -1;
				}
			}
			
			couleurChoisi = couleursDisponible.get(rep);
			couleursDisponible.remove(rep);
			
			
			joueurs.add(new JoueurHumain(nomChoisi ,couleurChoisi));
		}
		
		sc.close();
		
	}
	
	public void initialiserPlateau() {
		
		
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
		
	}

}
