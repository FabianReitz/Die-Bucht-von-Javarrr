package graphics;
import java.awt.image.BufferedImage;


public class Assets {

	public static BufferedImage player, enemySmall, enemyMedium, enemyBig, enemyBoss, damageUp, maxLebenUp, cannonUp, scoreboard, shoot;
	public static BufferedImage[] background;
	
	
	public static void init() {

		Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("assets/sprites/Javarrr_Wasser_768x512_Spritesheet.png"));
		
		// Background Animation laden
		background = new BufferedImage[2];
		background[0]= sheet.zerteilen(0, 0, 768, 512);
		background[1]= sheet.zerteilen(768, 0, 768, 512);
			
		// Buffs
		damageUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_damage_001.png");
		maxLebenUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_hp-up_002.png");
		cannonUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_cannon_001.png");
		
		scoreboard = ImageLoader.loadImage("assets/sprites/Javarrr_Treasure-Map_002.png");
		
		// Schiffe/Units
		player = ImageLoader.loadImage("assets/sprites/Javarrr_player_ship_001.png");
		enemySmall = ImageLoader.loadImage("assets/sprites/Javarrr_enemy_small_001.png");
		enemyMedium = ImageLoader.loadImage("assets/sprites/Javarrr_enemy_medium_001.png");
		enemyBig = ImageLoader.loadImage("assets/sprites/Javarrr_enemy_big_001.png");
		enemyBoss = ImageLoader.loadImage("assets/sprites/Javarrr_enemy_boss_001.png");
		

		//Animationen
		shoot = ImageLoader.loadImage("assets/sprites/Javarrr_shoot.png");
		
	}
}
