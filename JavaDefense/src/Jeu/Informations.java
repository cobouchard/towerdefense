package Jeu;

public class Informations {
	// Informations sur les statistiques de départ des tours
	
	//archer
	public static int prix_tour_archer = 50;
	public static double range_tour_archer = 0.2;
	public static int speed_tour_archer = 20;
		
	//bombardier
	public static int prix_tour_bombe = 60;
	public static double range_tour_bombe = 0.2;
	public static int speed_tour_bombe = 20;
	

	//vitesse d'apparition des monstres
	public static int compteur_apparition = 0;
	public static final int apparition_temps = 50; //nombre d'update entre chaque apparition de monstre
	
	//monstre volant
	public static int degatsVolant = 2;
	public static int orVolant = 5;
	public static double speedVolant = 0.02;
	
	//monstre volant
	public static int degatsBase = 1;
	public static int orBase = 5;
	public static double speedBase = 0.01;
	
	
	//taille du plateau
	public static final int taille = 15;
	public final static int NOMBRE_NIVEAU = 3;
}
