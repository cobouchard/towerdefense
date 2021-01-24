package Interface;

import Jeu.Monster;

public class Bomb extends Projectile {
	
	// Entier donnant le nombre de points de vie retiré par la bombe
	int damage;
	
	public Bomb(Position p, Monster monstre, int degats) {
		super(p, monstre, degats);
		speed = 0.03;
	}
	
	/**
	 * Affiche un projectile
	 * Le projectile est représenté par un triangle de couleur grise
	 */
	public void draw() {
		StdDraw.picture(p.getX(),p.getY(),"../images/projectiles/bombas.png");
	}
}
