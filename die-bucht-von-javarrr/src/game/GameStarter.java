package game;

public class GameStarter {

	public static void main(String[] args) {
		//Initialisiert das Spiel und ruft die Start-Methode auf
		Game game = new Game("Die Bucht von Javarrr!", 774, 564);
		game.start();
	}

}
