package Interface;

import Jeu.Monster;

public abstract class Projectile {
	// Position du projectile à l'instant t
	protected Position p;
	// Vitesse du projectile
	double speed;
	// Position du projectile à l'instant t+1
	Position nextP;
	// Boolean pour savoir si le projectile a atteint le monstre
	boolean reached;
	// Compteur de déplacement pour savoir si le projectile a atteint le monstre
	int checkpoint = 0;
	// Monstre visé par le projectile
	Monster monstre;
	
	private int degats;

	public Projectile(Position p, Monster monstre, int degats) {
		this.p = p;
		this.nextP = monstre.getP();
		this.monstre = monstre;
		this.degats=degats;
	}
	
	public int getDegats() 
	{
		return degats;
	}
	
	
	/**
	 * Déplace le projectile en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le projectile se dirige.
		double dx = nextP.getX() - p.getX();
		double dy = nextP.getY() - p.getY();
		if (dy + dx != 0){
			// Mesure la distance à laquelle le projectile a pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			p.setX(ratioX * speed + p.getX());
			p.setY(ratioY * speed + p.getY());
		}
	}
	
	public void update() {
		move();
		this.nextP = monstre.getP();
		draw();
		checkpoint++;
	}
	
	public Position getP() 
	{
		return p;
	}
	
	public Monster getMonster() 
	{
		return monstre;
	}
	
	
	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher le projectile sur le plateau de jeu.
	 */
	public abstract void draw();
}
