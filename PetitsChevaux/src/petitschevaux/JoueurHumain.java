package petitschevaux;

import java.util.ArrayList;

public class JoueurHumain extends Joueur {

	public JoueurHumain(String nom, Couleur c) {
		super(nom,c);
	}
	
	/**
	 * Fonction de choix du pion � bouger
	 * @param valeurDe la valeur du de
	 * @param p le plateau de la partie
	 * @return retourne le pion que le joueur joue
	 */
	public Pion choisirPion(int valeurDe, Plateau p) {
		
		ArrayList<Pion> pionBougable = new ArrayList<Pion>();
		
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
		
		return null;////////////////////
	}
	
}
