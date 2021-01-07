package Read;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import Jeu.BaseMonster;
import Jeu.FlyingMonster;
import Jeu.Niveau;
import Jeu.Vague;

/**
 * Structure d'un fichier de niveau :
 * Nom du niveau
 * Grille du jeu
 * Vague 1 (type de monstre, nombre, difficulté)
 * Vague 2
 * Vague ...
 * Vague n
 * @author Corentin Bouchard and Enoal Gesny
 *
 */
public final class Reader {
	
	public static final int TAILLE = 15;
	
	public static Niveau func(String cheminNiveau) throws Exception
	{
		File niveau_file = new File(cheminNiveau);
		Scanner sc = new Scanner(niveau_file);
		String chaine;
		
		String nom_niveau = sc.nextLine();
		int[][] grille = new int[TAILLE][TAILLE];
		ArrayList<Vague> vagues = new ArrayList<>();
		
		int ligne;
		int colonne;
		
		//on récupère la grille
		for(ligne=0; ligne!= TAILLE; ligne++) 
		{
			colonne=0;
			chaine=sc.nextLine();
			for(String s : chaine.split(","))
			{
				grille[ligne][colonne]=Integer.parseInt(s);
				colonne++;
			}
		}
		
		//on récupère les vagues de monstre
		while(sc.hasNextLine()) 
		{
			chaine=sc.nextLine();
			String[] decoupe = chaine.split(",");
			
			System.out.println(decoupe[0]);
			
			switch(Integer.parseInt(decoupe[0])) 
			{
			case 1:
				vagues.add(new Vague(Integer.parseInt(decoupe[1]),Integer.parseInt(decoupe[2]),new BaseMonster(null)));
				
				break;
			case 2:
				vagues.add(new Vague(Integer.parseInt(decoupe[1]),Integer.parseInt(decoupe[2]), new FlyingMonster(null)));
				break;
			}
			
		}
		
		sc.close();
		return new Niveau(nom_niveau,grille,vagues);
	}
	
	public static void main(String[] args) {
		Niveau n = null;
		try {
			n=func("../niveaux/niveau1.niveau");
		} catch (Exception e) {
			e.printStackTrace();
		}
		n.affiche();
	}
}
