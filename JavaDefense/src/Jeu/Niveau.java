package Jeu;

import java.util.ArrayList;

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
		for(int ligne=0;ligne!=Reader.TAILLE; ligne++) 
		{
			for(int colonne=0; colonne!=Reader.TAILLE; colonne++)
				System.out.print(grille[ligne][colonne] + ",");
			System.out.println();
		}
		for(Vague v : vagues)
			System.out.println(v);
			
	}
	public void poserTourArcher(PositionTab position_tab) {
		// TODO Auto-generated method stub
		//On attribue la valeur -5 à une tour dans la grille
		int x = position_tab.getX();
		int y = position_tab.getY();
		if (peutConstruire(new PositionTab(x,y)))
		{
			grille[x][y] = -5;
		}
	}
	
	public boolean peutConstruire(PositionTab pt) 
	{
		// TODO Auto-generated method stub
		int x = pt.getX();
		int y = pt.getY();
		if ((grille[x][y] < 0)&&(grille[x][y] != -5))
		{
			if ((x > 0)&&(x < Reader.TAILLE- 1)&&(y > 0)&&(y < Reader.TAILLE -1))
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
	
	
	
	
}
