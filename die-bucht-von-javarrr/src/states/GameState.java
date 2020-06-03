package states;


import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import game.Game;
import game.Musik;
import graphics.Assets;
import graphics.Background;
import levels.Level_1;
import units.Gegner;
import units.Player;
import units.PlayerShot;
import units.EnemyShot;



public class GameState extends State{

	private static Player player;
	private Background background;

	public GameState(Game game) {
		super(game);
		player = new Player(game, 256, 450);
		background = new Background(game);

		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
			Gegner gegner = new Gegner(game, 20 + 100 * j, 20 +  75* i, "medium");
			Gegner.getCanShoot().add(gegner);
			}
		}
		

	}
	public static Player getPlayer() {
		return player;
	}

	@Override
	public void update() {
		player.update();
		background.update();
		
		for(Gegner gegner : Gegner.getEnemys())  {
			gegner.update();
		}
	
		for(Gegner gegner : Gegner.getShooting()) {
			gegner.getSchuss().update();
		}
		
		for (PlayerShot playerShot : Player.getFlyingShots()) {
			playerShot.update();
		}	
		
		
	}
	
	@Override
	public void render(Graphics graphics) {
		background.render(graphics);
		
		player.render(graphics);
		for(Gegner gegner : Gegner.getEnemys()) {
		gegner.render(graphics);
		}	
		
		
		for(Gegner gegner : Gegner.getShooting()) {
		gegner.getSchuss().render(graphics);
		}

		 
		for(PlayerShot playerShot : Player.getFlyingShots()) {
			playerShot.render(graphics);
		}	
		}	
	


	 
}
