package Jeu;

public class Joueur {
	private int or;
	private int pdv;
	
	public Joueur(int or, int pdv)
	{
		this.or=or;
		this.pdv=pdv;
	}
	
	public void gagnerOr(int or_supp) 
	{
		or=or_supp;
	}
	
	/**
	 * renvoie true si la transaction est autorisée i.e. le joueur a assez d'or (la transaction est effectuée)
	 * @param prix
	 * @return
	 */
	public boolean payerOr(int prix) 
	{
		boolean test =false;
		
		if(prix<or) 
		{
			test=true;
			or-=prix;
		}
		
		return test;
	}
	
	/**
	 * 
	 * @param degat quantité de point de vie perdu par le joueur
	 * @return renvoie true si le joueur est mort (a perdu)
	 */
	public boolean perdrePv(int degat) 
	{
		pdv-=degat;
		return pdv<=0;
	}
}
