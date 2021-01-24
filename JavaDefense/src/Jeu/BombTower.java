package Jeu;

import Interface.Bomb;
import Interface.Position;
import Interface.Projectile;
import Interface.StdDraw;

public class BombTower extends Tower {

	public BombTower(Position p) {
		super(p);
		range = Informations.range_tour_bombe;
		speed = Informations.speed_tour_bombe;
		degats = Informations.degats_tour_bombe;
		projectile_compteur=0;
	}

	@Override
	public void ameliorerTour() {
		range = range*1.75;
		this.level++;
	}

	@Override
	public void draw() {
		 StdDraw.picture(p.getX(),p.getY(),"../images/Tower2/Isometric/towerBomb.png");		
	}

	@Override
	public Projectile getProjectile(Monster m) {
		return new Bomb(new Position(p), m, degats);
	}
	
	@Override
	protected int degatInfliges() 
	{
		return degats*level;
	}

	@Override
	public int coutAmelioration() {
		return Informations.coutAmeliorationBombe + Informations.facteurAugmentationtCoutAmeliorationBombe*level;
	}
}
