package Interface;

import Jeu.Monster;

public class Arrow extends Projectile {

	//Entier donnant l'orientation de la flèche
	float angle;
	// Entier donnant le nombre de points de vie retiré par la bombe
	int damage;
	
	public Arrow(Position p,Monster monstre, int degats, float angle) {
		super(p,monstre, degats);
		speed = 0.03;
		this.angle = angle;
	}
	
	public float getAngle()
	{
		return angle;
	}
	
	/**
	 * Affiche un projectile qui change de couleur au cours du temps
	 * Le projectile est représenté par un cercle de couleur gris clair
	 */
	public void draw() {
		StdDraw.picture(p.getX(), p.getY(),"../images/projectiles/arrow silver/sprite_1.png", angle);
	}
}
