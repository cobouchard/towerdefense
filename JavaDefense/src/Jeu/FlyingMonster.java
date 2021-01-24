package Jeu;

import Interface.Position;
import Interface.StdDraw;

public class FlyingMonster extends Monster{

	public FlyingMonster(Position p) {
		super(p);
	}

	@Override
	public void draw() {
		StdDraw.picture(p.getX(),p.getY(),"../images/monsters/FlyingMonster1.png");
		
	}

	@Override
	protected Monster createMonster(Position p) {
		return new FlyingMonster(p);
	}

	@Override
	protected void updateStat(int difficulte) {
		speed = 0.02;
		degats=2;
		or = 3;
	}

	@Override
	public boolean perdrePv(int degats) {
		pdv-=degats;
		return pdv<=0;
	}

}
