package graphics;
import java.awt.image.BufferedImage;


public class Assets {

	public static BufferedImage background1, background2, player, enemysmall, damageUp, maxLebenUp, cannonUp, scoreboard;
	
	public static void init() {

		background1 = ImageLoader.loadImage("assets/sprites/Javarrr_Water_512x512_001.png");
		background2 = ImageLoader.loadImage("assets/sprites/Javarrr_Water_512x512_002.png");
		damageUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_damage_001.png");
		maxLebenUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_hp-up_002.png");
		cannonUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_cannon_001.png");
		scoreboard = ImageLoader.loadImage("assets/sprites/Javarrr_Treasure-Map_002.png");
		player = ImageLoader.loadImage("assets/sprites/Javarrr_player_ship_001.png");
		
	}
}
