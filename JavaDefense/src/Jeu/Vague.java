package Jeu;

import Interface.Position;

public class Vague<M extends Monster> {
	private int nb_monstre_total;
	private int nb_monstre_actuel;
	
	private M monster;
	
	private int difficulte;
	
	Vague(int nb_monstre_total, int difficulte)
	{
		this.nb_monstre_total=nb_monstre_total;
		nb_monstre_actuel=nb_monstre_total;
		this.difficulte=difficulte;
	}
	
	public int getNbMonstreTotal() 
	{
		return this.nb_monstre_total;
	}
	
	/**
	 * créé un nouveau monstre, initialise sa position, met à jour le nombre de monstres restants dans la vague
	 * @param p position du nouveau monstre crée
	 * @return renvoie un monstre de type M
	 */
	public Monster getMonster(Position p) 
	{
		if(nb_monstre_actuel==0)
			return null;
		
		nb_monstre_actuel--;
		Monster m = monster.createMonster(p);
		m.updateStat(difficulte);
		return m;
	}
	
}
