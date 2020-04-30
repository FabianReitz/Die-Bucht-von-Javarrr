package states;


import java.awt.Graphics;

import game.Game;
import graphics.Assets;
import graphics.Background;
import units.Gegner;
import units.Player;

public class GameState extends State{
	private Gegner gegner;
	private Player player;
	private Background background;
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 256, 450);
		background = new Background(game);
		gegner = new Gegner(game, 256, 200);
		
		
	}
	
	@Override
	public void update() {
		player.update();
		background.update();
		gegner.update();
	}

	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		player.render(graphics);
		gegner.render(graphics);
	}

	
}
