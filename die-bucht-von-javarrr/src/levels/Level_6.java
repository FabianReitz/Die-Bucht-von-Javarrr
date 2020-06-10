package levels;

import java.awt.Graphics;

import game.Game;
import units.Gegner;
import units.Player;
import units.PlayerShot;

public class Level_6 {

	private Game game;
	private Player player;
	

	public Level_6(Game game, Player player) {

		this.game = game;
		this.player = player;
		initLevel();
	}
	
	private void initLevel() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {

			Gegner gegner = new Gegner(game, 20 + 100 * j, 20 + 75 * i, "big");
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

