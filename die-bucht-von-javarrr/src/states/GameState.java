package states;


import java.awt.Graphics;

import game.Game;
import graphics.Assets;
import units.Player;

public class GameState extends State{

	private Player player;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 250, 250);
		
	}
	
	@Override
	public void update() {
		
		player.update();
	}

	@Override
	public void render(Graphics graphics) {
		graphics.drawImage(Assets.background1, (int) 0, (int) 0, null);
		graphics.drawImage(Assets.scoreboard, (int) 512, (int) 0, null);
		player.render(graphics);
		
	}

	
}
