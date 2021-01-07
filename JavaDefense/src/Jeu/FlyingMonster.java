package Jeu;

import Interface.Position;
import Interface.StdDraw;

public class FlyingMonster extends Monster{

	public FlyingMonster(Position p) {
		super(p);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw() {
		StdDraw.picture(p.getX(),p.getY(),"../images/monsters/FlyingMonster1.png");
		
	}

	@Override
	protected Monster createMonster(Position p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void updateStat(int difficulte) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected boolean perdrePv(int degats) {
		// TODO Auto-generated method stub
		return false;
	}

}
