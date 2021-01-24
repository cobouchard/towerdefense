package Jeu;

import java.util.ArrayList;
import java.util.HashSet;

import AStar.Algorithm;
import AStar.PositionTab;
import Interface.Converter;
import Interface.Position;
import Read.Reader;

public class Niveau {
	private String nom;
	private int[][] grille;
	private ArrayList<Vague> vagues;
	private final int or_depart;
	
	private Position pChateau;
	
	// Position par laquelle les monstres vont venir
	private ArrayList<PositionTab> spawns;
	
	public String getNom() {
		return nom;
	}
	public int[][] getGrille() {
		return grille;
	}
	
	public int getOrDepart() 
	{
		return this.or_depart;
	}
	
	public Position getPChateau() 
	{
		return pChateau;
	}
	
	/**
	 * 
	 * @return renvoie la prochaine vague de monstre et la supprime de la liste, renvoie null si le niveau est terminé
	 */
	public Vague getNextVague() 
	{
		if(vagues.isEmpty())
			return null;
		Vague v = vagues.get(0);
		vagues.remove(0);
		return v;
	}
	
	/*
	 * @return renvoie un spawn au hasard parmi la liste des spawns du niveau
	 */
	public PositionTab getRandomSpawn() 
	{
		int rand = Randomizer.randomInt(0, spawns.size()-1);
		return spawns.get(rand);
	}
	
	public Niveau(String nom, int[][] grille, ArrayList<Vague> vagues, int or_depart, ArrayList<PositionTab> spawns) {
		this.nom = nom;
		this.grille = grille;
		this.vagues = vagues;
		this.or_depart=or_depart;
		this.spawns=spawns;
		
		for(int i=0; i!=grille.length; i++)
			for(int j=0; j!=grille.length; j++)
				if(grille[i][j]==250)
					pChateau = Converter.tabToPosition(new PositionTab(i,j));
		
	}
	
	public void affiche() 
	{
		System.out.println("Niveau : " + nom);
		System.out.println("Or de départ : " + or_depart);
		for(int ligne=0;ligne!=Informations.taille; ligne++) 
		{
			for(int colonne=0; colonne!=Informations.taille; colonne++)
				System.out.print(grille[ligne][colonne] + ",");
			System.out.println();
		}
		for(Vague v : vagues)
			System.out.println(v);
			
	}
	public void poserTourArcher(PositionTab position_tab) {
		//On attribue la valeur -5 à une tour dans la grille
		int x = position_tab.getX();
		int y = position_tab.getY();
		if (peutConstruire(new PositionTab(x,y)))
		{
			grille[x][y] = -5;
		}
	}
	
	/**
	 * 
	 * @param pt position ou le joueur essaye de poser une tour
	 * @return renvoie vrai si on peut poser une tour sur la case pt
	 */
	public boolean peutConstruire(PositionTab pt) 
	{
		int x = pt.getX();
		int y = pt.getY();
		if ((grille[x][y] < 0)&&(grille[x][y] != -5))
		{
			if ((x > 0)&&(x < Informations.taille- 1)&&(y > 0)&&(y < Informations.taille -1))
			{
				if ((grille[x][y + 1] <= 0)&&(grille[x][y-1] <= 0)&&(grille[x+1][y] <= 0)&&(grille[x-1][y] <= 0)
						&&(grille[x+1][y+1] <= 0)&&(grille[x+1][y-1] <= 0)&&(grille[x-1][y+1] <= 0)&&(grille[x-1][y-1] <= 0))
				{
					return true;
				}
				else
				{
					return false;
				}
			}
			else 
			{
				return false;
			}
		}
		else
		{
			return false;
		}
	}
	
	/**
	 * 
	 * @param pt position ou le joueur essaye de créer un mur
	 * @param monstre_sur_le_plateau liste des monstres présents sur le plateau de jeu
	 * @return renvoie vrai si construire un mur ne bloquera pas les monstres
	 */
	public boolean peutConstruireMur(PositionTab pt, HashSet<Monster> monstre_sur_le_plateau) 
	{
		if(grille[pt.getX()][pt.getY()]<=0 && grille[pt.getX()][pt.getY()]!=-5 && grille[pt.getX()][pt.getY()]!=-10) //on vérifie que le joueur essaye de faire un mur sur la route
			return false;
		
		else 
		{
			//on vérifie d'abord que l'on peut se déplacer du spawn jusqu'au chateau
			int[][] grille_temp = new int[Informations.taille][Informations.taille];
			
			for(int ligne=0;ligne!=Informations.taille; ligne++) 
				for(int colonne=0; colonne!=Informations.taille; colonne++)
					grille_temp[ligne][colonne] = grille[ligne][colonne];
			
			
			grille_temp[pt.getX()][pt.getY()]=-10;
			Algorithm a_star = new Algorithm(grille_temp);
			
			for(PositionTab spawn : spawns) 
			{
				
				
				ArrayList<PositionTab> plusCourtChemin = a_star.fastestWay(spawn,Converter.positionToTab(pChateau));
				
				if(plusCourtChemin==null) 
				{
					return false;
				}
					
			}
			
			
			//on vérifie que les monstres sur la carte ne sont pas enfermés
			for(Monster m : monstre_sur_le_plateau) 
			{
				ArrayList<PositionTab> plusCourtChemin = a_star.fastestWay(Converter.positionToTab(m.getP()),Converter.positionToTab(pChateau));
				
				if(plusCourtChemin==null)
					return false;
			}
			
		}
		
		return true;
	}
	
	public void pose_mur(PositionTab pt) 
	{
		grille[pt.getX()][pt.getY()]=-10;
		Informations.cout_mur++;
	}
	
	
	
	
}
