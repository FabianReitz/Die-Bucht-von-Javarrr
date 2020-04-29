package graphics;

import java.awt.Graphics;

import game.Game;

public class Background {

	private Game game;
	private int x, y;
	
	public Background(Game game, int x, int y) {
		this.game = game;
		this.x = x;
		this.y = y;

	}
	
	public void update() {
		
	}
	
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.background1, (int) x, (int) y, null);
	}
	
	
}
