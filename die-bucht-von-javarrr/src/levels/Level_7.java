package levels;

import java.awt.Graphics;

import game.Game;
import units.Gegner;
import units.Player;
import units.PlayerShot;

public class Level_7 {

	private Game game;
	private Player player;
	
	public Level_7(Game game, Player player) {
		this.game = game;
		this.player = player;
		initLevel();
	}
	
	private void initLevel() {
		Gegner gegner = new Gegner(game, player, 160, 10, "boss");
		Gegner.getEnemys().add(gegner);
		Gegner.getCanShoot().add(gegner);
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {
			gegner = new Gegner(game, player, 20 + 100 * j, 245 + 75 * i, "medium");
			Gegner.getEnemys().add(gegner);
			Gegner.getCanShoot().add(gegner);
			}
		}
		for (int i = 0; i < 2; i++) {
			for (int j = 0; j < 4; j++) {
			gegner = new Gegner(game, player, 20 + 100 * j, 95 + 75 * i, "big");
			Gegner.getEnemys().add(gegner);
			Gegner.getCanShoot().add(gegner);
			}
		}
	}
	


	public void update() {
		for(Gegner gegner : Gegner.getEnemys())  {
			gegner.update();
		}
		for(Gegner gegner : Gegner.getShooting()) {
			gegner.schuss.update();
		}
		
		for (PlayerShot playerShot : Player.getFlyingShots()) {
			playerShot.update();
		}	
	}


	public void render(Graphics graphics) {
		
		for(Gegner gegner : Gegner.getEnemys()) {
			gegner.render(graphics);
			}	
			for(Gegner gegner : Gegner.getShooting()) {
			gegner.schuss.render(graphics);

			}
			 
			for(PlayerShot playerShot : Player.getFlyingShots()) {
				playerShot.render(graphics);
			}	
		
	}
}

