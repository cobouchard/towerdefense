package Jeu;

import Interface.Position;
import Interface.StdDraw;

public class BaseMonster extends Monster {

	public BaseMonster(Position p) {
		super(p);
	}
	
	/**
	 * Affiche un monstre qui change de couleur au cours du temps
	 * Le monstre est représenté par un cercle de couleur bleue ou grise
	 */
	public void draw() {
		 StdDraw.picture(p.getX(),p.getY(),"../images/Tower2/Isometric/baseMonster.png");		
	}

	@Override
	protected Monster createMonster(Position p) {
		return new BaseMonster(p);
	}

	@Override
	protected void updateStat(int difficulte) {
		// TODO Auto-generated method stub
		speed = speed+difficulte;
	}

	@Override
	protected boolean perdrePv(int degats) {
		// TODO Auto-generated method stub
		if (degats == 0)
		{
			return false;
		}
		else
		{
			pdv = pdv - degats;
			return true;
		}
	}
}