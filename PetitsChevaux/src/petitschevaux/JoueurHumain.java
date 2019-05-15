package petitschevaux;

import java.util.ArrayList;

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
							System.out.println("########### PION ECHELLE 0 PEUT PAS MONTER ECHELLE ############");
						}
					}
					
					for(int i = 1; i <= valeurDe; i++) {
						/*Definition de la case a tester*/
						numCaseTest = p.getChemin().indexOf(CDC)+i;
						if(p.getChemin().indexOf(CDC)+i > 54) {
							numCaseTest = p.getChemin().indexOf(CDC)+i-54;
						}
						
						/*On verifie si le pion est sur la derniere case de son chemin et peut passer*/
						if(p.getChemin().get(numCaseTest) == this.getCaseDeDepart()) {
							
							if(valeurDe != 1 || !p.getEchelles().get(indiceDeLEchelle).get(0).peutSArreter(pions)) {
								bougable = false;
								System.out.println("########### DERNIERE CASE MAIS PAS 1 ############");
								break;
							}
						}
						/*On verifie que le pion peut passer sur les case entre sa case et la case d'arriver*/
						else if(!p.getChemin().get(numCaseTest).peutPasser(pions) && i != valeurDe) {
							bougable = false;
							System.out.println("########### BLOQUE PAR (!PASSER) ############");
							break;
						}
						/*On verifie qu'il peut s'arreter sur la case d'arriver*/
						else if(i == valeurDe-1 && !p.getChemin().get(numCaseTest).peutSArreter(pions)) {
							bougable = false;
							System.out.println("########### PAS POSSIBLE DE S ARRETER DERNIERE CASE ############");
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
		
		System.out.println();
		
		for(Pion pion :pionBougable) {
			
			String message = "Le pion "+pion.getId()+" sur la case ";
			
			for(CaseEcurie CE : p.getEcuries()) {
				if(CE.getChevaux().indexOf(pion) != -1) {
					message += "ecurie de sa couleur";
				}
			}
			
			for(CaseDeChemin CDC : p.getChemin()) {
				if(CDC.getChevaux().indexOf(pion) != -1) {
					message += "de chemin "+p.getChemin().indexOf(CDC);
				}
			}
			
			for(CaseDEchelle CDE : p.getEchelles().get(indiceDeLEchelle)) {
				if(CDE.getChevaux().indexOf(pion) != -1) {
					message += "d'echelle "+p.getEchelles().get(indiceDeLEchelle).indexOf(CDE);
				}
			}
			
			System.out.println(message);
		}
		
		return null;////////////////////
	}
	
}
