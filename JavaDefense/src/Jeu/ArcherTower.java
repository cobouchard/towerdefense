package Jeu;

import Interface.Arrow;
import Interface.Position;
import Interface.Projectile;
import Interface.StdDraw;

public class ArcherTower extends Tower {

	public ArcherTower(int prix, double range, int speed, Position p,int projectile_compteur) {
		super(prix, range, speed, p,projectile_compteur);
	}

	@Override
	public void ameliorerTour() {
		range = range*1.25;
		this.level++;
	}

	@Override
	public void draw() {
		 StdDraw.picture(p.getX(),p.getY(),"../images/Tower2/Isometric/towerArcher.png");		
	}

	@Override
	public Projectile getProjectile(Monster m) {
		return new Arrow(new Position(p),m, degats);
	}
	
}
