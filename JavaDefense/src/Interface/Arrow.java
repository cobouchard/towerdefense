package Interface;

public class Arrow extends Projectile {

	
	// Entier donnant le nombre de points de vie retiré par la bombe
	int damage;
	// Position des monstres touchés
	Position p;
	
	public Arrow(Position p) {
		super(p);
	}
	
	/**
	 * Affiche un projectile qui change de couleur au cours du temps
	 * Le projectile est représenté par un cercle de couleur noire à rouge
	 */
	//TODO
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(p.getX(), p.getY(), 0.01);
	}
}
