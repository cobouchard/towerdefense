package Jeu;

import Interface.Arrow;
import Interface.Position;
import Interface.Projectile;
import Interface.StdDraw;

public class ArcherTower extends Tower {

	public ArcherTower(int prix, double range, int speed, Position p,int projectile_compteur) {
		super(prix, range, speed, p,projectile_compteur);
	}
	
	public static float angleEntreDeuxPoints(float x1, float x2, float y1, float y2) {
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
		this.level++;
	}

	@Override
	public void draw() {
		 StdDraw.picture(p.getX(),p.getY(),"../images/Tower2/Isometric/towerArcher.png");		
	}

	@Override
	public Projectile getProjectile(Monster m) {
		return new Arrow(new Position(p),m, degats, angleEntreDeuxPoints((float)(p.getX()), 
				(float)(p.getY()),(float)(m.getP().getX()),(float)(m.getP().getY())));
	}

	@Override
	public int coutAmelioration() {
		return Informations.coutAmeliorationArcher + Informations.facteurAugmentationtCoutAmeliorationArcher*level;
	}
	
}
