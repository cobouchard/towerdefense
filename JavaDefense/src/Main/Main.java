package Main;

import Interface.Position;
import Jeu.BaseMonster;
import Jeu.FlyingMonster;
import Jeu.Monster;

public class Main {
 
	public static void main(String[] args) {
//		int width = 750;
//		int height = 750;		


		

		
		World w = new World();
		
		// Ajout d'un monstre "à la main" pour afficher comment un monstre se déplaçe. Vous ne devez pas faire pareil, mais ajouter une vague comportant plusieurs monstres 
		/*Monster monster = new FlyingMonster(new Position(startX * w.squareWidth + w.squareWidth / 2, startY * w.squareHeight + w.squareHeight / 2));
		monster.setNextP(new Position(startX * w.squareWidth + w.squareWidth / 2, 0)); 
		monster.setSpeed(0.01);
		w.monsters.add(monster);*/
		
		// Lancement de la boucle principale du jeu
		w.run();
	}
}
