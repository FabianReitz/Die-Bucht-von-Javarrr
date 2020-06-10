package levels;

import java.awt.Graphics;

import game.Game;
import units.Gegner;
import units.Player;
import units.PlayerShot;

public class Level_3 {

	private Game game;
	private Player player;
	

	
	public Level_3(Game game, Player player) {

		this.game = game;
		this.player = player;
		initLevel();
	}
	
	private void initLevel() {
		for (int i = 0; i < 1; i++) {
			for (int j = 0; j < 4; j++) {

			Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "medium");
			game.getFleet().getEnemys().add(gegner);
			game.getFleet().getCanShoot().add(gegner);

			}
		}
		
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {

			Gegner gegner = new Gegner(game, 20 + 100 * j, 95 + 75 * i, "small");
			game.getFleet().getEnemys().add(gegner);
			game.getFleet().getCanShoot().add(gegner);

			}
		}
	}
	


	public void update() {
		for(Gegner gegner : game.getFleet().getEnemys())  {
			gegner.update();
		}
		for(Gegner gegner : game.getFleet().getShooting()) {
			gegner.getSchuss().update();
		}
		
		for (PlayerShot playerShot : player.getFlyingShots()) {
			playerShot.update();
		}	
	}


	public void render(Graphics graphics) {
		
		for(Gegner gegner : game.getFleet().getEnemys()) {
			gegner.render(graphics);
			}	
			for(Gegner gegner : game.getFleet().getShooting()) {
			gegner.getSchuss().render(graphics);

			}
			 
			for(PlayerShot playerShot : player.getFlyingShots()) {
				playerShot.render(graphics);
			}	
		
	}
}

