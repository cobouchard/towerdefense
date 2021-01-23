package AStar;

import java.util.ArrayList;

import Jeu.Niveau;
import Read.Reader;

public class Maintest {
	public static void main(String[] args) {
		/*int[][] matrix =
			{
			    {17,16,1,0,0,0},
			    {0,0,2,3,0,0},
			    {0,0,0,4,14,0},
			    {8,7,6,5,12,13},
			    {0,15,9,11,0,0},
			    {0,0,0,10,0,0}
			};
		
		Algorithm a_star = new Algorithm(matrix);
		
		ArrayList<PositionTab> plusCourtChemin = a_star.fastestWay(new PositionTab(0,1), new PositionTab(4,3));
		
		System.out.println(plusCourtChemin);
		System.out.println(a_star.fastestWay(new PositionTab(4,3), new PositionTab(0,1)));*/
		
		
		
		Niveau n = null;
		try {
			n=Reader.func("../niveaux/niveau2.niveau");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Algorithm a_star = new Algorithm(n.getGrille());
		
		ArrayList<PositionTab> plusCourtChemin = a_star.fastestWay(new PositionTab(6,5), new PositionTab(11,5));
		
		System.out.println(plusCourtChemin);
	}
}
