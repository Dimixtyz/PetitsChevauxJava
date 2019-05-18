package fr.joueur;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import fr.cases.CaseDEchelle;
import fr.cases.CaseDeChemin;
import fr.cases.CaseEcurie;
import fr.jeu.Couleur;
import fr.jeu.Partie;
import fr.jeu.Plateau;

/**
 * Classe d'un joueur humain
 * @author Quentin Fontaine
 */

public class JoueurHumain extends Joueur {

	/**
	 * Constructeur d'un joueur humain
	 * @param nom le nom du joueur
	 * @param c la couleur du joueur
	 */
	public JoueurHumain(String nom, Couleur c) {
		super(nom,c);
	}
	
	/**
	 * Fonction de choix du pion à bouger, (trouve les pions qui peuvent bouger et demande à l'utilisateur d'en choisir 1)
	 * @param valeurDe la valeur du dé
	 * @param p le plateau de la partie
	 * @return retourne le pion que le joueur joue
	 */
	public Pion choisirPion(int valeurDe, Plateau p) {
		
		ArrayList<Pion> pionBougeable = new ArrayList<Pion>();
		
		/*Recherche de l'indice de l'échelle du joueur*/
		int indiceDeLEchelle = 0;
		for(int j = 0; j<p.getEchelles().size(); j++) {
			if(p.getEchelles().get(j).get(0).getCouleur() == this.getCouleur())
				indiceDeLEchelle = j;
		}
		
		/*On cherche les pions qui peuvent bouger*/
		
		/*les pions dans l'écurie peuvent bouger si la valeur du dé est de 6*/
		for(CaseEcurie CE : p.getEcuries()) {
			
			if(CE.getCouleur() == this.getCouleur()) {
				
				for(Pion pions : CE.getChevaux()){
					if(valeurDe == 6) {
						pionBougeable.add(pions);
					}
				}
			}
		}
		
		/*Pion sur le chemin*/
		for(CaseDeChemin CDC : p.getChemin()) {
			for(Pion pions : CDC.getChevaux()) {
				if(pions.getCouleur() == this.getCouleur()) {
					
					boolean bougeable = true;
					int numCaseTest;
					
					/*Si le pion se trouve au dernier indice de l'arraylist*/
					if(p.getChemin().indexOf(CDC) == 55) {
						
						/*Si c'est un pion de l'écurie 0 et que la valeur du dé n'est pas 1 ou il y a déjà un pion sur sa première case d'échelle, il ne peut pas bouger*/
						if((valeurDe != 1 && this.getCouleur() == p.getEcuries().get(0).getCouleur()) || (!p.getEchelles().get(0).get(0).peutSArreter(pions)&& this.getCouleur() == p.getEcuries().get(0).getCouleur())) {
							bougeable = false;
						}
					}
					
					/*On regarde si le pion peut passer sur les cases entre sa position et sa case d'arrivée et s'il peut s'arrêter sur sa case d'arrivée*/
					for(int i = 1; i <= valeurDe; i++) {
						/*Définition de la case à tester*/
						numCaseTest = p.getChemin().indexOf(CDC)+i;
						if(numCaseTest > 55) {
							numCaseTest -= 56;
						}
						
						/*si le pion est sur la dernière case de son chemin*/
						if(p.getChemin().get(numCaseTest) == this.getCaseDeDepart()) {
							
							if(valeurDe != 1 || !p.getEchelles().get(indiceDeLEchelle).get(0).peutSArreter(pions)) {
								bougeable = false;
								break;
							}
						}
						
						/*On vérifie que le pion peut passer sur les cases entre sa case et la case d'arrivée*/
						else if(!p.getChemin().get(numCaseTest).peutPasser(pions) && i != valeurDe) {
							bougeable = false;
							break;
						}
						
						/*On vérifie qu'il peut s'arrêter sur la case d'arrivée*/
						else if(i == valeurDe-1 && !p.getChemin().get(numCaseTest).peutSArreter(pions)) {
							bougeable = false;
							break;
						}
					}
					
					if(bougeable) { 
						pionBougeable.add(pions);
					}
					
				}
			}
		}
		
		/*Pion sur l'échelle*/
		for(CaseDEchelle CE : p.getEchelles().get(indiceDeLEchelle)) {
			
			for(Pion pions : CE.getChevaux()){
				/*Si le pion est sur la case 1( indice 0) il doit faire un 2 donc le dé doit être égal à l'indice de la case actuelle + 2*/
				if(valeurDe == p.getEchelles().get(indiceDeLEchelle).indexOf(CE)+2) {
					
					/*On vérifie que l'indice ne dépasse pas la taille de l'arraylist*/
					if(p.getEchelles().get(indiceDeLEchelle).indexOf(CE)+1 <= 5) {
						if(p.getEchelles().get(indiceDeLEchelle).get(p.getEchelles().get(indiceDeLEchelle).indexOf(CE)+1).peutSArreter(pions))
							pionBougeable.add(pions);
					}	
					
				}
			}
			
		}
		
		/*Affichage des pions possibles de jouer et sélection*/
		System.out.println();
		
		
		/* Définition du pion par défaut */
		Pion reponse = null;
		int rep = -1;
		
		
		/* (Sélection si au moins 2 pions possibles à bouger) Vérification que l'entier rentré est bien un numéro proposé */
		if(pionBougeable.size() >=2 ) {
			while((rep < 0 || rep >= pionBougeable.size())) {
				
				System.out.println("Quel pion voulez vous jouer : ");
				/*Affichage des pions*/
				
				for(int i = 0; i<pionBougeable.size(); i++) {
					String message = i+" : le pion sur la case ";
					for(CaseEcurie CE : p.getEcuries()) {
						if(CE.getChevaux().indexOf(pionBougeable.get(i)) != -1) {
							message += "écurie";
						}
					}
					for(CaseDeChemin CDC : p.getChemin()) {
						if(CDC.getChevaux().indexOf(pionBougeable.get(i)) != -1) {
							message += "de chemin "+p.getChemin().indexOf(CDC);
						}
					}
					for(CaseDEchelle CDE : p.getEchelles().get(indiceDeLEchelle)) {
						if(CDE.getChevaux().indexOf(pionBougeable.get(i)) != -1) {
							message += "d'échelle "+p.getEchelles().get(indiceDeLEchelle).indexOf(CDE);
						}
					}
					System.out.println(message);
				}
				
				/*Vérification que la réponse est bien un entier*/
				try {
					rep = Partie.sc.nextInt();
					
				}catch (InputMismatchException exception) { 
				    System.out.println("Mauvaise entrée");
				    Partie.sc.next();
				    rep = -1;
				}
					
			}
			/*On vide le scanner*/
			if(Partie.sc.hasNextLine())
				Partie.sc.nextLine();
		}
		
		/*S'il n'y a qu'un pion bougeable, on le bouge directement*/
		if(pionBougeable.size() == 1) {
			reponse = pionBougeable.get(0);
			System.out.println("Vous ne pouvez bouger que le pion "+pionBougeable.get(0).getId());
			Partie.sc.nextLine();
		}
		
		/*sinon on retourne le pion choisi*/
		if(pionBougeable.size()>=2)
			reponse = pionBougeable.get(rep);
		
		
		return reponse;
	}
	
}
