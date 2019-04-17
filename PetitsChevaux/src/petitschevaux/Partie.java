package petitschevaux;

import java.util.ArrayList;
import java.util.Random;

public class Partie {
	
	private Random de = new Random();
	private Joueur joueurCourrant;
	private Plateau plateau;
	private ArrayList<Joueur> joueurs;
	
	public Partie() {
		
	}
	
	public void initialiserJoueurs(int nb) {
		
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
