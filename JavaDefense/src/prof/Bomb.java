package prof;

public class Bomb extends Projectile {
	
	// Entier donnant le nombre de points de vie retiré par la bombe
	int damage;
	// Position des monstres touchés
	//TODO
	
	
	public Bomb(Position p) {
		super(p);
	}
	

	/**
	 * Affiche un projectile
	 * Le projectile est représenté par un triangle de couleur grise
	 */
	//TODO
	public void draw() {
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(p.x, p.y, 0.01);
	}
}
