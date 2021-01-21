package Jeu;

import Interface.Position;
import Interface.StdDraw;

public class BombTower extends Tower {

	public BombTower(int prix, double range, double speed, Position p) {
		super(prix, range, speed, p);
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

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(p.getX(), p.getY(), 0.01);
		
	}
}
