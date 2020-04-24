package graphics;
import java.awt.image.BufferedImage;


public class Assets {

	public static BufferedImage background, player, enemysmall;
	
	public static void init() {
		background = ImageLoader.loadImage("Javarrr_Water_512x512_001.png");
		player = ImageLoader.loadImage("Javarrr_player_ship_001.png");
	}
}
