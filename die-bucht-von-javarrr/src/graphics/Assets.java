package graphics;
import java.awt.image.BufferedImage;


public class Assets {

	//Die Images wurden auf Public gesetzt, um den Zugriff zu vereinfachen
	public static BufferedImage player, enemySmall, enemyMedium, enemyBig, enemyBoss, shotEnemy, shotPlayer,
								damageUp, maxLebenUp, cannonUp, scoreboard,  levelDone, gameWon, gameLogo,
								gameOver;
	public static BufferedImage[] background;
	
	
	public static void init() {

		Spritesheet sheet = new Spritesheet(ImageLoader.loadImage("assets/sprites/Javarrr_Wasser_768x512_Spritesheet.png"));
		
		// Background Animation laden
		background = new BufferedImage[2];
		background[0]= sheet.cut(0, 0, 768, 512);
		background[1]= sheet.cut(768, 0, 768, 512);
			
		// Buffs
		damageUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_damage_001.png");
		maxLebenUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_hp-up_002.png");
		cannonUp = ImageLoader.loadImage("assets/sprites/Javarrr_booster_cannon_001.png");
		
		
		// Anzeigen
		scoreboard = ImageLoader.loadImage("assets/sprites/Javarrr_Treasure-Map_002.png");
		levelDone = ImageLoader.loadImage("assets/sprites/Javarrr_Level_Done.png");
		gameWon = ImageLoader.loadImage("assets/sprites/Javarrr_Won.png");
		gameLogo = ImageLoader.loadImage("assets/sprites/Javarrr_logo.png");
		gameOver = ImageLoader.loadImage("assets/sprites/Javarrr_Game_Over.png");
		

		// Schiffe/Units
		player = ImageLoader.loadImage("assets/sprites/Javarrr_player_ship_001.png");
		enemySmall = ImageLoader.loadImage("assets/sprites/Javarrr_enemy_small_001.png");
		enemyMedium = ImageLoader.loadImage("assets/sprites/Javarrr_enemy_medium_001.png");
		enemyBig = ImageLoader.loadImage("assets/sprites/Javarrr_enemy_big_001.png");
		enemyBoss = ImageLoader.loadImage("assets/sprites/Javarrr_enemy_boss_001.png");
		

		// Schuesse
		shotEnemy = ImageLoader.loadImage("assets/sprites/Javarrr_Projectile_Enemy.png");
		shotPlayer = ImageLoader.loadImage("assets/sprites/Javarrr_Projectile_Player.png");
		
	}
}
