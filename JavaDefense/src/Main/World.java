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
	
	// l'ensemble des monstres, pour gerer (notamment) l'affichage (finira par disparaitre)
	List<Monster> monsters = new ArrayList<Monster>();
	
	
	
	// Information sur la taille du plateau de jeu
	public int width;
	int height;
	double squareWidth;
	double squareHeight;
	
	
	
	// Commande sur laquelle le joueur appuie (sur le clavier)
	char key;
	
	// Condition pour terminer la partie
	boolean end = false;
	
	// Niveau
	Niveau niveau = null;
	
	
	/**
	 * Initialisation du monde en fonction de la largeur, la hauteur et le nombre de cases données
	 * @param width
	 * @param height
	 * @param nbSquareX
	 * @param nbSquareY
	 * @param startSquareX
	 * @param startSquareY
	 */
	public World(int width, int height) {
		this.width = width;
		this.height = height;
		squareWidth = (double) 1 / taille;
		squareHeight = (double) 1 / taille;
		
		try {
			niveau = Reader.func("../niveaux/niveau3.niveau");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
				
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
					 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/EmptyTile.png");
				 }
				 else if (niveau.getGrille()[i][j] > 0)
				 {
					 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/Road.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/RoadToPRight.png");
						 }
						 else // Others
						 {
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
							 StdDraw.picture(n*i+25./750.,n*j+25./750.,"../images/tiles/NotConstructible.png");
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
		
		drawBackground();
		
//        double normalizedY2 = (int)(7./15 / squareHeight) * squareHeight + squareHeight / 2;
//        
//		StdDraw.picture(normalizedX2, normalizedX2, "../images/tiles/RoadBotAndLeft.png");
//		StdDraw.picture(normalizedY2,normalizedY2, "../images/tiles/RoadTopAndLeft.png");
		drawInfos();
		updateMonsters();
		drawMouse();
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
