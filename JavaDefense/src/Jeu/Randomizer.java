package Jeu;

import java.util.concurrent.ThreadLocalRandom;

public final class Randomizer {
	public static int randomInt(int debut, int fin) 
	{
		return ThreadLocalRandom.current().nextInt(debut, fin+1);
	}
}
