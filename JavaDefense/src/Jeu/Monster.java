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
	
	ArrayList<PositionTab> chemin;
	
	protected int pdv;
	
	public Monster(Position p) {
		this.p = p;
		if(p!=null)
			this.nextP = new Position(p);
	}
	
	public void updateChemin(int[][] grille, Position chateau) 
	{
		Algorithm a = new Algorithm(grille);
		
		chemin = a.fastestWay(Converter.positionToTab(p), Converter.positionToTab(chateau));
		System.out.println(chemin);
		nextP = Converter.tabToPosition(chemin.get(chemin.size()-1-endroit_chemin));
		System.out.println(nextP);
	}
	
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}



	public Position getP() {
		return p;
	}
	
	public Position getNextP() 
	{
		return nextP;
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
			System.out.println("test");
			endroit_chemin++;
			nextP = Converter.tabToPosition( pt  );
			
		}
	}

	public void update() {
		move();
			
		
		draw();
	}
	
	/**
	 * Fonction abstraite qui sera instanciée dans les classes filles pour afficher le monstre sur le plateau de jeu.
	 */
	public abstract void draw();
	
	protected abstract Monster createMonster(Position p);
	protected abstract void updateStat(int difficulte);
	protected abstract boolean perdrePv(int degats);
}
