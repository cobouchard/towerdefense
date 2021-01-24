package Interface;

import Jeu.Monster;

public class Arrow extends Projectile {

	
	// Entier donnant le nombre de points de vie retiré par la bombe
	int damage;
	
	public Arrow(Position p,Monster monstre) {
		super(p,monstre);
		speed = 0.03;
	}
	
	/**
	 * Affiche un projectile qui change de couleur au cours du temps
	 * Le projectile est représenté par un cercle de couleur gris clair
	 */
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(p.getX(), p.getY(), 0.01);
	}
}
