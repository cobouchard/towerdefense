package prof;

public class Projectile {
	// Position du projectile à l'instant t
	Position p;
	// Vitesse du projectile
	double speed;
	// Position du projectile à l'instant t+1
	Position nextP;
	// Boolean pour savoir si le projectile a atteint le monstre
	boolean reached;
	// Compteur de déplacement pour savoir si le projectile a atteint le monstre
	int checkpoint = 0;

	public Projectile(Position p) {
		this.p = p;
		this.nextP = new Position(p);
	}
	
//	/*
//	 * Getter des dégats infligés par le projectile
//	 */
//	public int getDamage() {
//		return this.damage;
//	}
//	/*
//	 * Setter des dégats infligés par le projectile
//	 */
//	public void setDamage(int damage2) {
//		this.damage = damage2;
//	}
	
	/**
	 * Déplace le projectile en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le projectile se dirige.
		double dx = nextP.x - p.x;
		double dy = nextP.y - p.y;
		if (dy + dx != 0){
			// Mesure la distance à laquelle le projectile a pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			p.x += ratioX * speed;
			p.y += ratioY * speed;
		}
	}
	
	public void update() {
		move();
		draw();
		checkpoint++;
	}
	
	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher le projectile sur le plateau de jeu.
	 */
	public abstract void draw();
}
