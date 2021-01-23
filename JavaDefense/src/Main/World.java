package Main;

import java.util.List;
import java.util.Scanner;

import AStar.PositionTab;
import Interface.Converter;
import Interface.Position;
import Interface.Projectile;
import Interface.StdDraw;
import Jeu.ArcherTower;
import Jeu.BombTower;
import Jeu.Joueur;
import Jeu.Monster;
import Jeu.Niveau;
import Jeu.Tower;
import Jeu.Vague;
import Read.Reader;


import java.util.ArrayList;
import java.util.InputMismatchException;

public class World {
	final static int NOMBRE_NIVEAU = 3;
	// Variable static pour la taille d'un côté du monde
	public static final int taille = 15;

	// l'ensemble des monstres, pour gerer (notamment) l'affichage (finira par disparaitre)
	List<Monster> monsters = new ArrayList<>();
	List<Tower> towers = new ArrayList<>();
	List<Projectile> projectiles = new ArrayList<>();
	
	private final static Scanner sc = new Scanner(System.in);
	
	// Information sur la taille du plateau de jeu
	public static final int width = 750;
	public static final int height = 750;
	double squareWidth;
	double squareHeight;
	
	
	// Informations sur les statistiques de départ des tours
	private int prix_tour_archer = 10;
	private double range_tour_archer = 3.;
	private double speed_tour_archer = 3.;
	
	private int prix_tour_bombe = 20;
	private double range_tour_bombe = 3.;
	private double speed_tour_bombe = 3.;
	
	
	
	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;
	
	// Condition pour terminer la partie
	boolean end = false;
	
	
	// Niveau et autres informations
	Niveau niveau = null;
	Joueur joueur = null;
	boolean demarre = false;
	
	
	//information des vagues pour l'apparition des monstres
	Vague current_vague = null;
	int compteur_apparition = 0;
	final int apparition_temps = 5; //nombre d'update entre chaque apparition de monstre
	
	
	
	
	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de cases données
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World() {
		squareWidth = (double) 1 / taille;
		squareHeight = (double) 1 / taille;
		
		try {
			niveau = Reader.func("../niveaux/niveau2.niveau");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		joueur = new Joueur(niveau.getOrDepart(),20);
				
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
	}
	
	/**
	 * Définit le décors du plateau de jeu.
	 */
	 public void drawBackground() 
	 {			 
		 double n = (int)(1./taille / squareWidth) * squareWidth;//  + squareWidth / 2
		 for (int i = 0 ; i < taille ; i++)
		 {
			 for (int j = 0 ; j < taille ; j++)
			 {
				 if (niveau.getGrille()[i][j] == 0)
				 {
					 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
				 }
				 else if (niveau.getGrille()[i][j] > 0)
				 {
					 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/Road.png");
					 if (niveau.getGrille()[i][j] == 250) // Château
					 {
						 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/Tower2/Isometric/towerRound_crystals_E.png");
					 }
				 }
				 else 
				 {
					 if ((i != 0)&&(j != 0)&&(i != taille -1)&&(j != taille - 1))
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotRight.png");
						 }
						 else if (niveau.getGrille()[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if ((i == 0)&&(j == 0))
					 {
						 if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if ((i == 0)&&(j == taille -1))
					 {
						 if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if ((i == taille - 1)&&(j == 0))
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopLeft.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if ((i == taille - 1)&&(j == taille - 1))
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotLeft.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if (i == 0)
					 {
						 if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotRight.png");
						 }
						 else if (niveau.getGrille()[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if (j == 0)
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if (i == taille - 1)
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotLeft.png");
						 }
						 else if (niveau.getGrille()[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadTopLeft.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if (j == taille - 1)
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadBotRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
						 }
					 }
				 } 
			 }
		 }
		 
	 }
	 
	 public void drawTowers() 
	 {
		 for(Tower t : towers) 
		 {
			 t.draw();
		 }
	 }
	 
	 /**
	  * Affiche certaines informations sur l'écran telles que les points de vie du joueur ou son or
	  */
	 public void drawInfos() {
		 StdDraw.setPenColor(StdDraw.BLACK);
	 }
	 
	 /**
	  * Fonction qui récupère le positionnement de la souris et permet d'afficher une image de tour en temps réél
	  *	lorsque le joueur appuie sur une des touches permettant la construction d'une tour.
	  */
	 public void drawMouse() { //a quoi nous sert cette fonction ??
		double normalizedX = (int)(StdDraw.mouseX() / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(StdDraw.mouseY() / squareHeight) * squareHeight + squareHeight / 2;
		String image = null;
		switch (key) {
		case 'a' : 
			 // TODO Ajouter une image pour représenter une tour d'archers
			 break;
		case 'b' :
			// TODO Ajouter une image pour représenter une tour à canon
			 break;
		}
		 if (image != null)
			 StdDraw.picture(normalizedX, normalizedY, image, squareWidth, squareHeight);
	 }
		 
	 /**
	  * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction update() qui appelle les fonctions run() et draw() de Monster.
	  * Modifie la position du monstre au cours du temps à l'aide du paramètre nextP.
	  */
	 public void updateMonsters() {
		for(Monster m : monsters)
		{
			m.update();
			 if(m.getP().getY() < 0) {
				 m.getP().setY(1);
			 }
		}
	 }
	 
	 /**
	  * Met à jour toutes les informations du plateau de jeu ainsi que les déplacements des monstres et les attaques des tours.
	  * @return les points de vie restants du joueur
	  */
	 public int update() {
		
		drawBackground();
		
//      double normalizedY2 = (int)(7./15 / squareHeight) * squareHeight + squareHeight / 2;
//        
//		StdDraw.picture(normalizedX2, normalizedX2, "../images/tiles/RoadBotAndLeft.png");
//		StdDraw.picture(normalizedY2,normalizedY2, "../images/tiles/RoadTopAndLeft.png");
		if(demarre) 
		{
			compteur_apparition = (compteur_apparition+1)%apparition_temps;
			
			updateMonsters();
			if(current_vague==null) 
			{
				current_vague = niveau.getNextVague();
				if(current_vague==null) 
				{
					System.out.println("Félicitations, vous avez terminé le niveau : \""+niveau.getNom()+"\" !");
				}
			}
				
			else if (compteur_apparition==0) 
			{
				Position spawn = Converter.tabToPosition(niveau.getRandomSpawn());
				Monster m = current_vague.getMonster(spawn);
				if(m!=null) //un nouveau monstre apparait
				{
					monsters.add(m);
				}
				else //la vague est terminée 
				{
					System.out.println("Vague terminée !");
				}
				
			}
		}
		drawInfos();
		
		drawMouse();
		drawTowers();
		
		for(Monster m : monsters)
			System.out.println(m);
		return -1;
	 }
	 
	/**
	 * Récupère la touche appuyée par l'utilisateur et affiche les informations pour la touche séléctionnée
	 * @param key la touche utilisée par le joueur
	 */
	public void keyPress(char key) {
		key = Character.toLowerCase(key);
		this.key = key;
		switch (key) {
		case 'a':
			System.out.println("Arrow Tower selected (50g).");
			break;
		case 'b':
			System.out.println("Bomb Tower selected (60g).");
			break;
		case 'e':
			System.out.println("Evolution selected (40g).");
			break;
		case 's':
			selectionNiveau();
			break;
		case 'r':
			System.out.println("Le niveau démarre !");
			demarre=true;
			break;
		case 'q':
			System.out.println("Exiting.");
			sc.close();
		
		}
	}
	
	private void selectionNiveau() 
	{
		System.out.println("Selectionnez un niveau (entrez un nombre entre 1 et "+NOMBRE_NIVEAU+")");
		
		int numero_niveau=-1;
		do 
		{
			int temp=-1;
			try 
			{
				temp = sc.nextInt();
				if(temp>=1 && temp <= NOMBRE_NIVEAU)
				{
					System.out.println("Vous avez séléctionnez le niveau :" + temp);
					numero_niveau=temp;
				}
				else
					throw new InputMismatchException();
			}
			catch(InputMismatchException e) 
			{
				System.out.println("Vous devez entrer un nombre entier entre 1 et "+NOMBRE_NIVEAU);
			}
		}while(numero_niveau==-1);
		
		String chemin = "../niveaux/niveau"+numero_niveau+".niveau";
		try {
			niveau = Reader.func(chemin);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
	}
	
	/**
	 * Vérifie lorsque l'utilisateur clique sur sa souris qu'il peut: 
	 * 		- Ajouter une tour à la position indiquée par la souris.
	 * 		- Améliorer une tour existante.
	 * Puis l'ajouter à la liste des tours
	 * @param x
	 * @param y
	 */
	public void mouseClick(double x, double y) {
		double normalizedX = (int)(x / squareWidth) * squareWidth + squareWidth / 2;
		double normalizedY = (int)(y / squareHeight) * squareHeight + squareHeight / 2;
		Position p = new Position(normalizedX, normalizedY);
		PositionTab pt = Converter.positionToTab(p);
		switch (key) {
		case 'a':
			//on vérifie qu'il peut construire à cette endroit et qu'il a assez d'or
			if(niveau.peutConstruire(pt))
				if(joueur.payerOr(prix_tour_archer)) 
				{
					System.out.println("Une tour d'archer a été créé !");
					ArcherTower tower = new ArcherTower(prix_tour_archer,range_tour_archer, speed_tour_archer, p);
					towers.add(tower);
				}
				else
					System.out.println("Vous n'avez pas assez d'or");
					
			else
				System.out.println("Vous ne pouvez pas construire ici");
			break;
			
			
		case 'b':
			if(niveau.peutConstruire(pt))
				if(joueur.payerOr(prix_tour_bombe)) 
				{
					System.out.println("Une tour de bombes a été créé !");
					BombTower tower = new BombTower(prix_tour_bombe,range_tour_bombe, speed_tour_bombe, p);
					towers.add(tower);
				}
				else
					System.out.println("Vous n'avez pas assez d'or");
					
			else
				System.out.println("Vous ne pouvez pas construire ici");
			break;
			
		case 'e':
			System.out.println("Ici il est possible de faire évolué une des tours");
			break;
		}
	}
	
	/**
	 * Comme son nom l'indique, cette fonction permet d'afficher dans le terminal les différentes possibilités 
	 * offertes au joueur pour intéragir avec le clavier
	 */
	public void printCommands() {
		System.out.println("Press A to select Arrow Tower (cost "+prix_tour_archer+"g).");
		System.out.println("Press B to select Cannon Tower (cost "+prix_tour_bombe+"g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press S to select a level.");
		System.out.println("Press R if you're ready so start the level");
	}
	
	/**
	 * Récupère la touche entrée au clavier ainsi que la position de la souris et met à jour le plateau en fonction de ces interractions
	 */
	public void run() {
		printCommands();
		while(!end) {
			
			StdDraw.clear();
			if (StdDraw.hasNextKeyTyped()) {
				keyPress(StdDraw.nextKeyTyped());
			}
			
			if (StdDraw.isMousePressed()) {
				mouseClick(StdDraw.mouseX(), StdDraw.mouseY());
				StdDraw.pause(50);
			}
			
			update();
			StdDraw.show();
			StdDraw.pause(20);			
		}
	}
}
