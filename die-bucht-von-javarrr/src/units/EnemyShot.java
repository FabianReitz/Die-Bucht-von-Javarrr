package units;

import java.awt.Graphics;
import game.Game;
import graphics.Assets;


public class EnemyShot {
	private float x;
	private float y;
	private int width = 15;
	private int height = 18;

	


	public EnemyShot(Game game,float x,float y) {
		
		this.x = x;
		this.y = y;
		}

	
		private void feuer() {
			y = y +2;
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
			graphics.drawImage(Assets.shotEnemy, (int) x, (int) y, width, height, null);
			
		}
	}



		
	
