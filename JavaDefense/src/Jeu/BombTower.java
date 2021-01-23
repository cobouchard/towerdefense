package Jeu;

import Interface.Position;
import Interface.Projectile;
import Interface.StdDraw;

public class BombTower extends Tower {

	public BombTower(int prix, double range, double speed, Position p, Projectile projectile) {
		super(prix, range, speed, p, projectile);
	}

	@Override
	public void ameliorerTour() {
		// TODO Auto-generated method stub
		range = range*1.75;
		speed = speed*1.50;
		this.level++;
	}

	@Override
	public void draw() {
		 StdDraw.picture(p.getX(),p.getY(),"../images/Tower2/Isometric/towerBomb.png");		
	}
}
