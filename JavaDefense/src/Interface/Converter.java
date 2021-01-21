package Interface;

import AStar.PositionTab;
import Main.World;

public final class Converter {
	public static PositionTab positionToTab(Position p) 
	{
		// TODO Auto-generated method stub
		double xp = p.getX();
		double yp = p.getY();
		int x = (int)(xp*750/15);
		int y = (int)(yp*750/15);
		return new PositionTab(x,y);
	}
	
	public static Position tabToPosition(PositionTab pt) 
	{
		// TODO Auto-generated method stub
		int xpt = pt.getX();
		int ypt = pt.getY();
		double x = xpt*15/750;
		double y = ypt*15/750;
		return new Position(x,y);
	}
}
