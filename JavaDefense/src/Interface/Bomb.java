package Interface;

import Jeu.Monster;

public class Bomb extends Projectile {
	
	// Entier donnant le nombre de points de vie retiré par la bombe
	int damage;
	// Position des monstres touchés
	
	public Bomb(Position p, Monster monstre) {
		super(p, monstre);
	}
	
	public Bomb(Position p) {
		super(p);
	}
	
	/**
	 * Affiche un projectile
	 * Le projectile est représenté par un triangle de couleur grise
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.filledCircle(p.getX(), p.getY(), 0.01);
	}
}
