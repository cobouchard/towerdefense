package Main;

import java.util.List;


import Interface.Position;
import Interface.StdDraw;
import Jeu.Monster;
import Jeu.Niveau;

import Read.Reader;

import java.util.LinkedList;

import java.util.ArrayList;
import java.util.Iterator;

public class World {
	// Variable static pour la taille d'un côté du monde
	static final int taille = 15;
	
	// l'ensemble des monstres, pour gerer (notamment) l'affichage
	List<Monster> monsters = new ArrayList<Monster>();
	
	// Position par laquelle les monstres vont venir
	ArrayList<Position> spawns;
	
	// Information sur la taille du plateau de jeu
	int width;
	int height;
	int nbSquareX;
	int nbSquareY;
	double squareWidth;
	double squareHeight;
	
	// Nombre de points de vie du joueur
	int life = 20;
	
	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;
	
	// Condition pour terminer la partie
	boolean end = false;
	
	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de cases données
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World(int width, int height, int nbSquareX, int nbSquareY, int startSquareX, int startSquareY) {
		this.width = width;
		this.height = height;
		this.nbSquareX = nbSquareX;
		this.nbSquareY = nbSquareY;
		squareWidth = (double) 1 / nbSquareX;
		squareHeight = (double) 1 / nbSquareY;
		spawns = new ArrayList<>();
		spawns.add(new Position(startSquareX * squareWidth + squareWidth / 2, startSquareY * squareHeight + squareHeight / 2));
				
		StdDraw.setCanvasSize(width, height);
		StdDraw.enableDoubleBuffering();
	}
	
	/**
	 * Définit le décors du plateau de jeu.
	 */
	 public void drawBackground(int[][]world) {	// Tab en entrée
		 
		 for (int i = 0 ; i < taille ; i++)
		 {
			 for (int j = 0 ; j < taille ; j++)
			 {
				 if (world[i][j] == 0)
				 {
					 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/EmptyTile.png");
				 }
				 else if (world[i][j] > 0)
				 {
					 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/Road.png");
				 }
				 else 
				 {
					 if ((i != 0)&&(j != 0)&&(i != taille -1)&&(j != taille - 1))
					 {
						 if ((world[i - 1][j] > 0)&&(world[i][j - 1] > 0)) // Top and left
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadTopAndLeft.png");
						 }
						 else if ((world[i + 1][j] > 0)&&(world[i][j - 1] > 0)) // Top and right
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadTopAndRight.png");
						 }
						 else if ((world[i - 1][j] > 0)&&(world[i][j + 1] > 0)) // Bot and left
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadBotAndLeft.png");
						 }
						 else if ((world[i + 1][j] > 0)&&(world[i][j + 1] > 0)) // Bot and right
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadBotAndRight.png");
						 }
						 else if (world[i - 1][j] > 0) // Left
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadLeft.png");
						 }
						 else if (world[i + 1][j] > 0) // Right
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadRight.png");
						 }
						 else if (world[i][j - 1] > 0) //Top
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadTop.png");
						 }
						 else if (world[i][j + 1] > 0) // Bot
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadBot.png");
						 }
						 else if (world[i - 1][j - 1] > 0) // TopLeft
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadTopLeft.png");
						 }
						 else if (world[i + 1][j - 1] > 0) // TopRight
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadTopRight.png");
						 }
						 else if (world[i - 1][j + 1] > 0) // BotLeft
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadBotLeft.png");
						 }
						 else if (world[i + 1][j + 1] > 0) // BotRight
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/RoadBotRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(1/taille*i,1/taille*j,"../images/tiles/NotConstructible.png");
						 }
					 }
				 } 
			 }
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
	 public void drawMouse() {
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
	 
		Iterator<Monster> i = monsters.iterator();
		Monster m;
		while (i.hasNext()) {
			 m = i.next();
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
		Niveau n = null;
		try {
			n = Reader.func("../niveaux/niveau1.niveau");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//		drawBackground(n.getGrille());
		double x = 1/15;
		double y = 7/15;
		double d = (double) Math.round(x * 100) / 100;
		double e = (double) Math.round(y * 100) / 100;
		StdDraw.picture(e, e, "../images/tiles/RoadBotAndLeft.png");
		StdDraw.picture(d,d, "../images/tiles/RoadTopAndLeft.png");
		drawInfos();
		updateMonsters();
		drawMouse();
		return life;
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
			System.out.println("Starting game!");
		case 'q':
			System.out.println("Exiting.");
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
		switch (key) {
		case 'a':
			System.out.println("il faut ajouter une tour d'archers si l'utilisateur à de l'or !!");
			break;
		case 'b':
			System.out.println("Ici il faut ajouter une tour de bombes");
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
		System.out.println("Press A to select Arrow Tower (cost 50g).");
		System.out.println("Press B to select Cannon Tower (cost 60g).");
		System.out.println("Press E to update a tower (cost 40g).");
		System.out.println("Click on the grass to build it.");
		System.out.println("Press S to start.");
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
