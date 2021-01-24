package Jeu;

public class Informations {
	// Informations sur les statistiques de départ des tours
	
	//archer
	public static int prix_tour_archer = 70;
	public static double range_tour_archer = 0.2;
	public static int speed_tour_archer = 30;
	public static int coutAmeliorationArcher = 40;
	public static int facteurAugmentationtCoutAmeliorationArcher = 20;
	public static int degats_tour_archer = 5;
		
	//bombardier
	public static int prix_tour_bombe = 100;
	public static double range_tour_bombe = 0.2;
	public static int speed_tour_bombe = 50;
	public static int coutAmeliorationBombe = 50;
	public static int facteurAugmentationtCoutAmeliorationBombe = 10;
	public static int degats_tour_bombe = 8;
	
	

	//vitesse d'apparition des monstres
	public static int compteur_apparition = 0;
	public static int apparition_temps = 50; //nombre d'update entre chaque apparition de monstre
	
	//monstre volant
	public static int degatsVolant = 6;
	public static int orVolant = 5;
	public static double speedVolant = 0.005;
	public static int pdvVolant = 27;
	
	//monstre de base
	public static int degatsBase = 4;
	public static int orBase = 3;
	public static double speedBase = 0.003;
	public static int pdvBase = 40;
	
	//monstre Boss
	public static int degatsBoss = 25;
	public static int orBoss = 20;
	public static double speedBoss = 0.002;
	public static int pdvBoss = 500;
	
	
	//taille du plateau
	public static final int taille = 18;
	public final static int NOMBRE_NIVEAU = 3;
	
	public static int cout_mur = 2;
	
	public static int joueurPdv = 40;
}
