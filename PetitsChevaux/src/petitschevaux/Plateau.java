package petitschevaux;

import java.util.ArrayList;

public class Plateau {
	
	/**
	 * Variables stockant les cases du plateau
	 */
	
	private ArrayList<ArrayList<CaseDEchelle>> echelles;
	private ArrayList<CaseEcurie> ecuries;
	private ArrayList<CaseDeChemin> chemin;
	private ArrayList<Joueur> joueurs; /*Pouvoir voir la couleur du joueur pour l'affichage du plateau*/
	
	
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
	
		/*Remplissage de la matrice a afficher*/
		String[][] affPlateau = new String[15][15];
		
		for(int ligne = 0; ligne < 15; ligne ++) {
			
			for(int colone = 0; colone < 15; colone ++) {
				if((ligne < 6 || ligne > 8)&&(colone < 6 || colone > 8)) {
					
					/*Ecurie 3*/
					if(ligne < 6 && colone < 6) {
						if(joueurs.size()>=3) {
							if(ligne == 3 && colone == 3) {
								affPlateau[ligne][colone]=joueurs.get(2).getCouleur().getCCode()+ecuries.get(2).getChevaux().size()+"\u265e\033[0m";
							}
							else {	
								affPlateau[ligne][colone]=joueurs.get(2).getCouleur().getCCode()+"  "+Couleur.BLANC.getCCode();
							}
						}
						else {
							affPlateau[ligne][colone]=Couleur.BLANC.getCCode()+"  ";
						}
					}
					/*Ecurie 2*/
					else if(ligne > 8 && colone < 6) {
						if(joueurs.size()>=2) {
							if(ligne == 11 && colone == 3) {
								affPlateau[ligne][colone]=joueurs.get(1).getCouleur().getCCode()+ecuries.get(1).getChevaux().size()+"\u265e\033[0m";
							}
							else {
								affPlateau[ligne][colone] = joueurs.get(1).getCouleur().getCCode()+"  "+Couleur.BLANC.getCCode();
							}
						}
						else {
							affPlateau[ligne][colone]=Couleur.BLANC.getCCode()+"  ";
						}
					}
								
						
					/*Ecurie 4*/
					if(ligne < 6 && colone > 8) {
						if(joueurs.size()>=4) {
							if(ligne == 3 && colone == 11) {
								affPlateau[ligne][colone]=joueurs.get(3).getCouleur().getCCode()+ecuries.get(3).getChevaux().size()+"\u265e\033[0m";
							}
							else {
								affPlateau[ligne][colone]=joueurs.get(3).getCouleur().getCCode()+"  "+Couleur.BLANC.getCCode();
							}
						}
						else {
							affPlateau[ligne][colone]=Couleur.BLANC.getCCode()+"  ";
						}
					}
					/*Ecurie 1*/
					if(ligne > 8 && colone > 8) {
						if(joueurs.size()>=1) {
							if(ligne == 11 && colone == 11) {
								affPlateau[ligne][colone]=joueurs.get(0).getCouleur().getCCode()+ecuries.get(0).getChevaux().size()+"\u265e\033[0m";
							}
							else {
								affPlateau[ligne][colone]=joueurs.get(0).getCouleur().getCCode()+"  "+Couleur.BLANC.getCCode();
							}
						}
						else {
							affPlateau[ligne][colone]=Couleur.BLANC.getCCode()+"  ";
						}
					}
					
				}
			
				/*Ligne horizontale et verticales*/
				else {
					Case laCase = null;/*Case pour s'implifier l'attribution des cases sur la matrice*/
					Couleur laCouleur;/*couleur des cases*/
					
					/*Attribution des cases a la matrice*/
					switch(colone) {
						case 6:
							if(ligne >= 0 && ligne <= 6)
								laCase = chemin.get((40-ligne));
							if(ligne >= 8 && ligne <= 14)
								laCase = chemin.get((28-ligne));
							break;
							
						case 7:
							if(ligne == 0)
								laCase = chemin.get(41);
							if(ligne >= 1 && ligne <= 6)
								laCase = echelles.get(3).get(ligne-1);
							if(ligne >= 8 && ligne <= 13)
								laCase = echelles.get(1).get(13-ligne);
							if(ligne == 7)
								laCase = null;
							if(ligne == 14)
								laCase = chemin.get(13);
							break;
							
						case 8:
							if(ligne >= 0 && ligne <= 6)
								laCase = chemin.get((42+ligne));
							if(ligne >= 8 && ligne <= 14)
								laCase = chemin.get((ligne-2));
							break;
					}
					switch(ligne) {
					
					case 6:
						if(colone >= 0 && colone <= 6)
							laCase = chemin.get(28+colone);
						if(colone >= 8 && colone <= 14)
							laCase = chemin.get(40+colone);
						break;
						
					case 7:
						if(colone == 0)
							laCase = chemin.get(27);
						if(colone >= 1 && colone <= 6)
							laCase = echelles.get(2).get(colone-1);
						if(colone >= 8 && colone <= 13)
							laCase = echelles.get(0).get(13-colone);
						if(colone == 7)
							laCase = null;
						if(colone == 14)
							laCase = chemin.get(55);
						break;
						
					case 8:
						if(colone >= 0 && colone <= 6)
							laCase = chemin.get(26-colone);
						if(colone >= 8 && colone <= 14)
							laCase = chemin.get(14-colone);
						break;
						
					}
						
							
			
					if(laCase != null) {
						if(laCase.getChevaux().size()==1) {
							affPlateau[ligne][colone]=laCase.getChevaux().get(0).getCouleur().getCCode()+"\u265e\033[0m";/*Case occupe par 1 seul cheval*/
						}
						else if(laCase.getChevaux().size()>1) {
							affPlateau[ligne][colone]=laCase.getChevaux().get(0).getCouleur().getCCode()+laCase.getChevaux().size()+"\u265e\033[0m";/*Case occupe par plusieurs chevaux*/
						}
						else if(laCase instanceof CaseDEchelle) {
							affPlateau[ligne][colone]=((CaseDEchelle) laCase).getCouleur().getCCode();
							
							int numEchelle = 0;
							for(int numC = 0; numC < echelles.size(); numC++) {
								if(echelles.get(numC).get(0).getCouleur() == ((CaseDEchelle) laCase).getCouleur()){
									numEchelle = numC;
								}
							}
							if(echelles.get(numEchelle).get(0).getCouleur() != Couleur.BLANC){
								affPlateau[ligne][colone] += (echelles.get(numEchelle).indexOf(laCase)+1) + " "+Couleur.BLANC.getCCode();
							}
							else {
								affPlateau[ligne][colone] += "  "+Couleur.BLANC.getCCode();
							}
							
						}
						else {
							affPlateau[ligne][colone]=Couleur.BLANC.getCCodeSec()+"  "+Couleur.BLANC.getCCode();/*Case vide*/
						}
					}
					else if(laCase == null) {
						affPlateau[ligne][colone]=Couleur.BLANC.getCCode()+"  ";
					}
					
				}
			}
		}
		
		
		/*Affichage*/
	
		for(int i = 0; i < 15; i++){
			System.out.println();
			
			for(int j = 0; j < 15; j++ ) {
				
				System.out.print(affPlateau[i][j]);
				
			}
			
		}
		
		
		
		
		
	}
	
	/**
	 * Fonction deplacent un pion sur une certaine case
	 */
	
	public void deplacerPionA(Pion p, Case c) {
		
		/*
		 * On retire le cheval de la case ou il est
		 * 
		 * On verifie chaque chevaux de chaque cases pour savoir si le cheval est celui passer en parametre
		 */
		
		for(Case cases : chemin) {
			if(cases.getChevaux().indexOf(p) != -1)
				cases.retirerCheval(p);
		}
		for(Case cases : ecuries) {
			if(cases.getChevaux().indexOf(p) != -1)
				cases.retirerCheval(p);
		}
		/*
		 * On verifie chaque chevaux de chaque cases de chaque echelle pour savoir si le cheval est celui passer en parametre
		 */
		for(ArrayList<CaseDEchelle> ech : echelles) {
			for(Case cases : ech) {
				if(cases.getChevaux().indexOf(p) != -1)
					cases.retirerCheval(p);
			}
		}
		
		/*
		 * On place le cheval sur sa nouvelle case
		 */
		
		c.ajouteCheval(p);
		
		
	}
	
	public void setJoueurs(ArrayList<Joueur> j){
		this.joueurs = j;
	}

}
