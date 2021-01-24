package Jeu;

import Interface.Position;
import Interface.StdDraw;

public class BossMonster extends Monster{

	public BossMonster(Position p) {
		super(p);
	}

	public void draw() {
		StdDraw.picture(p.getX(),p.getY(),"../images/monsters/BossMonster1.png");		
	}

	@Override
	protected Monster createMonster(Position p) {
		return new BossMonster(p);
	}

	@Override
	protected void updateStat(int difficulte) {
		speed = Informations.speedBoss;
		degats = Informations.degatsBoss;
		or = Informations.orBoss;
	}

	@Override
	public boolean perdrePv(int degats) {
		pdv-=degats;
		return pdv<=0;
	}

}
