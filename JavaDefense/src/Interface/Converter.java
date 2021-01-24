package Interface;

import AStar.PositionTab;
import Jeu.Informations;
import Main.World;

public final class Converter {
	public static PositionTab positionToTab(Position p) 
	{
		// TODO Auto-generated method stub
		double xp = p.getX();
		double yp = p.getY();
		int x = (int)(xp*World.width/(World.width/Informations.taille));
		int y = (int)(yp*World.height/(World.height/Informations.taille));
		return new PositionTab(x,y);
	}
	
	public static Position tabToPosition(PositionTab pt) 
	{
		// TODO Auto-generated method stub
		int xpt = pt.getX();
		int ypt = pt.getY();
		double x = ((double)xpt*(double)(World.width/Informations.taille))/(double)World.width + (double)(1/(double)(Informations.taille*2));
		double y = ((double)ypt*(double)(World.height/Informations.taille))/(double)World.height + (double)(1/(double)(Informations.taille*2));
		return new Position(x,y);
	}

}
