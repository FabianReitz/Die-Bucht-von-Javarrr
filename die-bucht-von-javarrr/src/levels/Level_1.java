package levels;

import java.awt.Graphics;

import game.Game;
import units.Fleet;
import units.Gegner;
import units.Player;
import units.PlayerShot;

public class Level_1 {
	
	private Game game;
	
	
	public Level_1(Game game) {
		this.game = game;
		initLevel();
	}
	
	private void initLevel() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
			Gegner gegner = new Gegner(game, 20 + 110 * j, 20 + 75 * i, "small");
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
		
		for (PlayerShot playerShot : Player.getFlyingShots()) {
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
			 
			for(PlayerShot playerShot : Player.getFlyingShots()) {
				playerShot.render(graphics);
			}	
		
	}
}
