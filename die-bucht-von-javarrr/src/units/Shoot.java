package units;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;

import game.Game;
import game.Window;
import graphics.Assets;


public class Shoot {
	private float x;
	private float y;
	private int width = 20;
	private int height = 20;

	


	public Shoot(Game game,float x,float y) {
		this.x = x;
		this.y = y;
		}

	
		public void feuer() {
			y = y + 1;
			}
		public float getSY() {
			return y;
		}

		public void update() {
		feuer();
		}
		public void render(Graphics graphics) {
			graphics.drawImage(Assets.shoot, (int) x, (int) y, width, height, null);
		
		
		}
		
	}
