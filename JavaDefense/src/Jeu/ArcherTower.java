package Jeu;

import Interface.Arrow;
import Interface.Position;
import Interface.Projectile;
import Interface.StdDraw;

public class ArcherTower extends Tower {

	public ArcherTower(Position p) {
		super(p);
		range = Informations.range_tour_archer;
		speed = Informations.speed_tour_archer;
		degats = Informations.degats_tour_archer;
		projectile_compteur=0;
	}
	
	private static float angleEntreDeuxPoints(float x1, float x2, float y1, float y2) {
        float xDiff = x2 - x1;
        float yDiff = y2 - y1;
        float angle = (float) ((Math.atan2(yDiff, xDiff) * (180 / Math.PI))+Math.PI);
        if (angle < 0)
        {
        	angle += 360;
        }
        return angle;
	}

	@Override
	public void ameliorerTour() {
		range = range*1.25;
		speed = (int)(speed * 88. / 100.);
		this.level++;
	}

	@Override
	public void draw() {
		 StdDraw.picture(p.getX(),p.getY(),"../images/Tower2/Isometric/towerArcher.png");		
	}
	
	protected int degatInfliges() 
	{
		return degats*level;
	}

	@Override
	public Projectile getProjectile(Monster m) {
		return new Arrow(new Position(p),m, degatInfliges(), angleEntreDeuxPoints((float)(p.getX()), 
				(float)(p.getY()),(float)(m.getP().getX()),(float)(m.getP().getY())));
	}

	@Override
	public int coutAmelioration() {
		return Informations.coutAmeliorationArcher + Informations.facteurAugmentationtCoutAmeliorationArcher*level;
	}
	
}
