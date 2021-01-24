package Interface;

import Jeu.Monster;

public class Bomb extends Projectile {
	
	public Bomb(Position p, Monster monstre, int degats) {
		super(p, monstre, degats);
		speed = 0.03;
	}
	
	/**
	 * Affiche un projectile
	 * Le projectile est représenté par une bombe
	 */
	public void draw() {
		StdDraw.picture(p.getX(),p.getY(),"../images/projectiles/bomb.png");
	}
}
