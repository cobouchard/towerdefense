package Interface;



public class Position {
	private double x;
	private double y;
	
	/**
	 * Classe qui permet d'avoir la position sur l'axe des x et des y des monstres et des tours
	 * @param x
	 * @param y
	 */
	public Position(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Position(Position p) {
		x = p.x;
		y = p.y;
	}
	
	public boolean equals(Position p) {
		return x == p.x && y == p.y;
	}
	
	
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	/**
	 * Mesure la distance euclidienne entre 2 positions.
	 * @param p
	 * @return
	 */
	public double dist(Position p) {
		return Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}
	
	/**
	 * Retourne la position du point sur l'axe des x et des y
	 */
	public String toString() {
		return "(" + Double.toString(x) + "," + Double.toString(y) + ")";
	}
}