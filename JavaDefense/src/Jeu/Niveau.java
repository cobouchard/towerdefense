package Jeu;

import java.util.ArrayList;

import Read.Reader;

public class Niveau {
	private String nom;
	private int[][] grille;
	private ArrayList<Vague> vagues;
	private final int or_depart;
	
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
	
	/**
	 * 
	 * @return renvoie la prochaine vague de monstre et la supprime de la liste, renvoie null si le niveau est terminé
	 */
	public Vague getNextVague() 
	{
		Vague v = vagues.get(0);
		vagues.remove(0);
		return v;
	}
	
	public Niveau(String nom, int[][] grille, ArrayList<Vague> vagues, int or_depart) {
		this.nom = nom;
		this.grille = grille;
		this.vagues = vagues;
		this.or_depart=or_depart;
	}
	
	public void affiche() 
	{
		System.out.println("Niveau : " + nom);
		for(int ligne=0;ligne!=Reader.TAILLE; ligne++) 
		{
			for(int colonne=0; colonne!=Reader.TAILLE; colonne++)
				System.out.print(grille[ligne][colonne] + ",");
			System.out.println();
		}
		for(Vague v : vagues)
			System.out.println(v);
			
	}
	
	
	
	
}
