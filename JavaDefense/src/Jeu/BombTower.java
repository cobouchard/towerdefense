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
		
	}

	@Override
	public void draw() {

		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(p.getX(), p.getY(), 0.01);
		
	}
}
