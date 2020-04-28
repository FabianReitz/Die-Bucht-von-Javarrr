package graphics;
import java.awt.image.BufferedImage;


public class Assets {

	public static BufferedImage background, player, enemysmall;
	
	public static void init() {

		player = ImageLoader.loadImage("assets/Javarrr_player_ship_001.png");
	}
}
