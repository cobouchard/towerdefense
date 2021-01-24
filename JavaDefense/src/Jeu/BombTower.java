package Jeu;

import Interface.Bomb;
import Interface.Position;
import Interface.Projectile;
import Interface.StdDraw;

public class BombTower extends Tower {

	public BombTower(int prix, double range, int speed, Position p,int projectile_compteur) {
		super(prix, range, speed, p, projectile_compteur);
	}

	@Override
	public void ameliorerTour() {
		range = range*1.75;
		this.level++;
	}

	@Override
	public void draw() {
		 StdDraw.picture(p.getX(),p.getY(),"../images/Tower2/Isometric/towerBomb.png");		
	}

	@Override
	public Projectile getProjectile(Monster m) {
		return new Bomb(new Position(p), m, degats);
	}
}
