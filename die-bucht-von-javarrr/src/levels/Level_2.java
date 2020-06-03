package levels;

import java.awt.Graphics;

import game.Game;
import units.Gegner;
import units.Player;
import units.PlayerShot;

public class Level_2 {

	private Game game;
	
	public Level_2(Game game) {
		this.game = game;
		initLevel();
	}
	
	private void initLevel() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 3; j++) {
			Gegner gegner = new Gegner(game, 20 + 100 * i, 20 + 75 * j, "small");
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
