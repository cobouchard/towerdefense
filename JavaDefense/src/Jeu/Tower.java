package Jeu;

import Interface.Position;

public abstract class Tower {

	// Position de la tour
	Position p;
	// Entier donnant le prix de la tour
	int prix;
	//Entier donnant le niveau de la tour
	int level;
	// Entier donnant les points de vie de la tour
	int HP;
	
	//Portée de la tour
	double range;
	
	//Vitesse de tir
	double speed;
	
	Tower(int prix,double range,double speed, Position p)
	{
		this.prix=prix;
		this.level=1;
		this.range=range;
		this.speed=speed;
		this.p=p;
	}
	
	public abstract void ameliorerTour();
	
}
