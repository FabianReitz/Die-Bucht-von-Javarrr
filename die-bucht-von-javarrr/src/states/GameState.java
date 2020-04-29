package states;


import java.awt.Graphics;

import game.Game;
import graphics.Assets;
import graphics.Background;
import units.Player;

public class GameState extends State{

	private Player player;
	private Background background;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 256, 450);
		background = new Background(game);
		
	}
	
	@Override
	public void update() {
		player.update();
		background.update();
	}

	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		player.render(graphics);
	}

	
}
