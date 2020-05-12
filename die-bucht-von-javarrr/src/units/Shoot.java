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
	private static boolean sichtbar = false;
	
	private Game game;

	public Shoot(Game game,float x,float y) {
		this.game = game;
		this.x = x;
		this.y = y;
		}
	
		public void schuss() {
			sichtbar = true;
			y = y + 1;
			
			if(y == 512) {
			y = (int) Gegner.getYCoord() + 20;
			x = (int) Gegner.getXCoord();
			}
			if(y >= 400 
					&& (x >= Player.getPXcoord() 
					&& (x <= Player.getPXcoord() + 72))) {
					x = (int) Gegner.getXCoord();
					y = (int) Gegner.getYCoord();
					}
			}
		public static boolean getSichtbar() {
			return sichtbar;
		}	
		public void treffer() {
			
		}

		public void update() {
		schuss();
		}
		public void render(Graphics graphics) {
			graphics.drawImage(Assets.shoot, (int) x, (int) y, width, height, null);
		
		
		}
		
	}
