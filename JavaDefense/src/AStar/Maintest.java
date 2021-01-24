package AStar;

import java.util.ArrayList;

import Jeu.Niveau;
import Read.Reader;

public class Maintest {
	public static void main(String[] args) {
		
		Niveau n = null;
		try {
			n=Reader.func("../niveaux/niveau2.niveau");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Algorithm a_star = new Algorithm(n.getGrille());
		
		ArrayList<PositionTab> plusCourtChemin = a_star.fastestWay(new PositionTab(0,5), new PositionTab(11,5));
		
		System.out.println(plusCourtChemin);
	}
}
