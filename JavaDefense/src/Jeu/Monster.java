package Jeu;

import java.util.ArrayList;

import AStar.Algorithm;
import AStar.PositionTab;
import Interface.Converter;
import Interface.Position;

public abstract class Monster {
	// Position du monstre à l'instant t
	protected Position p;
	// Vitesse du monstre
	double speed;
	// Position du monstre à l'instant t+1
	private Position nextP;
	// Boolean pour savoir si le monstre à atteint le chateau du joueur
	boolean reached;
	// Compteur de déplacement pour savoir si le monstre à atteint le chateau du joueur
	int checkpoint = 0;
	
	int endroit_chemin =1;
	
	protected int degats;
	protected int or;
	
	ArrayList<PositionTab> chemin;
	
	protected int pdv;
	
	public Monster(Position p) {
		this.p = p;
		if(p!=null)
			this.nextP = new Position(p);
	}
	
	public void updateChemin(int[][] grille, Position chateau) 
	{
		endroit_chemin=1;
		Algorithm a = new Algorithm(grille);
		
		chemin = a.fastestWay(Converter.positionToTab(p), Converter.positionToTab(chateau));
		nextP = Converter.tabToPosition(chemin.get(chemin.size()-1-endroit_chemin));
	}



	public Position getP() {
		return p;
	}
	
	public Position getNextP() 
	{
		return nextP;
	}

	public int getDegats() 
	{
		return degats;
	}




	/**
	 * Déplace le monstre en fonction de sa vitesse sur l'axe des x et des y et de sa prochaine position.
	 */
	public void move() {
		// Mesure sur quel axe le monstre se dirige.
		double dx = nextP.getX() - p.getX();
		double dy = nextP.getY() - p.getY();
		if (dy + dx != 0){
			// Mesure la distance à laquelle le monstre à pu se déplacer.
			double ratioX = dx/(Math.abs(dx) + Math.abs(dy));
			double ratioY = dy/(Math.abs(dx) + Math.abs(dy));
			p.setX(ratioX * speed + p.getX());
			p.setY(ratioY * speed + p.getY());
		}
		
		updateNextP();
			
	}
	
	private void updateNextP() 
	{
		PositionTab pt = chemin.get(chemin.size()-1-endroit_chemin);
		
		if(Converter.positionToTab(nextP).equals(Converter.positionToTab(p)) ) //si il est arrivé à la nouvelle case, on change sa direction
		{
			
			if(endroit_chemin!=chemin.size()-1)
				endroit_chemin++;
			nextP = Converter.tabToPosition( pt  );
			
		}
	}

	public void update() {
		move();
			
		
		draw();
	}
	
	public int getOr() 
	{
		return or;
	}
	
	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
	
	protected abstract Monster createMonster(Position p);
	protected abstract void updateStat();
	public abstract boolean perdrePv(int degats);
	
}
