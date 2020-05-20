package units;

import java.awt.Graphics;

import game.Game;
import graphics.Assets;

public class PlayerShot {
	private float x;
	private float y;
	private int width = 20;
	private int height = 20;
	public boolean sichtbar = false;

	


	public PlayerShot(Game game,float x,float y) {
		
		this.x = x;
		this.y = y;
		}

	
		public void feuer() {
			sichtbar = true;
			y = y - 2;
		}
		
		public float getSY() {
			return y;
		}
		
		public float getSX() {
			return x;
		}

		
		public void update() {
		feuer();

			
		}
	
		public void render(Graphics graphics) {
			graphics.drawImage(Assets.shotPlayer, (int) x, (int) y, width, height, null);
			
		}
	
}
