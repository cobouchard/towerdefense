package Interface;

import Jeu.Monster;

public class Arrow extends Projectile {

	
	// Entier donnant le nombre de points de vie retiré par la bombe
	int damage;
	// Position des monstres touchés
	Position p;
	
	public Arrow(Position p,Monster monstre) {
		super(p, monstre);
	}
	
	public Arrow(Position p) {
		super(p);
	}
	
	/**
	 * Affiche un projectile qui change de couleur au cours du temps
	 * Le projectile est représenté par un cercle de couleur gris clair
	 */
	//TODO
	public void draw() {
		StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
		StdDraw.setCanvasSize(10, 10); // Valeurs à tester
	}
}
