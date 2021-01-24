package Interface;

import Jeu.Monster;

public class Arrow extends Projectile {

	//Float donnant l'orientation de la flèche
	float angle;
	
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
	 * Le projectile est représenté par une flèche
	 */
	public void draw() {
		StdDraw.picture(p.getX(), p.getY(),"../images/projectiles/arrow.png", angle);
	}
}
