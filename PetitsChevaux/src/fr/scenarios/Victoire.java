package fr.scenarios;

import java.util.InputMismatchException;
import java.util.Scanner;

import fr.exceptionpetitschevaux.CasePleineException;
import fr.exceptionpetitschevaux.ConflitDeCouleurException;
import fr.exceptionpetitschevaux.PasDeJoueursException;
import fr.jeu.Partie;
import fr.joueur.Joueur;
import fr.joueur.Pion;


/**
 * Scenario de Monter des echelles et vitoire
 * @author quentin
 *
 */
public class Victoire {
	
	
	public static void main(String[] args) {
		
		
		Partie p = new Partie();
	
		/*
		 * Demande à l'utilisateur le nombre de joueurs
		 * Puis Initialisation des joueurs
		 */
		
		int nombreDeJoueurs;
		Scanner sc = new Scanner(System.in);
		do { 
			System.out.println("Combien y a-t-il de joueurs ? ");	
			try {
				nombreDeJoueurs = sc.nextInt();
			}catch (InputMismatchException exception) { 
			    System.out.println("Impossible d'avoir ce nombre de joueurs");
			    sc.next();
			    nombreDeJoueurs = -1;
			}
		}while(nombreDeJoueurs <= 0 || nombreDeJoueurs > 4);// Vérification que le nombre de joueurs est compris entre 1 et 4
		
		/*Vérification que le nombre de joueur n'est pas égal à 0*/
		try {
			p.initialiserJoueurs(nombreDeJoueurs);
		}catch(PasDeJoueursException e) {
			System.out.println(e);
		}
		
		/* Initialisation du plateau */
		p.initialiserPlateau();
		
		/*on place les pions sur leur derniere case*/
		for(int j = 0 ; j < p.getJoueurs().size(); j++) {
			for(Pion pions : p.getJoueurs().get(j).getChevaux()) {
				if(j == 0)
					p.getPlateau().deplacerPionA(pions, p.getPlateau().getChemin().get(55));
				else
					p.getPlateau().deplacerPionA(pions, p.getPlateau().getChemin().get(p.getPlateau().getChemin().indexOf(p.getJoueurs().get(j).getCaseDeDepart())-1));
			}
		}
		/*on affiche le plateau*/
		p.getPlateau().afficher();
		
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
