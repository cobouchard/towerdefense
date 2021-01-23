package Jeu;

import Interface.Position;
import Interface.StdDraw;

public class ArcherTower extends Tower {

	public ArcherTower(int prix, double range, double speed, Position p) {
		super(prix, range, speed, p);
	}

	@Override
	public void ameliorerTour() {
		// TODO Auto-generated method stub
		range = range*1.25;
		speed = speed*1.75;
		this.level++;
	}

	@Override
	public void draw() {
		 StdDraw.picture(p.getX(),p.getY(),"../images/Tower2/Isometric/towerArcher.png");		
	}
	
}
