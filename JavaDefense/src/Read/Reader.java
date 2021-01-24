package Read;


import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

import AStar.PositionTab;
import Jeu.BaseMonster;
import Jeu.BossMonster;
import Jeu.FlyingMonster;
import Jeu.Informations;
import Jeu.Niveau;
import Jeu.Vague;

/**
 * Structure d'un fichier de niveau :
 * Nom du niveau
 * Or de depart du niveau
 * Numéros des spawns
 * Grille du jeu
 * Vague 1 (type de monstre, nombre, difficulté)
 * Vague 2
 * Vague ...
 * Vague n
 * @author Corentin Bouchard and Enoal Gesny
 *
 */
public final class Reader {
	
	// lit un fichier pour renvoyer les données du niveau correspondantes
	public static Niveau func(String cheminNiveau) throws Exception
	{
		File niveau_file = new File(cheminNiveau);
		Scanner sc = new Scanner(niveau_file);
		String chaine;
		int[][] grille = new int[Informations.taille][Informations.taille];
		ArrayList<Vague> vagues = new ArrayList<>();
		int ligne;
		int colonne;
		
		//on récupère le nom du niveau
		String nom_niveau = sc.nextLine();
		
		//on récupère l'or de départ du niveau
		int or_depart = Integer.parseInt(sc.nextLine());
		
		//on récupère les numéros des spawns
		String[] spawns_num_chaine = sc.nextLine().split(",");
		int[] spawns_num = new int[spawns_num_chaine.length];
		for(int i=0; i!=spawns_num_chaine.length; i++) 
		{
			spawns_num[i] = Integer.parseInt(spawns_num_chaine[i]);
		}
		ArrayList<PositionTab> spawns = new ArrayList<>();
		
		
		//on récupère la grille
		for(ligne=0; ligne!= Informations.taille; ligne++) 
		{
			colonne=0;
			chaine=sc.nextLine();
			for(String s : chaine.split(","))
			{
				grille[ligne][colonne]=Integer.parseInt(s);
				
				//on peut grâce à la grille trouver l'emplacement des spawns
				for(Integer i : spawns_num)
					if(grille[ligne][colonne]==i)
						spawns.add(new PositionTab(ligne,colonne));
				
				colonne++;
			}
		}
		
		//on récupère les vagues de monstre
		while(sc.hasNextLine()) 
		{
			chaine=sc.nextLine();
			String[] decoupe = chaine.split(",");
			
			switch(Integer.parseInt(decoupe[0])) 
			{
			case 1:
				vagues.add(new Vague(Integer.parseInt(decoupe[1]),Integer.parseInt(decoupe[2]),new BaseMonster(null)));
				
				break;
			case 2:
				vagues.add(new Vague(Integer.parseInt(decoupe[1]),Integer.parseInt(decoupe[2]), new FlyingMonster(null)));
				break;
			case 3:
				vagues.add(new Vague(Integer.parseInt(decoupe[1]),Integer.parseInt(decoupe[2]), new BossMonster(null)));
				break;
			}
			
		}
		
		sc.close();
		return new Niveau(nom_niveau,grille,vagues, or_depart, spawns);
	}
	
	public static void main(String[] args) {
		Niveau n = null;
		try {
			n=func("../niveaux/niveau2.niveau");
		} catch (Exception e) {
			e.printStackTrace();
		}
		n.affiche();
	}
}
