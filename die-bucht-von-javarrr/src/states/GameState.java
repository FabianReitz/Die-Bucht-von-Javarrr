package states;


import java.awt.Graphics;

import game.Game;
import units.Player;

public class GameState extends State{

	private Player player;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 100, 100);
		
	}
	
	@Override
	public void update() {
		player.update();
	}

	@Override
	public void render(Graphics graphics) {
		player.render(graphics);
	}

	
}
