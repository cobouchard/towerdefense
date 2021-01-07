package Main;

import AStar.PositionTab;

public final class UsefulFuncToMove {
	
	
	public static PositionTab mouseClickToGridPos(double x_curs, double y_curs) 
	{
		// need changes after replacement in world
/**
 *  	use this 
 *  	double normalizedX2 = (int)(StdDraw.mouseX() / squareWidth) / (double)taille;
		double normalizedY2 = (int)(StdDraw.mouseY() / squareHeight) / (double)taille;
		in World.drawMouse()
 */
		return new PositionTab( (int)(x_curs*World.taille), (int)(y_curs*World.taille) );
	}
}
