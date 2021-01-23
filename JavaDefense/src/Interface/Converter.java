package Interface;

import AStar.PositionTab;
import Main.World;

public final class Converter {
	public static PositionTab positionToTab(Position p) 
	{
		// TODO Auto-generated method stub
		double xp = p.getX();
		double yp = p.getY();
		int x = (int)(xp*World.width/(World.width/World.taille));
		int y = (int)(yp*World.height/(World.height/World.taille));
		return new PositionTab(x,y);
	}
	
	public static Position tabToPosition(PositionTab pt) 
	{
		// TODO Auto-generated method stub
		int xpt = pt.getX();
		int ypt = pt.getY();
		double x = ((double)xpt*(double)(World.width/World.taille))/(double)World.width + (double)(1/(double)(World.taille*2));
		double y = ((double)ypt*(double)(World.height/World.taille))/(double)World.height + (double)(1/(double)(World.taille*2));
		return new Position(x,y);
	}
//	public static void main(String[] args) {
//		Position p = new Position(0.5, 0.0);
//		System.out.println(positionToTab(p).getX() + " " + positionToTab(p).getY());
//		PositionTab pt = new PositionTab(0, 12);
//		System.out.println(tabToPosition(pt).getX() + " " + tabToPosition(pt).getY());
//	}

}
