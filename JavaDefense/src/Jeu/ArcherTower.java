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
		
	}

	@Override
	public void draw() {

		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledCircle(p.getX(), p.getY(), 0.01);
		
	}
	
}
