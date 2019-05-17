package petitschevaux;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class JoueurHumain extends Joueur {

	public JoueurHumain(String nom, Couleur c) {
		super(nom,c);
	}
	
	/**
	 * Fonction de choix du pion � bouger
	 * (trouve les pions qui peuvent bouger et demande a l'utilisateur d'en choisir 1)
	 * @param valeurDe la valeur du de
	 * @param p le plateau de la partie
	 * @return retourne le pion que le joueur joue
	 */
	public Pion choisirPion(int valeurDe, Plateau p) {
		
		ArrayList<Pion> pionBougable = new ArrayList<Pion>();
		
		/*Recherche de l'indice de l echelle dans l'ArrayList echelles*/
		int indiceDeLEchelle = 0;
		for(int j = 0; j<p.getEchelles().size(); j++) {
			if(p.getEchelles().get(j).get(0).getCouleur() == this.getCouleur())
				indiceDeLEchelle = j;
		}
		
		/*Pion dans l'ecurie*/
		for(CaseEcurie CE : p.getEcuries()) {
			
			if(CE.getCouleur() == this.getCouleur()) {
				
				for(Pion pions : CE.getChevaux()){
					if(valeurDe == 6) {
						pionBougable.add(pions);
					}
				}
			}
		}
		
		/*Pion sur le chemin*/
		for(CaseDeChemin CDC : p.getChemin()) {
			for(Pion pions : CDC.getChevaux()) {
				if(pions.getCouleur() == this.getCouleur()) {
					
					boolean bougable = true;
					int numCaseTest;
					
					/*Si le pion se trouve au dernier indice de l'arraylist*/
					if(p.getChemin().indexOf(CDC) == 55) {
						/*Si c'est un pion de l'ecurie 0*/
						if((valeurDe != 1 && this.getCouleur() == p.getEcuries().get(0).getCouleur()) || (!p.getEchelles().get(0).get(0).peutSArreter(pions)&& this.getCouleur() == p.getEcuries().get(0).getCouleur())) {
							bougable = false;
						}
					}
					
					for(int i = 1; i <= valeurDe; i++) {
						/*Definition de la case a tester*/
						numCaseTest = p.getChemin().indexOf(CDC)+i;
						if(numCaseTest > 55) {
							numCaseTest -= 56;
						}
						
						/*On verifie si le pion est sur la derniere case de son chemin et peut passer*/
						if(p.getChemin().get(numCaseTest) == this.getCaseDeDepart()) {
							
							if(valeurDe != 1 || !p.getEchelles().get(indiceDeLEchelle).get(0).peutSArreter(pions)) {
								bougable = false;
								break;
							}
						}
						/*On verifie que le pion peut passer sur les case entre sa case et la case d'arriver*/
						else if(!p.getChemin().get(numCaseTest).peutPasser(pions) && i != valeurDe) {
							bougable = false;
							break;
						}
						/*On verifie qu'il peut s'arreter sur la case d'arriver*/
						else if(i == valeurDe-1 && !p.getChemin().get(numCaseTest).peutSArreter(pions)) {
							bougable = false;
							break;
						}
					}
					
					if(bougable) { 
						pionBougable.add(pions);
					}
					
				}
			}
		}
		
		/*Pion sur l'echelle*/
		for(CaseDEchelle CE : p.getEchelles().get(indiceDeLEchelle)) {
			
			for(Pion pions : CE.getChevaux()){
				/*Si le pion est sur la case 1( indice 0) il doit faire un 2 donc le de doit etre egale a l'indice de la case actuel + 2*/
				if(valeurDe == p.getEchelles().get(indiceDeLEchelle).indexOf(CE)+2) {
					
					/*On verifie que l'indice ne depasse pas la taille de l'arraylist*/
					if(p.getEchelles().get(indiceDeLEchelle).indexOf(CE)+1 <= 5) {
						if(p.getEchelles().get(indiceDeLEchelle).get(p.getEchelles().get(indiceDeLEchelle).indexOf(CE)+1).peutSArreter(pions))
							pionBougable.add(pions);
					}	
					
				}
			}
			
		}
		
		/*Affichage des pions possible de jouer et selection*/
		System.out.println();
		
		
		/* Definition du pion "Initial" */
		Pion reponse = null;
		int rep = -1;
		
		
		/* (Selection si au moins 2 pions possible a bouger) Verification que l'entier rentrer est bien un numero de couleur propose */
		if(pionBougable.size() >=2 ) {
			while((rep < 0 || rep >= pionBougable.size())) {
				
				System.out.println("Quel pion voulez vous jouer : ");
				/*Affichage des pions*/
				
				for(int i = 0; i<pionBougable.size(); i++) {
					String message = i+" : pion "+pionBougable.get(i).getId()+" sur la case ";
					for(CaseEcurie CE : p.getEcuries()) {
						if(CE.getChevaux().indexOf(pionBougable.get(i)) != -1) {
							message += "ecurie de sa couleur";
						}
					}
					for(CaseDeChemin CDC : p.getChemin()) {
						if(CDC.getChevaux().indexOf(pionBougable.get(i)) != -1) {
							message += "de chemin "+p.getChemin().indexOf(CDC);
						}
					}
					for(CaseDEchelle CDE : p.getEchelles().get(indiceDeLEchelle)) {
						if(CDE.getChevaux().indexOf(pionBougable.get(i)) != -1) {
							message += "d'echelle "+p.getEchelles().get(indiceDeLEchelle).indexOf(CDE);
						}
					}
					System.out.println(message);
				}
				
				
				try {
					rep = Partie.sc.nextInt();
					
				}catch (InputMismatchException exception) { 
				    System.out.println("Mauvaise entree");
				    Partie.sc.next();
				    rep = -1;
				}
					
			}
			/*On vide le scanner*/
			if(Partie.sc.hasNextLine())
				Partie.sc.nextLine();
		}
		
		if(pionBougable.size() == 1) {
			reponse = pionBougable.get(0);
			System.out.println("Vous ne pouvez bouger que le pion "+pionBougable.get(0).getId());
			Partie.sc.nextLine();
		}
		
		if(pionBougable.size()>=2)
			reponse = pionBougable.get(rep);
		
		
		return reponse;
	}
	
}
