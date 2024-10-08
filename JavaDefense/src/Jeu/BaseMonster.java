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
		 StdDraw.picture(p.getX(),p.getY(),"../images/monsters/baseMonster.png");		
	}

	@Override
	protected Monster createMonster(Position p) {
		return new BaseMonster(p);
	}

	/**
	 * met à jour les caractéristiques du monstre
	 */
	@Override
	protected void updateStat() {
		speed = Informations.speedBase;
		degats = Informations.degatsBase;
		or = Informations.orBase;
		pdv = Informations.pdvBase;
	}

	/**
	 * @param degats infliges au monstre
	 * @return renvoie true si le monstre est mort
	 */
	@Override
	public boolean perdrePv(int degats) {
		pdv-=degats;
		return pdv<=0;
	}
}