package petitschevaux;

import java.util.InputMismatchException;
import java.util.Scanner;

public class PetitsChevaux {

	public PetitsChevaux() {
		
	}
	
	public static void main(String[] args) {
		
		
		Partie p = new Partie();
		p.initialiserPlateau();
		
		/*
		 * Demande à l'utilisateur du nombre de joueurs
		 * Puis Initialisation des joueurs
		 */
		int nombreDeJoueurs;
		Scanner sc = new Scanner(System.in);
		do { 
			System.out.println("Combiens y-a-t il de joueurs ? ");	
			try {
				nombreDeJoueurs = sc.nextInt();
			}catch (InputMismatchException exception) { 
			    System.out.println("Impossible d'avoir ce nombre de joueurs");
			    sc.next();
			    nombreDeJoueurs = -1;
			}
		}while(nombreDeJoueurs < 0 || nombreDeJoueurs > 4);// Verification que le nombre de joueurs et compris entre 1 et 4
		
		try {
			p.initialiserJoueurs(nombreDeJoueurs);
		}catch(PasDeJoueursException e) {
			System.out.println(e);
		}
		
	}
	
	
}
