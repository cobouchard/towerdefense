package Jeu;

import java.util.ArrayList;

import Read.Reader;

public class Niveau {
	private String nom;
	private int[][] grille;
	private ArrayList<Vague> vagues;
	
	public String getNom() {
		return nom;
	}
	public int[][] getGrille() {
		return grille;
	}
	
	public Vague getNextVague() 
	{
		Vague v = vagues.get(0);
		vagues.remove(0);
		return v;
	}
	
	public Niveau(String nom, int[][] grille, ArrayList<Vague> vagues) {
		this.nom = nom;
		this.grille = grille;
		this.vagues = vagues;
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
