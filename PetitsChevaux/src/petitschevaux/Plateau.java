package petitschevaux;

import java.util.ArrayList;

public class Plateau {
	
	/**
	 * Variables stockant les cases du plateau
	 */
	
	private ArrayList<ArrayList<CaseDEchelle>> echelles;
	private ArrayList<CaseEcurie> ecuries;
	private ArrayList<CaseDeChemin> chemin;
	
	/**
	 * Initialisation des variables
	 */
	
	public Plateau() {
		echelles = new ArrayList<ArrayList<CaseDEchelle>>();
		ecuries = new ArrayList<CaseEcurie>();
		chemin = new ArrayList<CaseDeChemin>();
	}
	
	/**
	 * Fonctions getters
	 */
	
	public ArrayList<CaseEcurie> getEcuries(){
		return ecuries; 
	}
	
	public ArrayList<ArrayList<CaseDEchelle>> getEchelles(){
		return echelles;
	}
	
	public ArrayList<CaseDeChemin> getChemin(){
		return chemin;
	}
	
	
	/**
	 * Fonctions pour etablir un plateau de jeu
	 */
	
	public void addCaseEcuries(CaseEcurie ce) {
		ecuries.add(ce);
	}
	public void addCaseEchelles(ArrayList<CaseDEchelle> ce) {
		echelles.add(ce);
	}
	public void addCaseChemin(CaseDeChemin cc) {
		chemin.add(cc);
	}
	
	/**
	 * Fonction qui affiche le plateau
	 */
	
	public void afficher() {
		
		
	}
	
	/**
	 * Fonction deplacent un pion sur une certaine case
	 */
	
	public void deplacerPionA(Pion p, Case c) {
		
		
		
	}

}
