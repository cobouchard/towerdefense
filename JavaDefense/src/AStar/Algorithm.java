package AStar;

import java.lang.Math;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * 
 * @author Corentin Bouchard et Enoal Gesny
 * Cette classe implémente l'algorithme A*
 * https://en.wikipedia.org/wiki/A*_search_algorithm
 * On considère que les monstres ne se déplace pas en diagonale (seulement vers gauche, droite, haut, bas)
 */
public class Algorithm {
	private Cell[][] table;
	private final int TAILLE=6;
	
	private HashSet<Cell> open_cells;
	private HashSet<Cell> closed_cells;
	
	@Override
	public String toString() 
	{
		String result = "";
		for(int ligne=0; ligne!=TAILLE; ligne ++) 
		{
			for(int colonne=0; colonne!=TAILLE; colonne++)
				result += table[ligne][colonne].toString() + " ; " ;
			result += "\n";
		}
		return result;	
	}
	
	/**
	 * 
	 * @param depart
	 * @param arrivee
	 * @return retourne la distance entre les 2 points
	 */
	private static int distance(PositionTab depart, PositionTab arrivee) 
	{
		return Math.abs(depart.getX()-arrivee.getX())+Math.abs(depart.getY()-arrivee.getY());
	}
	
	public Algorithm(int[][] int_table) 
	{
		table = new Cell[TAILLE][TAILLE];
		for(int ligne=0; ligne!=TAILLE; ligne ++)
			for(int colonne=0; colonne!=TAILLE; colonne++)
				table[ligne][colonne]= new Cell(int_table[ligne][colonne]);
	}
	
	/**
	 * renvoie une cellule en fonction de sa position
	 * @param p position
	 * @return cellule
	 */
	private Cell findCell(PositionTab p) 
	{
		return table[p.getX()][p.getY()];
	}
	
	/**
	 * cherche une position en fonction d'une cellule
	 * @param c cellule à localiser
	 * @return position de la cellule dans le tableau
	 */
	private PositionTab findPosition(Cell c) 
	{
		for(int ligne=0; ligne!=TAILLE; ligne ++)
			for(int colonne=0; colonne!=TAILLE; colonne++)
				if(table[ligne][colonne].equals(c))
					return new PositionTab(ligne,colonne);
		return null;
	}
	
	/**
	 * Trouve la cellule la plus prometteuse pour aller à l'arrivée. Cherche le plus faible total cost et plus faible h cost en cas d'égalité.
	 * @return renvoie la prochaine cellule à explorer
	 */
	private Cell getMinCostCellInOpen() 
	{
		Cell min = null;
		for(Cell c : open_cells) 
		{
			if(min==null)
				min=c;
			else if(c.getTotalCost()<min.getTotalCost())
				min=c;
			else if(c.getTotalCost()==min.getTotalCost() && c.gethCost() < min.gethCost())
				min=c;
		}
		return min;
	}
	
	/**
	 * trouve la prochaine position du chemin
	 * @param p position actuelle
	 * @return prochaine position dans le chemin
	 */
	private PositionTab getMinCostAdjacentPosition(PositionTab p) 
	{
		Cell temp_cell = null;
		PositionTab best_posit = null;
		int best_cost = Integer.MAX_VALUE;
		
		//cellule du haut
		if(p.getX()-1 >=0 && p.getX()-1 < TAILLE) 
		{
			temp_cell = table[p.getX()-1][p.getY()];
			if(temp_cell.getValue()>0 && temp_cell.getTotalCost()>0) //si le total cost est 0 la cellule n'a pas ete visité (mais ce n'est pas le bon chemin)
			{
				best_posit = new PositionTab(p.getX()-1,p.getY());
				best_cost = temp_cell.getgCost();
			}
		}
		
		
		//cellule du bas
		if(p.getX()+1 >=0 && p.getX()+1 < TAILLE) 
		{
			temp_cell = table[p.getX()+1][p.getY()];
			if(temp_cell.getValue()>0  && temp_cell.getTotalCost()>0)
			{
				if(best_posit==null || best_cost>temp_cell.getgCost()) 
				{
					best_posit=new PositionTab(p.getX()+1,p.getY());
					best_cost=temp_cell.getgCost();
				}
			}
		}
		
		//cellule de gauche
		if(p.getY()-1 >=0 && p.getY()-1 < TAILLE) 
		{
			temp_cell = table[p.getX()][p.getY()-1];
			if(temp_cell.getValue()>0  && temp_cell.getTotalCost()>0)
			{
				if(best_posit==null || best_cost>temp_cell.getgCost()) 
				{
					best_posit=new PositionTab(p.getX(),p.getY()-1);
					best_cost=temp_cell.getgCost();
				}
			}
		}
		
		//cellule de droite
		if(p.getY()+1 >=0 && p.getY()+1 < TAILLE) 
		{
			temp_cell = table[p.getX()][p.getY()+1];
			if(temp_cell.getValue()>0  && temp_cell.getTotalCost()>0)
			{
				if(best_posit==null || best_cost>temp_cell.getgCost()) 
				{
					best_posit=new PositionTab(p.getX(),p.getY()+1);
					best_cost=temp_cell.getgCost();
				}
			}
		}
		
		return best_posit;
	}
	
	/**
	 * renvoie le chemin le plus court entre 2 positions, null si il n'existe pas de chemin
	 * @param depart
	 * @param arrivee
	 * @return liste des positions dans l'ordre inverse (de l'arrivee au depart)
	 */
	public ArrayList<PositionTab> fastestWay(PositionTab depart, PositionTab arrivee) 
	{
		for(int ligne=0; ligne!=TAILLE; ligne ++)
			for(int colonne=0; colonne!=TAILLE; colonne++) 
			{
				table[ligne][colonne].setgCost(0);
				table[ligne][colonne].sethCost(0);
			}
				
		open_cells = new HashSet<Cell>();
		closed_cells = new HashSet<Cell>();
		ArrayList<PositionTab> chemin = new ArrayList<>();
		
		
		Cell temp_cell = findCell(depart);
		closed_cells.add(temp_cell);
		temp_cell.sethCost(distance(new PositionTab(depart.getX(),depart.getY()), arrivee));
		
		Cell current_cell = temp_cell;
		PositionTab current_pos = depart;
		
		boolean test = true;
		while(test) 
		{
			//calcul des coûts
			
			//cellule du haut
			if(current_pos.getX()-1 >=0 && current_pos.getX()-1 < TAILLE) 
			{
				temp_cell = table[current_pos.getX()-1][current_pos.getY()];
				if(temp_cell.getValue()>0 && !closed_cells.contains(temp_cell)) //la cellule courante est un chemin et pas déjà parcourue
				{
					temp_cell.setgCost(current_cell.getgCost()+1);
					
					if(temp_cell.gethCost()==0) //inutile de le recalculer
						temp_cell.sethCost(distance(new PositionTab(current_pos.getX()-1,current_pos.getY()), arrivee));
					open_cells.add(temp_cell);
				}
			}
			
			
			//cellule du bas
			if(current_pos.getX()+1 >=0 && current_pos.getX()+1 < TAILLE) 
			{
				temp_cell = table[current_pos.getX()+1][current_pos.getY()];
				if(temp_cell.getValue()>0 && !closed_cells.contains(temp_cell)) //la cellule courante est un chemin et pas déjà parcourue
				{
					temp_cell.setgCost(current_cell.getgCost()+1);
					if(temp_cell.gethCost()==0)
						temp_cell.sethCost(distance(new PositionTab(current_pos.getX()+1,current_pos.getY()), arrivee));
					open_cells.add(temp_cell);
				}
			}
			
			//cellule de gauche
			if(current_pos.getY()-1 >=0 && current_pos.getY()-1 < TAILLE) 
			{
				temp_cell = table[current_pos.getX()][current_pos.getY()-1];
				if(temp_cell.getValue()>0 && !closed_cells.contains(temp_cell)) //la cellule courante est un chemin et pas déjà parcourue
				{
					temp_cell.setgCost(current_cell.getgCost()+1);
					if(temp_cell.gethCost()==0)
						temp_cell.sethCost(distance(new PositionTab(current_pos.getX(),current_pos.getY()-1), arrivee));
					open_cells.add(temp_cell);
				}
			}
			
			//cellule de droite
			if(current_pos.getY()+1 >=0 && current_pos.getY()+1 < TAILLE) 
			{
				temp_cell = table[current_pos.getX()][current_pos.getY()+1];
				if(temp_cell.getValue()>0 && !closed_cells.contains(temp_cell)) //la cellule courante est un chemin et pas déjà parcourue
				{
					temp_cell.setgCost(current_cell.getgCost()+1);
					if(temp_cell.gethCost()==0)
						temp_cell.sethCost(distance(new PositionTab(current_pos.getX(),current_pos.getY()+1), arrivee));
					open_cells.add(temp_cell);
				}
			}
			//on cherche la cellule open avec un f cost minimal (puis hcost pour départager)
			Cell min = getMinCostCellInOpen();
			
			current_cell = min;
			current_pos = findPosition(current_cell); 
			closed_cells.add(current_cell);
			
			
			if(current_cell.equals(findCell(arrivee))) // si on est arrivé
				test=false;
			
			if(open_cells.isEmpty())
				return null;
		}
		System.out.println(this);
		
		//il faut maintenant re faire le chemin en partant de l'arrivee et en suivant les fcost décroissant
		chemin.add(arrivee);
		PositionTab temp_posit = arrivee;
		while(!temp_posit.equals(depart)) 
		{
			temp_posit = getMinCostAdjacentPosition(temp_posit);
			chemin.add(temp_posit);
		}
		
		
		
		return chemin;
	}
}
