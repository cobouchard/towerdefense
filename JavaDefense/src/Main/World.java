package Main;

import java.util.Scanner;

import AStar.PositionTab;
import Interface.Converter;
import Interface.Position;
import Interface.Projectile;
import Interface.StdDraw;
import Jeu.ArcherTower;
import Jeu.BombTower;
import Jeu.FlyingMonster;
import Jeu.Informations;
import Jeu.Joueur;
import Jeu.Monster;
import Jeu.Niveau;
import Jeu.Tower;
import Jeu.Vague;
import Read.Reader;


import java.util.HashSet;
import java.util.InputMismatchException;

public class World {

	// l'ensemble des monstres, pour gerer (notamment) l'affichage (finira par disparaitre)
	HashSet<Monster> monsters = new HashSet<>();
	HashSet<Tower> towers = new HashSet<>();
	HashSet<Projectile> projectiles = new HashSet<>();
	
	private final static Scanner sc = new Scanner(System.in);
	
	// Information sur la taille du plateau de jeu
	public static final double width = 1000;
	public static final double height = 1000;
	double squareWidth = 25;
	double squareHeight = 25;	
	
	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;
	
	// Condition pour terminer la partie
	boolean perdu = false;
	boolean end = false;
	
	
	// Niveau et autres informations
	Niveau niveau = null;
	Joueur joueur = null;
	boolean demarre = false;
	
	
	//information des vagues pour l'apparition des monstres
	Vague current_vague = null;
	
	// empeche le double clic
	private int temps=0;
	private final int temps_entre_2_clic = 10;

	
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
		squareWidth = (double) 1 / Informations.taille;
		squareHeight = (double) 1 / Informations.taille;
		
		try {
			niveau = Reader.func("../niveaux/niveau2.niveau");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		joueur = new Joueur(niveau.getOrDepart(),20);
				
		StdDraw.setCanvasSize((int)width, (int)height);
		StdDraw.enableDoubleBuffering();
	}
	
	/**
	 * Définit le décors du plateau de jeu.
	 */
	 public void drawBackground() 
	 {			 
		
		 double n = (int)(1./Informations.taille / squareWidth) * squareWidth;//  + squareWidth / 2
		 for (int i = 0 ; i < Informations.taille ; i++)
		 {
			 for (int j = 0 ; j < Informations.taille ; j++)
			 {
				 if (niveau.getGrille()[i][j] == 0)
				 {
					 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/NotConstructible.png");
				 }
				 else if (niveau.getGrille()[i][j] > 0)
				 {
					 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/Road.png");
					 if (niveau.getGrille()[i][j] == 250) // Château
					 {
						 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/Tower2/Isometric/towerRound_crystals_E.png");
					 }
				 }
				 else 
				 {
					 if ((i != 0)&&(j != 0)&&(i != Informations.taille -1)&&(j != Informations.taille - 1))
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotRight.png");
						 }
						 else if (niveau.getGrille()[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if ((i == 0)&&(j == 0))
					 {
						 if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if ((i == 0)&&(j == Informations.taille -1))
					 {
						 if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if ((i == Informations.taille - 1)&&(j == 0))
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopLeft.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if ((i == Informations.taille - 1)&&(j == Informations.taille - 1))
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotLeft.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if (i == 0)
					 {
						 if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotRight.png");
						 }
						 else if (niveau.getGrille()[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if (j == 0)
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if (i == Informations.taille - 1)
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTop.png");
						 }
						 else if (niveau.getGrille()[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotLeft.png");
						 }
						 else if (niveau.getGrille()[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadTopLeft.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
						 }
					 }
					 else if (j == Informations.taille - 1)
					 {
						 if ((niveau.getGrille()[i - 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if ((niveau.getGrille()[i + 1][j] > 0)&&(niveau.getGrille()[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if (niveau.getGrille()[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadRight.png");
						 }
						 else if (niveau.getGrille()[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBot.png");
						 }
						 else if (niveau.getGrille()[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotLeft.png");
						 }
						 else if (niveau.getGrille()[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/RoadBotRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+squareWidth/width,n*j+squareWidth/width,"../images/tiles/EmptyTile.png");
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
	 
	 public void drawProjectiles() 
	 {
		 for(Projectile p : projectiles) 
		 {
			 p.update();
		 }
	 }
	 
	 /**
	  * Affiche certaines informations sur l'écran telles que les points de vie du joueur ou son or
	  */
	 public void drawInfos() {
		 StdDraw.setPenColor(StdDraw.BLACK);
		 StdDraw.picture(0.9, 0.95, "../images/health_and_gold/TransparentPNG/Health/frame-1.png");
		 StdDraw.text(0.94,0.95,""+joueur.getPdv());
		 StdDraw.picture(0.9, 0.9, "../images/health_and_gold/TransparentPNG/Coin/frame-1.png");
		 StdDraw.text(0.94,0.9,""+joueur.getOr());		 
	 }
		 
	 /**
	  * Pour chaque monstre de la liste de monstres de la vague, utilise la fonction update() qui appelle les fonctions run() et draw() de Monster.
	  * Modifie la position du monstre au cours du temps à l'aide du paramètre nextP.
	  */
	 public void updateMonsters() {
		HashSet<Monster> monstres_arrives = new HashSet<>();
		
		
		for(Monster m : monsters)
		{
			m.update();
			if(m.getP().dist(niveau.getPChateau()) < 0.008)
			{
				if(joueur.perdrePv(m.getDegats())) 
				{
					perdu = true;
				}
				
				monstres_arrives.add(m);
			}
		}
		if(demarre)
			monsters.removeAll(monstres_arrives);
	 }
	 
	 /**
	  * @param m un monstre
	  * @param t une tour
	  * @return un booléen indiquant si le monstre est à portée de la tour
	  */
	 public boolean checkTowerRange(Tower t, Monster m) {
		 return (t.getP().dist(m.getP()) <= t.getRange());
	 }
	 
	 public boolean checkProjectileHit(Projectile proj) 
	 {
		 return proj.getP().dist(proj.getMonster().getP())<0.03;
	 }
	 
	 public void checkProjectiles() 
	 {
		 HashSet<Projectile> proj_touches = new HashSet<>();
		 for(Projectile proj : projectiles) 
		 {
			 if(checkProjectileHit(proj)) 
			 {
				 Monster m = proj.getMonster();
				 
				 if(m!=null) //si jamais un monstre arrive au chateau avant de se faire toucher		 
					 if( m.perdrePv(proj.getDegats()) ) //si le monstre est mort
					 {
						 joueur.gagnerOr(m.getOr());
						 monsters.remove(m);
					 }
				 proj_touches.add(proj);
				 
			 }
		 }
		 projectiles.removeAll(proj_touches);
	 }
	 
	 /**
	  * Vérifie si le projectile a de l'effet sur le monstre
	  */
	 public boolean isEfficient(Monster m, Tower t)
	 {
		 if ((m instanceof FlyingMonster)&&(t instanceof BombTower))
		 {
			 return false;
		 }
		 else return true;
	 }
	 
	 /**
	  * Vérifie si des monstres sont à portée de chaque tour et tire si c'est le cas
	  */
	 public void shotMonster() {
		 for (Tower t : towers)
		 {
			 for (Monster m : monsters)
			 {
				 if ((checkTowerRange(t,m))&&(isEfficient(m,t))) {
					 if (t.getCompteur() == 0)
					 {
						 Projectile projectile = t.getProjectile(m);
						 projectiles.add(projectile);
					 }
					 t.updateCompteur(); 
				 } 
				 
			 }
		 }
	 }

	 public void lose() {
		 System.out.println("Vous n'avez plus de points de vie, vous avez perdu !");;
	}
	 

	 public void win() {
		 System.out.println("Vous avez vaincu tous les monstres, vous avez gagné !");;
	 }
	 
	 public void clean() 
	 {
		 demarre=false;
		 projectiles.clear();
		 monsters.clear();
		 towers.clear();
	 }
	 
	 /**
	  * Met à jour toutes les informations du plateau de jeu ainsi que les déplacements des monstres et les attaques des tours.
	  * @return les points de vie restants du joueur
	  */
	 public int update() {
		 
		 if(temps!=0)
			 temps=(temps+1)%temps_entre_2_clic;
		 
		 drawBackground();
		 drawInfos();
		 drawProjectiles();
		 shotMonster();

		 if(demarre) 
		 {
			 Informations.compteur_apparition = (Informations.compteur_apparition+1)%Informations.apparition_temps;
			 updateMonsters();
			 checkProjectiles();
			 if(current_vague==null)
			 {
				 current_vague = niveau.getNextVague();
				 if(current_vague==null) 
				 {
					 clean();
					 win();
				 }
			 }

			 else if (Informations.compteur_apparition==0) 
			 {
				 Position spawn = Converter.tabToPosition(niveau.getRandomSpawn());
				 Monster m = current_vague.getMonster(spawn);
				 if(m!=null) //un nouveau monstre apparait
				 {
					 monsters.add(m);
					 m.updateChemin(niveau.getGrille(), niveau.getPChateau());

				 }
				 else if (monsters.isEmpty()) //la vague est terminée 
				 {
					 System.out.println("Vague terminée !");
					 current_vague=null;
				 }

			 }
			 
			 if(perdu) 
			 {
				 clean();
				 lose();
			 }
		 }
		 drawInfos();

		 drawTowers();


		 drawTowers();

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
		case 'm':
			System.out.println("Mur séléctionné (" + Informations.cout_mur + "g).");
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
		System.out.println("Selectionnez un niveau (entrez un nombre entre 1 et "+Informations.NOMBRE_NIVEAU+")");
		
		int numero_niveau=-1;
		do 
		{
			int temp=-1;
			try 
			{
				temp = sc.nextInt();
				if(temp>=1 && temp <= Informations.NOMBRE_NIVEAU)
				{
					System.out.println("Vous avez séléctionnez le niveau :" + temp);
					numero_niveau=temp;
				}
				else
					throw new InputMismatchException();
			}
			catch(InputMismatchException e) 
			{
				System.out.println("Vous devez entrer un nombre entier entre 1 et "+Informations.NOMBRE_NIVEAU);
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
		
		if(temps==0) 
		{
			double normalizedX = (int)(x / squareWidth) * squareWidth + squareWidth / 2;
			double normalizedY = (int)(y / squareHeight) * squareHeight + squareHeight / 2;
			Position p = new Position(normalizedX, normalizedY);
			PositionTab pt = Converter.positionToTab(p);
			switch (key) {
			case 'a':
				//on vérifie qu'il peut construire à cette endroit et qu'il a assez d'or
				if(niveau.peutConstruire(pt))
					if(joueur.payerOr(Informations.prix_tour_archer)) 
					{
						System.out.println("Une tour d'archer a été créé !");
						ArcherTower tower = new ArcherTower(Informations.prix_tour_archer,Informations.range_tour_archer, Informations.speed_tour_archer, new Position(p), 0);
						towers.add(tower);
					}
					else
						System.out.println("Vous n'avez pas assez d'or");
						
				else
					System.out.println("Vous ne pouvez pas construire ici");
				break;
				
				
			case 'b':
				if(niveau.peutConstruire(pt))
					if(joueur.payerOr(Informations.prix_tour_bombe)) 
					{
						System.out.println("Une tour de bombes a été créé !");
						BombTower tower = new BombTower(Informations.prix_tour_bombe,Informations.range_tour_bombe, Informations.speed_tour_bombe, new Position(p), 0);
						
						towers.add(tower);
					}
					else
						System.out.println("Vous n'avez pas assez d'or");
						
				else
					System.out.println("Vous ne pouvez pas construire ici");
				break;
				
			case 'm':
				if(niveau.peutConstruireMur(pt, monsters))
					if(joueur.payerOr(Informations.cout_mur))
					{
						niveau.pose_mur(pt);
						System.out.println("Un mur a été créé");
						
					}
					else
						System.out.println("Vous n'avez pas assez d'or");
				else
					System.out.println("Pas le droit de faire un mur ici");
				break;
				
			case 'e':
				
				for(Tower t : towers) 
				{
					if(Converter.positionToTab(t.getP()).equals(pt)) //on cherche la tour 
					{
						if(joueur.payerOr(t.coutAmelioration())) 
						{
							t.ameliorerTour();
							System.out.println("La tour a été amélioré au niveau " + t.getLevel());
						}
							
						else
							System.out.println("Vous n'avez pas assez d'or pour améliorer la tour, coût = " + t.coutAmelioration() + "g.");
						
						break;
					}
				}
				
				break;
			}
		}
		
		temps=1;
	}
	
	/**
	 * Comme son nom l'indique, cette fonction permet d'afficher dans le terminal les différentes possibilités 
	 * offertes au joueur pour intéragir avec le clavier
	 */
	public void printCommands() {
		System.out.println("Press A to select Arrow Tower (cost "+Informations.prix_tour_archer+"g).");
		System.out.println("Press B to select Cannon Tower (cost "+Informations.prix_tour_bombe+"g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press S to select a level.");
		System.out.println("Press R if you're ready so start the level");
		System.out.println("Appuez sur m pour selectionner les murs");
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
