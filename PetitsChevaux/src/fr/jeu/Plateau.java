package fr.jeu;

import java.util.ArrayList;

import fr.cases.Case;
import fr.cases.CaseDEchelle;
import fr.cases.CaseDeChemin;
import fr.cases.CaseEcurie;
import fr.joueur.Joueur;
import fr.joueur.Pion;

/**
 * Classe de plateau des petits chevaux
 * @author Quentin Fontaine
 */

public class Plateau {
	
	/**
	 * Liste des échelles 
	 */
	private ArrayList<ArrayList<CaseDEchelle>> echelles;
	/**
	 * Tableau des écuries
	 */
	private ArrayList<CaseEcurie> ecuries;
	/**
	 * Tableau des cases de chemin du plateau
	 */
	private ArrayList<CaseDeChemin> chemin;
	/*Pouvoir voir la couleur du joueur pour l'affichage du plateau*/
	/**
	 * Liste des joueurs de la partie
	 */
	private ArrayList<Joueur> joueurs;
	
	
	/**
	 * Constructeur d'un plateau, initialisation des tableaux
	 */
	public Plateau() {
		echelles = new ArrayList<ArrayList<CaseDEchelle>>();
		ecuries = new ArrayList<CaseEcurie>();
		chemin = new ArrayList<CaseDeChemin>();
	}
	
	/**
	 * Fonction getter des écuries
	 * @return retourne la liste des écuries
	 */
	public ArrayList<CaseEcurie> getEcuries(){
		return ecuries; 
	}
	
	/**
	 * Fonction getter des listes des échelles
	 * @return retourne les listes d'échelles
	 */
	public ArrayList<ArrayList<CaseDEchelle>> getEchelles(){
		return echelles;
	}
	
	/**
	 * Fonction getter de la liste des cases de chemin
	 * @return retourne la liste des cases de chemin
	 */
	public ArrayList<CaseDeChemin> getChemin(){
		return chemin;
	}
	
	
	/**
	 * Fonction d'ajout de case d'écurie
	 * @param ce la case d'écurie à ajouter
	 */
	public void addCaseEcuries(CaseEcurie ce) {
		ecuries.add(ce);
	}
	/**
	 * Fonction d'ajout de case d'échelle
	 * @param ce la case d'échelle à ajouter
	 */
	public void addCaseEchelles(ArrayList<CaseDEchelle> ce) {
		echelles.add(ce);
	}
	/**
	 * Fonction d'ajout de case de chemin
	 * @param cc la case de chemin à ajouter
	 */
	public void addCaseChemin(CaseDeChemin cc) {
		chemin.add(cc);
	}
	
	/**
	 * Fonction qui affiche le plateau de jeu (Pour un terminal shell)
	 */
	public void afficher() {
	
		/*Remplissage de la matrice à afficher*/
		String[][] affPlateau = new String[15][15];
		
		for(int ligne = 0; ligne < 15; ligne ++) {
			
			for(int colone = 0; colone < 15; colone ++) {
				
				/*Ajout des écuries dans la matrice*/
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
						/*S'il n'y a pas de joueur 3, affichage d'une écurie blanche*/
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
			
				/*Lignes horizontales et verticales*/
				else {
					/*Case pour simplifier l'attribution des cases sur la matrice*/
					Case laCase = null;
					
					/*Recherche de la case à afficher pour une ligne et une colonne du tableau*/
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
						
							
			
					/*Si la ligne et la colone ont une case*/
					if(laCase != null) {
						/*S'il y a un cheval sur cette case*/
						if(laCase.getChevaux().size()==1) {
							affPlateau[ligne][colone]=laCase.getChevaux().get(0).getCouleur().getCCode()+"\u265e \033[0m";/*Case occupe par 1 seul cheval*/
						}
						/*S'il y a plus d'un cheval*/
						else if(laCase.getChevaux().size()>1) {
							affPlateau[ligne][colone]=laCase.getChevaux().get(0).getCouleur().getCCode()+laCase.getChevaux().size()+"\u265e\033[0m";/*Case occupe par plusieurs chevaux*/
						}
						/*Si cette case est une case d'échelle, on affiche son numéro ou le cheval s'il y en a un*/
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
		
		/*On affiche toutes les lignes et les colonnes du tableau*/
		for(int i = 0; i < 15; i++){
			System.out.println();
			
			for(int j = 0; j < 15; j++ ) {
				
				System.out.print(affPlateau[i][j]);
				
			}
			
		}
		
		
		
		
		
	}
	
	/**
	 * Fonction pour déplacer un pion
	 * @param p le pion à déplacer
	 * @param c la case sur laquelle on veut que le pion aille
	 */
	public void deplacerPionA(Pion p, Case c) {
		
		/*
		 * On retire le cheval de la case où il est et on l'ajoute à la case passée en paramètre
		 * On vérifie chaque cheval de chaque case pour savoir si le cheval est celui passé en paramètre
		 */
		
		for(Case cases : chemin) {
			if(cases.getChevaux().indexOf(p) != -1) {
				cases.retirerCheval(p);
				c.ajouteCheval(p);
				return;
			}
			
		}
		for(Case cases : ecuries) {
			if(cases.getChevaux().indexOf(p) != -1) {
				cases.retirerCheval(p);
				c.ajouteCheval(p);
				return;
			}
		}
		/* On vérifie chaque cheval de chaque case de chaque échelle pour savoir si le cheval est celui passé en paramètre */
		for(ArrayList<CaseDEchelle> ech : echelles) {
			for(Case cases : ech) {
				if(cases.getChevaux().indexOf(p) != -1) {
					cases.retirerCheval(p);
					c.ajouteCheval(p);
					return;
				}
			}
		}
		
		
	}
	
	/**
	 * Setter de la liste des joueurs de la partie
	 * @param j la liste des joueurs de la partie
	 */
	public void setJoueurs(ArrayList<Joueur> j){
		this.joueurs = j;
	}

}
