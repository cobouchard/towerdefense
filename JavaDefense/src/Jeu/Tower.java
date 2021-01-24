package Jeu;

import Interface.Position;
import Interface.Projectile;

public abstract class Tower {

	// Position de la tour
	protected Position p;
	// Entier donnant le prix de la tour
	int prix;
	//Entier donnant le niveau de la tour
	int level;
	// Entier donnant les points de vie de la tour
	int HP;
	
	//Portée de la tour
	double range;
	
	//Vitesse de tir
	int speed;
	
	//nombre d'update entre chaque apparition de monstre
	int projectile_compteur; 

	public Position getP()
	{
		return p;
	}
	
	public double getRange()
	{
		return range;
	}
	
	public double getSpeed()
	{
		return speed;
	}
	
	
	
	public int getCompteur()
	{
		return projectile_compteur;
	}
	
	public void updateCompteur()
	{
		projectile_compteur = (projectile_compteur+1)%speed;
	}
	
	Tower(int prix,double range,int speed, Position p, int projectile_compteur)
	{
		this.prix=prix;
		this.level=1;
		this.range=range;
		this.speed=speed;
		this.p=p;
		this.projectile_compteur=projectile_compteur;
	}
	
	public abstract void ameliorerTour();
	
	public abstract void draw();
	
	public abstract Projectile getProjectile(Monster m);
	
}
