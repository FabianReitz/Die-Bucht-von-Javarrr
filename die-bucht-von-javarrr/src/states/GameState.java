package states;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import game.Game;
import game.Musik;
import graphics.Assets;
import graphics.Background;
import units.Gegner;
import units.Player;
import units.Shoot;

public class GameState extends State{
	private Player player;
	private Background background;
	private ArrayList<Gegner> enemy = new ArrayList<Gegner>();
	
	

	
	
	public GameState(Game game) {
		super(game);
		player = new Player(game, 256, 450);
		background = new Background(game);
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
			Gegner gegner = new Gegner(game, 20 + 120 * j, 20 + 80 * i);
			enemy.add(gegner);
			}
		}
	}

	@Override
	public void update() {
		player.update();
		background.update();
		for(Gegner gegner : enemy) {
		gegner.update();
		}
		}
	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		player.render(graphics);
		for(Gegner gegner : enemy) {
		gegner.render(graphics);
		}
		
		for(Gegner gegner : enemy) {
		gegner.getSchuss().render(graphics);
		}
		
	}

	
}
