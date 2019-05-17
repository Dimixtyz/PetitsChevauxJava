package fr.petitschevaux;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.exceptionpetitschevaux.CasePleineException;
import fr.exceptionpetitschevaux.ConflitDeCouleurException;
import fr.exceptionpetitschevaux.PasDeJoueursException;
import fr.jeu.Partie;

public class PetitsChevaux {

	public PetitsChevaux() {
		
	}
	
	public static void main(String[] args) {
		
		
		Partie p = new Partie();
	
		/*
		 * Demande a l'utilisateur du nombre de joueurs
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
		}while(nombreDeJoueurs <= 0 || nombreDeJoueurs > 4);// Verification que le nombre de joueurs et compris entre 1 et 4
		
		try {
			p.initialiserJoueurs(nombreDeJoueurs);
		}catch(PasDeJoueursException e) {
			System.out.println(e);
		}
		
		p.initialiserPlateau();
		
		
		/*Boucle du jeu*/
		while(!p.estPartieTermine()) {
			try {
				p.jouerUnTour();
			} catch (ConflitDeCouleurException e) {
				System.out.println();
				p.getPlateau().afficher();
				System.out.println();
				System.out.println(e.getMessage());
				break;
			} catch (CasePleineException e) {
				System.out.println();
				p.getPlateau().afficher();
				System.out.println();
				System.out.println(e.getMessage());
			}
		}
		
		
	}
	
	
}
