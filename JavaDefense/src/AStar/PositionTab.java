package AStar;


public class PositionTab {
	private int x;
	private int y;
	
	@Override
	public String toString() 
	{
		return x+","+y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionTab other = (PositionTab) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public PositionTab(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void inversion() 
	{
		int temp_x = x;
		x=y;
		y=temp_x;
	}
	
}
