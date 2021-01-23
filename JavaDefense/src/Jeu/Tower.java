package Jeu;

import Interface.Position;
import Interface.StdDraw;
import Interface.Projectile;

public abstract class Tower {

	//Projectile
	Projectile projectile;
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
	
	public Position getP()
	{
		return p;
	}
	
	public double getRange()
	{
		return range;
	}
	
	public Projectile getProjectile()
	{
		return projectile;
	}
	
	Tower(int prix,double range,double speed, Position p, Projectile projectile)
	{
		this.prix=prix;
		this.level=1;
		this.range=range;
		this.speed=speed;
		this.p=p;
	}
	
	public abstract void ameliorerTour();
	
	public abstract void draw();
	
}
