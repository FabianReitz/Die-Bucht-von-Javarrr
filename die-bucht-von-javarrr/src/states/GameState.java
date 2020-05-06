package states;


import java.awt.Graphics;
import java.util.ArrayList;

import game.Game;
import game.Musik;
import graphics.Assets;
import graphics.Background;
import levels.Level_1;
import units.Gegner;
import units.Player;

public class GameState extends State{

	private Player player;
	private Background background;

	private Level_1 level1;
	
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 256, 450);
		background = new Background(game);
		level1 = new Level_1(game);

		
	}
	@Override
	public void update() {
		player.update();
		background.update();
		level1.update();

	}

	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		player.render(graphics);
		level1.render(graphics);

	}

	
}
