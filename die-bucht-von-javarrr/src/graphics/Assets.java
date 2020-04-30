  
package graphics;
import java.awt.image.BufferedImage;


public class Assets {

<<<<<<< Updated upstream
	public static BufferedImage background1, background2, player, enemysmall, damageUp, maxLebenUp, cannonUp, scoreboard;
	
	public static void init() {

		background1 = ImageLoader.loadImage("assets/sprites/Javarrr_Water_512x512_001.png");
		background2 = ImageLoader.loadImage("assets/sprites/Javarrr_Water_512x512_002.png");
=======
	public static BufferedImage player, enemysmall, damageUp, maxLebenUp, cannonUp, scoreboard, shoot;
	public static BufferedImage[] background, menu;
	
	public static void init() {

		Spritesheet backgroundSheet = new Spritesheet(ImageLoader.loadImage("assets/sprites/Javarrr_Wasser_Spritesheet.png"));
		Spritesheet menuSheet = new Spritesheet(ImageLoader.loadImage("assets/sprites/Javarrr_Menu_Spritesheet.png"));
		
		// Background Animation laden
		background = new BufferedImage[2];
		background[0]= backgroundSheet.zerteilen(0, 0, 512, 512);
		background[1]= backgroundSheet.zerteilen(512, 0, 512, 512);
		
		//Menue Sprites laden
		menu = new BufferedImage[2];
		menu[0]= menuSheet.zerteilen(0, 0, 157, 64);
		menu[1] = menuSheet.zerteilen(157, 0, 157, 64);
			
		// Buffs
>>>>>>> Stashed changes
		damageUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_damage_001.png");
		maxLebenUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_hp-up_002.png");
		cannonUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_cannon_001.png");
		scoreboard = ImageLoader.loadImage("assets/sprites/Javarrr_Treasure-Map_002.png");
		player = ImageLoader.loadImage("assets/sprites/Javarrr_player_ship_001.png");
		
	}
}