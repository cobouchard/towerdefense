package AStar;

public class Cell {
	private int gCost; //distance jusqu'à la cellule de départ
	private int hCost; // distance jusq'à la cellule d'arrivée
	private int totalCost; //somme des deux couts
	
	
	@Override
	public String toString() 
	{
		return String.valueOf(gCost) + "," + String.valueOf(hCost) + "," + String.valueOf(totalCost) + ":" + String.valueOf(VALUE);
	}
	
	/**
	 * Une value positive est un bout de route (là où passe les monstres)
	 * Une value égale à -1 est un endroit de construction (là où on pose des tours)
	 */
	private final int VALUE;
	
	Cell(int value)
	{
		this.VALUE=value;
		gCost = 0;
		hCost = 0;
		totalCost = 0;
	}
	
	public int getValue() 
	{
		return VALUE;
	}
	
	public int getTotalCost() 
	{
		return totalCost;
	}

	public int getgCost() {
		return gCost;
	}
	
	public int gethCost() {
		return hCost;
	}

	public void setgCost(int gCost) {
		this.gCost = gCost;
		updateTotalCost(); 
	}

	public void sethCost(int hCost) {
		this.hCost = hCost;
		updateTotalCost(); 
	}
	
	private void updateTotalCost() 
	{
		totalCost=gCost+hCost;
	}
	
	
}
