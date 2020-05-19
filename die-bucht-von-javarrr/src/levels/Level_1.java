package levels;

import java.awt.Graphics;

import game.Game;
import units.Gegner;
import units.Player;

public class Level_1 {
	
	private Game game;
	
	public Gegner gegner;
	public Gegner gegner1;
	public Gegner gegner2;
	
	
	public Level_1(Game game) {
		this.game = game;
		
		gegner = new Gegner(game, 190, 208, "small");
		gegner1 = new Gegner(game, 110, 204, "medium");
		gegner2 = new Gegner(game,10, 200, "big");
	}
	


	public void update() {
		gegner.update();
		gegner1.update();
		gegner2.update();
	}


	public void render(Graphics graphics) {
		gegner.render(graphics);
		gegner1.render(graphics);
		gegner2.render(graphics);
		
	}
}
