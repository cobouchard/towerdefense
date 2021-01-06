package Jeu;


import Interface.Position;

public class Vague {
	private int nb_monstre_total;
	private int nb_monstre_actuel;
	
	private Monster monster;
	
	private int difficulte;
	
	public Vague(int nb_monstre_total, int difficulte, Monster monster)
	{
		this.monster=monster;
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

	@Override
	public String toString() {
		return "Vague [Nombre de monstre=" + nb_monstre_total + ", Type de Monstre=" + monster.getClass() + ", Difficulté=" + difficulte
				+ "]";
	}
	
	
}
