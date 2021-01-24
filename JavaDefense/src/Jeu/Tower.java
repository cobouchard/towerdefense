package Jeu;

import Interface.Position;
import Interface.Projectile;

public abstract class Tower {

	// Position de la tour
	protected Position p;
	//Entier donnant le niveau de la tour
	int level;
	
	//Portée de la tour
	double range;
	
	//Vitesse de tir
	int speed;
	
	//nombre d'update entre chaque tir
	int projectile_compteur; 
	
	int degats;

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
	
	public int getLevel() 
	{
		return level;
	}
	
	
	
	public int getCompteur()
	{
		return projectile_compteur;
	}
	
	public void updateCompteur()
	{
		projectile_compteur = (projectile_compteur+1)%speed;
	}
	
	Tower(Position p)
	{
		this.level=1;
		this.p=p;
	}
	
	public abstract int coutAmelioration();
	
	public abstract void ameliorerTour();
	
	public abstract void draw();
	
	public abstract Projectile getProjectile(Monster m);
	
	protected abstract int degatInfliges();
	
}
