package Interface;

import AStar.PositionTab;

public final class Converter {
	public static PositionTab positionToTab(Position p) 
	{
		// TODO Auto-generated method stub
		int x = (int)p.getX();
		int y = (int)p.getY();
		return new PositionTab(x,y);
	}
	
	public static Position tabToPosition(PositionTab pt) 
	{
		// TODO Auto-generated method stub
		double x = (double)pt.getX();
		double y = (double)pt.getY();
		return new Position(x,y);
	}
}
