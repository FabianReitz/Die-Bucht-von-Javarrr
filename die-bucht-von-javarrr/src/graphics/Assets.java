package graphics;
import java.awt.image.BufferedImage;


public class Assets {

	public static BufferedImage player,gegner, enemysmall, damageUp, maxLebenUp, cannonUp, scoreboard, shoot;
	public static BufferedImage[] background;
	
	
	public static void init() {

		Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("assets/sprites/Javarrr_Wasser_Spritesheet.png"));
		
		// Background Animation laden
		background = new BufferedImage[2];
		background[0]= sheet.zerteilen(0, 0, 512, 512);
		background[1]= sheet.zerteilen(512, 0, 512, 512);
			
		// Buffs
		damageUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_damage_001.png");
		maxLebenUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_hp-up_002.png");
		cannonUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_cannon_001.png");
		
		scoreboard = ImageLoader.loadImage("assets/sprites/Javarrr_Treasure-Map_002.png");
		
		// Schiffe/Units
		player = ImageLoader.loadImage("assets/sprites/Javarrr_player_ship_001.png");
		gegner = ImageLoader.loadImage("assets/sprites/Javarrr_player_ship_001.png");
		

		//Animationen
		shoot = ImageLoader.loadImage("assets/sprites/Javarrr_shoot.png");
		
	}
}
